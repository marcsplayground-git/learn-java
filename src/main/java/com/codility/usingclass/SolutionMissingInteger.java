package com.codility.usingclass;

public class SolutionMissingInteger {
    private int result;

    public SolutionMissingInteger(int[] A) {
        // Implement your solution here
        int n = A.length;

        boolean[] counters = new boolean[n + 1];

        for (int x : A) {
            if (x > 0 && x <= n) {
                counters[x] = true;
            }
        }

        for (int i = 1; i <= n; i++){
            if(!counters[i]) {
                setResult(i);
                return;
            }
        }

        setResult(n + 1);
    }

    public int getResult() {
        return result;
    }

    private void setResult(int r) {
        this.result = r;
    }
}

