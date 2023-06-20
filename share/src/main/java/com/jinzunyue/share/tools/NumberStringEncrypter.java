package com.jinzunyue.share.tools;

import java.util.Arrays;
import java.util.List;

/**
 * 固定洗牌顺序的洗牌算法，添加校验
 */
import java.util.Arrays;
import java.util.List;

/**
 * 这个类的功能是对一个长度为27的数字字符串进行加密和解密操作，同时添加了校验和来验证数据是否被篡改。具体的操作如下：
 *
 * 加密操作：
 *
 * 首先，检查输入字符串的长度是否为27，不为27则抛出异常。
 * 然后，根据预设的交换索引列表对原字符串进行重新排列，得到加密后的字符串。
 * 接着，计算原字符串中所有数字的和，作为校验和。
 * 最后，将校验和的最后两位数加到加密字符串的末尾，得到最终的加密字符串。
 * 解密操作：
 *
 * 首先，检查输入字符串的长度是否为29，不为29则抛出异常。
 * 然后，从输入字符串末尾提取出校验和，并从输入字符串中移除。
 * 接着，根据预设的交换索引列表对输入字符串进行重新排列，得到解密后的字符串。
 * 然后，计算解密后的字符串中所有数字的和。
 * 最后，验证计算得到的校验和是否与提取出的校验和一致，如果不一致说明输入的数据可能被篡改，抛出异常。
 */
public class NumberStringEncrypter {
    // 交换索引的列表
    private List<Integer> swapIndices;

    // 无参构造函数，初始化一个固定的、乱序的交换索引列表
    public NumberStringEncrypter() {
//        swapIndices = Arrays.asList(2, 5, 18, 13, 21, 19, 8, 24, 0, 1, 17, 23, 4, 7, 14, 22, 10, 6, 26, 20, 11, 15, 9, 3, 25, 16, 12);
        swapIndices = Arrays.asList(2, 5, 18, 13, 21, 19, 8, 0, 1, 17, 4, 7, 14, 10, 6, 20, 11, 15, 9, 3, 16, 12);
    }

    // 加密一串数字字符串
    public String encrypt(String numberString) {
        // 如果输入字符串的长度不是22，抛出异常
        if (numberString.length() != 22) {
            throw new IllegalArgumentException("Input string length must be 22");
        }

        // 使用StringBuilder来构建加密后的字符串
        StringBuilder encrypted = new StringBuilder();
        // 遍历交换索引，将原字符串中对应索引的字符加到加密字符串的末尾
        for (int i : swapIndices) {
            encrypted.append(numberString.charAt(i));
        }

        // 计算校验和，即原字符串中所有数字的和
        int checksum = 0;
        for (char c : numberString.toCharArray()) {
            checksum += Character.getNumericValue(c);
        }

        // 将校验和的最后两位数加到加密字符串的末尾
        encrypted.append(checksum % 100);

        // 返回加密后的字符串
        return encrypted.toString();
    }

    // 解密一串数字字符串
    public String decrypt(String encryptedString) {
        // 如果输入字符串的长度不是24，抛出异常
        if (encryptedString.length() != 24) {
            throw new IllegalArgumentException("Input string length must be 24");
        }

        // 提取出加密字符串末尾的校验和，并从加密字符串中移除它
        String checksumString = encryptedString.substring(22, 24);
        encryptedString = encryptedString.substring(0, 22);

        // 创建一个字符数组来存放解密后的字符
        char[] decrypted = new char[22];
        // 遍历交换索引，将加密字符串中对应索引的字符放到解密字符数组的对应位置
        for (int i = 0; i < swapIndices.size(); i++) {
            decrypted[swapIndices.get(i)] = encryptedString.charAt(i);
        }

        // 计算校验和，即解密后的字符串中所有数字的和
        int checksum = 0;
        for (char c : new String(decrypted).toCharArray()) {
            checksum += Character.getNumericValue(c);
        }

        // 验证校验和，如果校验和的最后两位数不等于提取出的校验和，说明输入的数据可能被篡改，抛出异常
        if (checksum % 100 != Integer.parseInt(checksumString)) {
            throw new IllegalArgumentException("Checksum does not match. The input data may have been tampered with.");
        }

        // 返回解密后的字符串
        return new String(decrypted);
    }
}

