package com.jinzunyue.share.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OriginCode5 {

    public static String GenerateOriginCode(String hanzi){

        return "end";
    }

    public static String convertChineseToNumber(String chinese) {
        StringBuilder result = new StringBuilder(); // 创建一个StringBuilder来存储结果
        for (char c : chinese.toCharArray()) { // 遍历输入字符串中的每个字符
            String code = Integer.toString((int) c); // 将字符转换为其对应的Unicode码点（一个整数），然后将这个整数转换为字符串
            result.append(code.length()).append(code); // 将这个整数的长度和这个整数本身添加到结果中
        }
        return result.toString(); // 返回结果
    }

    public static String convertNumberToChinese(String number) {
        StringBuilder result = new StringBuilder(); // 创建一个StringBuilder来存储结果
        for (int i = 0; i < number.length();) { // 遍历输入字符串中的每个字符
            int length = Character.getNumericValue(number.charAt(i++)); // 读取并移除第一个字符（这个字符表示接下来的整数的长度）
            String code = number.substring(i, i + length); // 读取接下来的整数
            char c = (char) Integer.parseInt(code); // 将这个整数转换为对应的字符
            result.append(c); // 将这个字符添加到结果中
            i += length; // 移动到下一个整数的位置
        }
        return result.toString(); // 返回结果
    }


    public static String convertDecimalToBinary(String decimal) {
        StringBuilder result = new StringBuilder(); // 创建一个StringBuilder来存储结果
        for (int i = 0; i < decimal.length();) { // 遍历输入字符串中的每个字符
            int length = Character.getNumericValue(decimal.charAt(i++)); // 读取第一个字符（这个字符表示接下来的整数的长度）
            String number = decimal.substring(i-1, i + length); // 读取接下来的整数
            long num = Long.parseLong(number); // 将这个整数转换为一个长整型数
            String binary = Long.toBinaryString(num); // 将这个长整型数转换为二进制字符串
            result.append(String.format("%02d", binary.length())).append(binary); // 将这个二进制字符串的长度（以两位数字的形式）和这个二进制字符串本身添加到结果中
            i += length; // 移动到下一个整数的位置
        }
        return result.toString(); // 返回结果
    }

    public static String convertBinaryToDecimal(String binary) {
        StringBuilder result = new StringBuilder(); // 创建一个StringBuilder来存储结果
        for (int i = 0; i < binary.length();) { // 遍历输入字符串中的每个字符
            int length = Integer.parseInt(binary.substring(i, i + 2)); // 读取并移除前两个字符（这两个字符表示接下来的二进制字符串的长度）
            i += 2;
            String number = binary.substring(i, i + length); // 读取接下来的二进制字符串
            long num = Long.parseLong(number, 2); // 将这个二进制字符串转换为一个长整型数
            result.append(num); // 将这个长整型数转换为一个十进制字符串，并添加到结果中
            i += length; // 移动到下一个二进制字符串的位置
        }
        return result.toString(); // 返回结果
    }


}
