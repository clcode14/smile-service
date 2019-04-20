package org.flightythought.smile.appserver;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName TestDate
 * @CreateTime 2019/4/17 23:06
 * @Description: TODO
 */
public class TestDate {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2019-04-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime now = LocalDateTime.now();
        long daysDiff = ChronoUnit.DAYS.between(localDateTime, now);
        System.out.println(daysDiff);
    }
}
