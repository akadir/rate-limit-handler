# rate-limit-handler

[![Build Status](https://travis-ci.org/akadir/rate-limit-handler.svg?branch=master)](https://travis-ci.org/akadir/rate-limit-handler)
[![gradle-version](https://img.shields.io/badge/gradle-5.5.1-brightgreen)](https://img.shields.io/badge/gradle-5.5.1-brightgreen)

Project to handle twitter api rate limits.

Using RateLimitStatus object and calculates average time per api call and calls Thread.sleep() for calculated time.

You can update default delay for api calls in [ApiProcessType](src/main/java/com/kadir/twitterbots/ratelimithandler/process/ApiProcessType.java) enumeration.

### Usage

to use in your gradle project add this into your settings.gradle and build.gradle files respectively:

settings.gradle:

```groovy
sourceControl {
  gitRepository("https://github.com/akadir/rate-limit-handler.git") {
    producesModule("com.kadir.twitterbots.commons:rate-limit-handler")
  }
}
```

build.gradle:

```groovy
dependencies {
  implementation 'com.kadir.twitterbots.commons:rate-limit-handler:1.0'
}
```

And then call `RateLimitHandler.handle() method after your api calls:

ex-1:
```
Status updatedStatus = twitter.updateStatus(textToTweet);
RateLimitHandler.handle(twitter.hashCode(), updatedStatus.getRateLimitStatus(), ApiProcessType.UPDATE_STATUS);
```

ex-2:
```
Paging paging = new Paging(1, 100);
ResponseList<Status> statuses;
statuses = twitter.getUserTimeline(twitter.getId(), paging);
RateLimitHandler.handle(twitter.getId(), statuses.getRateLimitStatus(), ApiProcessType.GET_USER_TIMELINE);
```

### Projects Used rate-limit-handler

- [alligator](https://github.com/akadir/alligator)
- [crocodile](https://github.com/akadir/crocodile)
- [eagle](https://github.com/akadir/eagle)
- [elephant](https://github.com/akadir/elephant)
- [giraffe](https://github.com/akadir/giraffe)
- [squirrel](https://github.com/akadir/squirrel)
- [worm](https://github.com/akadir/worm)
