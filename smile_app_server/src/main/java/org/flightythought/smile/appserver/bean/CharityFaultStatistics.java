package org.flightythought.smile.appserver.bean;

import lombok.Data;

@Data
public class CharityFaultStatistics {

    /**
     * 行善次数
     */
    private int charityCount;

    /**
     * 忏悔次数
     */
    private int faultCount;

    /**
     * 计分
     */
    private int score;

    /**
     * 用户ID
     */
    private UserInfo user;
}
