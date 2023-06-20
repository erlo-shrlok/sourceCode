package com.jinzunyue.share.tools;

import java.util.*;

public class OriginCode4 {

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


        String r1 = lastDigit;

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

    public static void printCode(HuffmanNode root, String s, Map<Character, String> huffmanCode) {
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.c, s);
            return;
        }
        printCode(root.left, s + "0", huffmanCode);
        printCode(root.right, s + "1", huffmanCode);
    }

    public static void huff(){
        String str = "010100010002022120112000122102002003020120";

        // count frequency of each character
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!freq.containsKey(str.charAt(i))) {
                freq.put(str.charAt(i), 0);
            }
            freq.put(str.charAt(i), freq.get(str.charAt(i)) + 1);
        }

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(freq.size(), new MyComparator());

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = entry.getKey();
            hn.data = entry.getValue();
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }

        Map<Character, String> huffmanCode = new HashMap<>();
        printCode(root, "", huffmanCode);

        System.out.println("Huffman Codes are :");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("Compressed string is:");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            s.append(huffmanCode.get(str.charAt(i)));
        }
        System.out.println(s);
    }
}
