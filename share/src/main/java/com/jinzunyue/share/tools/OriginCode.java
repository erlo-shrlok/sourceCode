package com.jinzunyue.share.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OriginCode {

    public static final int[] WEIGHTS = {13, 11, 9, 7, 5, 3,1};
    public static String GenerateOriginCode(String locationCode,String companyName){
        String result = "";
        String zoneBitCode = ZoneBitCode.getCode(companyName);
        result = locationCode + zoneBitCode;

        String allButLast = result.substring(0, result.length() - 1);
        String lastDigit = result.substring(result.length() - 1);

        // Step 1: Convert to quaternary
        String quaternary = convertStringToQuaternary(allButLast);

        // Step 2: Divide into sections
        List<String> sections = divideIntoSections(quaternary);

        // Step 3 & 4: Determine section and calculate C
        String[] cValues = new String[3];
        for (int i = 0; i < 3; i++) {
            cValues[i] = calculateC(sections.get(i));
        }

//        // Step 5: Combine C values and last digit of location code
//        String encrypted = cValues[0] + cValues[1] + cValues[2] + lastDigit;

        // Step 6: Generate checksum
//        int checksum = generateChecksum(cValues[0], cValues[1]);

//        cValues[0] = cValues[0].substring(1);
//        cValues[1] = cValues[1].substring(1);
//        cValues[2] = 2+cValues[2]+3;

        String r1 = cValues[0] + cValues[1] + cValues[2] + lastDigit;

        // 奇偶校验

        return r1;
    }

    public static String convertStringToQuaternary(String decimalString) {
        StringBuilder quaternaryString = new StringBuilder();
        for (int i = 0; i < decimalString.length(); i++) {
            int decimal = Integer.parseInt(String.valueOf(decimalString.charAt(i)));
            String quaternary = Integer.toString(decimal, 4);
            // 如果转换后的四进制数只有一位，前面补0
            if (quaternary.length() == 1) {
                quaternary = "0" + quaternary;
            }
            quaternaryString.append(quaternary);
        }
        return quaternaryString.toString();
    }

    public static List<String> divideIntoSections(String quaternary) {
        // Implement division into sections
        List<String> sections = new ArrayList<>();

        for (int i = 0; i < quaternary.length(); i += 14) {
            String section = quaternary.substring(i, i + 14);
            sections.add(section);
        }

        return sections;
    }
    // 问题代码
    private static String calculateC(String section) {
        int c = 0;
        String cStr = "";
        for (int i = 0; i < 7; i++) {
            int itemValue = Integer.parseInt(section.substring(i * 2, i * 2 + 2));
            c += itemValue * WEIGHTS[i];
        }
        // Convert c to a 3-digit string
        cStr = String.format("%03d", c);
        return cStr;
    }

    private static int generateChecksum(String c1, String c2) {
        // Implement checksum generation
        // 根据描述，我们需要将前两组的区段标识码进行排列组合，共有9种
        Map<String, Integer> map = new HashMap<>();
        map.put("11", 1);
        map.put("12", 2);
        map.put("13", 3);
        map.put("21", 4);
        map.put("22", 5);
        map.put("23", 6);
        map.put("31", 7);
        map.put("32", 8);
        map.put("33", 9);

        // Extract the last digit from each c value to get the section identifier
        String sectionIdentifier1 = c1.substring(c1.length() - 1);
        String sectionIdentifier2 = c2.substring(c2.length() - 1);

        String key = sectionIdentifier1 + sectionIdentifier2;
        return map.getOrDefault(key, -1);
    }
}
