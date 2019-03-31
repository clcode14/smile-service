package org.flightythought.smile.appserver.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName IpUtil.java
 * @CreateTime 2019/3/29 1:07
 * @Description: IP工具类
 */
public class IpUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 摘自：http://blog.csdn.net/scugxl/article/details/47818007
     * ipv4 To Long
     *
     * @param ipv4
     * @return
     */
    public static long ip2long(String ipv4) {

        String[] splits = ipv4.split("\\.");
        long l = 0;
        l = l + (Long.valueOf(splits[0], 10)) << 24;
        l = l + (Long.valueOf(splits[1], 10) << 16);
        l = l + (Long.valueOf(splits[2], 10) << 8);
        l = l + (Long.valueOf(splits[3], 10));
        return l;
    }

    /**
     * 摘自：http://blog.csdn.net/scugxl/article/details/47818007
     * ipv6 To BigInteger
     *
     * @param ipv6
     * @return
     */
    public static BigInteger ipv6toInt(String ipv6) {

        int compressIndex = ipv6.indexOf("::");
        if (compressIndex != -1) {
            String part1s = ipv6.substring(0, compressIndex);
            String part2s = ipv6.substring(compressIndex + 1);
            BigInteger part1 = ipv6toInt(part1s);
            BigInteger part2 = ipv6toInt(part2s);
            int part1hasDot = 0;
            char ch[] = part1s.toCharArray();
            for (char c : ch) {
                if (c == ':') {
                    part1hasDot++;
                }
            }
            return part1.shiftLeft(16 * (7 - part1hasDot)).add(part2);
        }
        String[] str = ipv6.split(":");
        BigInteger big = BigInteger.ZERO;
        for (int i = 0; i < str.length; i++) {
            if (str[i].isEmpty()) {
                str[i] = "0";
            }
            big = big.add(BigInteger.valueOf(Long.valueOf(str[i], 16))
                    .shiftLeft(16 * (str.length - i - 1)));
        }
        return big;
    }

    public static void main(String[] args) {
        System.out.println(ip2long("183.156.219.110"));
    }

}
