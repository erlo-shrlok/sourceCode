package com.jinzunyue.share.tools;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

/**
 * 随机数生成工具
 */
public class RandomUtil {
    /**
     * 创建一个随机字符串，其长度是指定的字符数，将从数字字符集中选择字符。
     * count:生成随机数的长度
     */
    public static String randomNumeric(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 随机数Int的生成
     */
    public static int getRandomForIntegerUnbounded() {
        int intUnbounded = new Random().nextInt();
        System.out.println(intUnbounded);
        return intUnbounded;
    }


    /**
     * 随机数Long的生成
     */
    public static long getRandomForLongUnbounded() {
        long unboundedLong = new Random().nextLong();
        System.out.println(unboundedLong);
        return unboundedLong;
    }

    /**
     * 随机数Double的生成
     */
    public static double getRandomForDouble0To1() {
        double generatorDouble = new Random().nextDouble();
        System.out.println(generatorDouble);
        return generatorDouble;
    }
}
