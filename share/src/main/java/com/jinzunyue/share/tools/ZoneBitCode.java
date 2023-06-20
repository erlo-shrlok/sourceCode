package com.jinzunyue.share.tools;

import java.io.UnsupportedEncodingException;

public class ZoneBitCode {

    // 将传入的一串汉字转换为区位码
    public static String getCode(String s){
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            try {
                byte[] bytes = ("" + c).getBytes("GB2312");
                String quweiCode = "" + ((bytes[0] & 0xff) - 160) + ((bytes[1] & 0xff) - 160);
                if (quweiCode.length()<4){
                    quweiCode = 0 + quweiCode;
                }
                result += quweiCode;
                System.out.println(c + "的区位码是：" + quweiCode);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 将区位码转为汉字
    public static String deCode(String s){
        if (s.length() % 4 != 0) {
            throw new IllegalArgumentException("Invalid quwei code: length must be a multiple of 4");
        }
        String result = "";
        for (int i = 0; i < s.length(); i += 4) {
            String code = s.substring(i, i + 4);
            try {
                int q = Integer.parseInt(code.substring(0, 2)) + 160;
                int w = Integer.parseInt(code.substring(2, 4)) + 160;
                byte[] bytes = {(byte) (q + 160), (byte) (w + 160)};
                result += new String(bytes, "GB2312");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid quwei code: " + code, e);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
