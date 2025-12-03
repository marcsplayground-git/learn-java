package com.codility;

public class MissingInteger {
    public static void main(String[] args) {
        int[] i = {1, 3, 4};

        int result = solution(i);

        System.out.println("Result " + result);
    }

    public static int solution(int[] A) {
        // Implement your solution here
        int n = A.length;

        // We only care about numbers in the range [1..n]
        // because the smallest missing positive will be in [1..n+1]
        boolean[] present = new boolean[n + 1]; // index 0 unused

        // Mark the numbers that are present in the array
        for (int x : A) {
            if (x > 0 && x <= n) {
                present[x] = true;
            }
        }

        // The first index i in [1..n] that is false is our answer
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }

        // If all 1..n are present, then the answer is n+1
        return n + 1;
    }
}
