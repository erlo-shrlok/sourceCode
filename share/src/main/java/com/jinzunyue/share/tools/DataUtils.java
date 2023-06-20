package com.jinzunyue.share.tools;

import cn.hutool.core.util.NumberUtil;

import java.math.BigInteger;

public class DataUtils {
    /**
     *  62位有序字符
     */
    public static String CHARS62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static BigInteger SCALE62 = new BigInteger("62", 10);
    private static BigInteger SCALE62_MAX_INDEX = new BigInteger("61", 10);

    /**
     * 将数字转为62进制，采用短除法计算方式
     *
     * @param numString 数字字符串
     * @return 62进制字符串
     */
    public static String encode(String numString) {
        if (isEmpty(numString) || !NumberUtil.isNumber(numString)) {
            throw new NumberFormatException("Zero length BigInteger");
        }
        return encode(new BigInteger(numString));
    }


    /**
     * 将数字转为62进制，采用短除法计算方式
     *
     * @param num Long 型数字
     * @return 62进制字符串
     */
    public static String encode(BigInteger num) {

        StringBuilder sb = new StringBuilder();
        BigInteger remainder = null;
        while (num.compareTo(SCALE62_MAX_INDEX) == 1) {

            // 对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转（reverse）字符串
            remainder = num.mod(SCALE62);
            sb.append(CHARS62.charAt(remainder.intValue()));
            num = num.divide(SCALE62);
        }

        sb.append(CHARS62.charAt(num.intValue()));
        String value = sb.reverse().toString();
        return value;
    }

    /**
     * 62进制字符串转为数字
     *
     * @param str 编码后的62进制字符串
     * @return 解码后的 10 进制字符串
     */
    public static String decode(String str) {

        if (isEmpty(str)) {
            throw new NumberFormatException("Zero length BigInteger");
        }

        // 将 0 开头的字符串进行替换
        str = str.replace("^0*", "");
        BigInteger num = new BigInteger("0");
        int index = 0;
        for (int i = 0; i < str.length(); i++) {

            // 查找字符的索引位置
            index = CHARS62.indexOf(str.charAt(i));

            // 索引位置代表字符的数值
            BigInteger bigIndex = new BigInteger(index + "", 10);
            BigInteger temp = SCALE62.pow(str.length() - i - 1);
            num = num.add(bigIndex.multiply(temp));
        }
        return num.toString();
    }
    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    private static boolean isEmpty(String str) {
        return (null == str || str.length() == 0);
    }
}
