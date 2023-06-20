package com.jinzunyue.share.tools;

import com.jinzunyue.share.entity.MyCode;
import java.util.ArrayList;
import java.util.List;

public class enCodeUtil {

    public static List<String> Code(MyCode code){
        NumberStringEncrypter cipher = new NumberStringEncrypter();
        List<String> codeList = new ArrayList<>();
        String original = code.getArea()+code.getKind()+code.getDate()+code.getType();
        for(int i = 0;i<code.getN();i++){
            // 生成标识个体的随机数
            String random = RandomUtil.randomNumeric(5);
            String shuffled = cipher.encrypt(original+random);
            codeList.add(shuffled);
        }
        return codeList;
    }

    public static MyCode DeCode(String num){
        NumberStringEncrypter cipher = new NumberStringEncrypter();
        String unshuffled = cipher.decrypt(num);
        // 从码中获取信息
        MyCode code = new MyCode();
        code.setArea(unshuffled.substring(0,6));
        code.setKind(unshuffled.substring(6,10));
        code.setDate(unshuffled.substring(10,16));
        code.setType(unshuffled.substring(16,17));
        return code;
    }

    /**
     * 凯撒密码
     * @param input
     * @param offset
     * @return
     */
    // Shifts each character in the given string to the right by the given offset.
    public static String encrypt(String input, int offset) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            char shifted = (char) ((c - '0' + offset) % 10 + '0');
            encrypted.append(shifted);
        }
        return encrypted.toString();
    }

    // Shifts each character in the given string to the left by the given offset.
    public static String decrypt(String input, int offset) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            char shifted = (char) ((c - '0' - offset + 10) % 10 + '0');
            decrypted.append(shifted);
        }
        return decrypted.toString();
    }
}
