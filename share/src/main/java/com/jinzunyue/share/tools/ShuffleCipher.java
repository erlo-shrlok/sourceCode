package com.jinzunyue.share.tools;

import java.util.*;

public class ShuffleCipher {
    private final List<Integer> swapIndices;

    public ShuffleCipher(int length) {
        // Initialize and shuffle the swapIndices during construction
        swapIndices = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            swapIndices.add(i);
        }
        Collections.shuffle(swapIndices);
        System.out.println(swapIndices);
    }

    public String shuffle(String input) {
        if (input.length() != swapIndices.size()) {
            throw new IllegalArgumentException("Input length must match cipher length");
        }
        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            swap(array, i, swapIndices.get(i));
        }
        return new String(array);
    }

    public String unshuffle(String input) {
        if (input.length() != swapIndices.size()) {
            throw new IllegalArgumentException("Input length must match cipher length");
        }
        char[] array = input.toCharArray();
        // Create an inverse mapping for unshuffling
        int[] inverse = new int[swapIndices.size()];
        for (int i = 0; i < swapIndices.size(); i++) {
            inverse[swapIndices.get(i)] = i;
        }
        // Apply the inverse mapping
        for (int i = 0; i < array.length; i++) {
            swap(array, i, inverse[i]);
        }
        return new String(array);
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
