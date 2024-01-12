package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        int minDegree = Math.min(p1.getDegree(), p2.getDegree());
        int maxDegree = Math.max(p1.getDegree(), p2.getDegree());
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        // Add the two polynomials
        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(p1.getCoefficients().get(i) + p2.getCoefficients().get(i));
        }

        // Add remaining coefficients from the longer polynomial
        if (minDegree != maxDegree) {
            Polynomial longerPolynomial = (maxDegree == p1.getDegree()) ? p1 : p2;
            coefficients.addAll(longerPolynomial.getCoefficients().subList(minDegree + 1, maxDegree + 1));
        }

        return new Polynomial(coefficients);
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        int minDegree = Math.min(p1.getDegree(), p2.getDegree());
        int maxDegree = Math.max(p1.getDegree(), p2.getDegree());
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        // Subtract the two polynomials
        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(p1.getCoefficients().get(i) - p2.getCoefficients().get(i));
        }

        // Add remaining coefficients from the longer polynomial
        if (minDegree != maxDegree) {
            Polynomial longerPolynomial = (maxDegree == p1.getDegree()) ? p1 : p2;
            coefficients.addAll(longerPolynomial.getCoefficients().subList(minDegree + 1, maxDegree + 1));
        }

        removeLeadingZeros(coefficients);

        return new Polynomial(coefficients);
    }

    public static Polynomial addZeros(Polynomial p, int offset) {
        List<Integer> coefficients = new ArrayList<>(Collections.nCopies(offset, 0));
        coefficients.addAll(p.getCoefficients());
        return new Polynomial(coefficients);
    }

    private static void removeLeadingZeros(List<Integer> coefficients) {
        int i = coefficients.size() - 1;
        while (i > 0 && coefficients.get(i) == 0) {
            coefficients.remove(i);
            i--;
        }
    }

}
