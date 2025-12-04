package com.codility;

public class BinaryGap {
    public static void main(String[] args) {
        int i = 1041;

        int result = solution(i);

        System.out.println("Result " + result);
    }

    public static int solution(int N) {
        String binary = Integer.toBinaryString(N);

        int maxGap = 0;
        int currentGap = 0;
        boolean counting = false;

        for (char c : binary.toCharArray()) {
            if (c == '1') {
                // Close current gap
                if (counting) {
                    maxGap = Math.max(maxGap, currentGap);
                }
                counting = true; // Start counting after first '1'
                currentGap = 0;  // Reset gap
            } else { // c == '0'
                if (counting) {
                    currentGap++; // Count only if between 1s
                }
            }
        }

        return maxGap;
    }

    public static int solution2(int N) {
        int maxGap = 0;
        int currentGap = -1;

        while (N > 0) {
            if ((N & 1) == 1) {
                if (currentGap > maxGap) {
                    maxGap = currentGap;
                }
                currentGap = 0;
            } else {
                if (currentGap >= 0) {
                    currentGap++;
                }
            }
            N >>= 1;
        }

        return maxGap;
    }
}