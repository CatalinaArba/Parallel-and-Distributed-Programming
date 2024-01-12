package org.example;

public final class Task implements Runnable {
    private final int start;
    private final int end;
    private final Polynomial p1;
    private final Polynomial p2;
    private final Polynomial result;

    public Task(int start, int end, Polynomial p1, Polynomial p2, Polynomial result) {
        this.start = start;
        this.end = end;
        this.p1 = p1;
        this.p2 = p2;
        this.result = result;
    }

    @Override
    public void run() {
        int resultSize = result.getCoefficients().size();

        for (int i = start; i < end && i < resultSize; i++) {
            for (int j = 0; j <= i; j++) {
                int p1Index = j;
                int p2Index = i - j;

                if (p1Index < p1.getCoefficients().size() && p2Index < p2.getCoefficients().size()) {
                    int val = p1.getCoefficients().get(p1Index) * p2.getCoefficients().get(p2Index);
                    result.getCoefficients().set(i, result.getCoefficients().get(i) + val);
                }
            }
        }
    }
}
