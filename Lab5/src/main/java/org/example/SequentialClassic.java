package org.example;

import java.util.ArrayList;
import java.util.List;

public class SequentialClassic {
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        int resultDegree = p1.getDegree() + p2.getDegree();
        List<Integer> coefficients = new ArrayList<>(resultDegree + 1);

        for (int i = 0; i <= resultDegree; i++) {
            coefficients.add(0);
        }

        for (int i = 0; i <= p1.getDegree(); i++) {
            for (int j = 0; j <= p2.getDegree(); j++) {
                coefficients.set(i + j, coefficients.get(i + j) + p1.getCoefficients().get(i) * p2.getCoefficients().get(j));
            }
        }

        return new Polynomial(coefficients);
    }
}
