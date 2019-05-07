package com.kadir.twitterbots.ratelimithandler.process;

/**
 * @author akadir
 * Date: 09/12/2018
 * Time: 23:23
 */
public enum ApiProcessType {
    SEARCH("search"), SHOW_STATUS("showStatus", 1), SHOW_USER("showUser", 1), UPDATE_STATUS("updateStatus"),
    GET_BLOCKS_IDS("getBlocksIDs"), GET_USER_LISTS("getUserLists"), GET_USER_LIST_MEMBERS("getUserListMembers", 1),
    SHOW_FRIENDSHIP("showFriendship"), CREATE_USER_LIST("createUserList"), CREATE_USER_LIST_MEMBERS("createUserListMembers"),
    DESTROY_STATUS("destroyStatus", 1), DESTROY_FAVORITE("destroyFavorite");

    private String name;
    private int delayInSecond = -1;

    private ApiProcessType(String name) {
        this.name = name;
    }

    private ApiProcessType(String name, int delayInSecond) {
        this.name = name;
        this.delayInSecond = delayInSecond;
    }

    public String getName() {
        return name;
    }

    public int getDelayInSecond() {
        return delayInSecond;
    }
}
