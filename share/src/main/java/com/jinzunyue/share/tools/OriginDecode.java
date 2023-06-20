package com.jinzunyue.share.tools;

import java.util.ArrayList;
import java.util.List;

import static com.jinzunyue.share.tools.OriginCode.WEIGHTS;

public class OriginDecode {
    public static String DecryptOriginCode(String encryptedCode) {
        // Step 1: Split the encrypted code into the original sections
        String[] cValues = new String[3];
        cValues[0] = encryptedCode.substring(0, 3);
        cValues[1] = encryptedCode.substring(3, 6);
        cValues[2] = encryptedCode.substring(6, 9);
        String lastDigit = encryptedCode.substring(encryptedCode.length() - 1);

        // Step 2: Calculate the original values for each section
        List<String> sections = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String section = calculateOriginalSection(cValues[i]);
            sections.add(section);
        }

        // Step 3: Combine the sections into a quaternary string
        String quaternary = String.join("", sections);

        // Step 4: Convert the quaternary string back to the original string
        String originalString = convertQuaternaryToString(quaternary);

        // Step 5: Append the last digit to the original string
        originalString += lastDigit;

        return originalString;
    }

    private static String calculateOriginalSection(String cValue) {
        int c = Integer.parseInt(cValue);
        String section = "";
        for (int i = 6; i >= 0; i--) {
            int itemValue = c / WEIGHTS[i];
            c %= WEIGHTS[i];
            // Convert the item value to a 2-digit string
            String itemValueStr = String.format("%02d", itemValue);
            section = itemValueStr + section;
        }
        return section;
    }

    public static String convertQuaternaryToString(String quaternaryString) {
        StringBuilder decimalString = new StringBuilder();
        for (int i = 0; i < quaternaryString.length(); i += 2) {
            String quaternary = quaternaryString.substring(i, i + 2);
            int decimal = Integer.parseInt(quaternary, 4);
            decimalString.append(decimal);
        }
        return decimalString.toString();
    }
}
