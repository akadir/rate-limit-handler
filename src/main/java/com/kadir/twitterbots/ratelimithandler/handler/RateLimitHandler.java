package com.kadir.twitterbots.ratelimithandler.handler;

import com.kadir.twitterbots.ratelimithandler.process.ApiProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.RateLimitStatus;

import java.util.HashMap;

/**
 * @author akadir
 * Date: 09/12/2018
 * Time: 15:47
 */
public class RateLimitHandler {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitHandler.class);

    private static HashMap<String, RateLimitStatus> rateLimit = new HashMap<>();

    private RateLimitHandler() {
    }

    public static void handle(long id, RateLimitStatus rts, ApiProcessType processType) throws InterruptedException {
        //as default use 180 calls per 15 minute period (+15 seconds)
        double sleep = processType.getDelayInSecond() > 0 ? processType.getDelayInSecond() : 15.0 * 61.0 / 180.0;
        if (rts != null) {
            RateLimitStatus oldRTS = rateLimit.get(id + "-" + processType.getName());
            double remaining = rts.getRemaining();
            double resetTime = rts.getSecondsUntilReset();

            if (remaining == 0) {
                sleep = rts.getSecondsUntilReset() + 1.0;
            } else if (oldRTS != null && oldRTS.getRemaining() > rts.getRemaining()) {
                double oldRemaining = oldRTS.getRemaining();
                double oldResetTime = oldRTS.getSecondsUntilReset();

                double dif = oldResetTime - resetTime;
                if (dif < (oldResetTime / oldRemaining)) {
                    sleep = (oldResetTime / oldRemaining) - dif + 0.3;
                }
            }

            rateLimit.put(id + "-" + processType.getName(), rts);
        }

        if (sleep > 0) {
            if (logger.isDebugEnabled())
                logger.debug("Sleep {} seconds for the next {} process. Thread name: {}", String.format("%.2f", sleep), processType.getName(), Thread.currentThread().getName());
            Thread.sleep((long) (sleep * 1000));
        }
    }
}
