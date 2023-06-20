package com.jinzunyue.share.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 固定洗牌顺序的洗牌算法
 */
public class ShuffleCipher2 {
    private final List<Integer> swapIndices;

    public ShuffleCipher2() {
        // Initialize with a fixed, shuffled swapIndices
        swapIndices = Arrays.asList(2, 5, 18, 13, 21, 19, 8, 24, 0, 1, 17, 23, 4, 7, 14, 22, 10, 6, 26, 20, 11, 15, 9, 3, 25, 16, 12);
    }

    public String shuffle(String input) {
        if (input.length() != swapIndices.size()) {
            throw new IllegalArgumentException("Input length must match cipher length");
        }
        char[] array = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            array[swapIndices.get(i)] = input.charAt(i);
        }
        return new String(array);
    }

    public String unshuffle(String input) {
        if (input.length() != swapIndices.size()) {
            throw new IllegalArgumentException("Input length must match cipher length");
        }
        char[] array = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            array[i] = input.charAt(swapIndices.get(i));
        }
        return new String(array);
    }
}
