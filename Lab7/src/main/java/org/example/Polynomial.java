package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Polynomial {
    private static final int BOUND = 10;
    private List<Integer> coefficients;

    private Integer degree;

    public Polynomial(List<Integer> coefficients) {
        this.coefficients = coefficients;
        this.degree=computeDegree();
    }

    public Polynomial(Integer degree) {
        this.degree=degree;
        this.coefficients=generate();

    }

    public Polynomial(List<Integer> coefficients, Integer degree) {
        this.coefficients=coefficients;
        this.degree=degree;

    }

    public ArrayList<Integer> generate(){
        ArrayList<Integer> coefficients = new ArrayList<>(degree + 1);

        Random randomGenerator = new Random();
        for (int i = 0; i < degree; i++) {
            coefficients.add(randomGenerator.nextInt(BOUND));
        }
        //the coefficient of the biggest power has to be different from  0
        coefficients.add(randomGenerator.nextInt(BOUND) + 1);
        return coefficients;
    }

    public int computeDegree() {
        return this.coefficients.size() - 1;
    }

    public List<Integer> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Integer> coefficients) {
        this.coefficients = coefficients;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int power = getDegree();

        for (int i = getDegree(); i >= 0; i--) {
            str.append(" ").append(coefficients.get(i)).append("x^").append(power);
            if (i > 0) {
                str.append(" +");
            }
            power--;
        }

        return str.toString();
    }
    public Polynomial addZeros(Integer degree){

        List<Integer> coefficients = new ArrayList<>();
        for (int i = 0; i < degree; i++) {
            coefficients.add(0);
        }
        return new Polynomial(coefficients,degree);
    }

}
