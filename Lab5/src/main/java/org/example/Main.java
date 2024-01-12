package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        Polynomial p1 = new Polynomial(100);
        System.out.println("p1=" + p1);
        Polynomial p2 = new Polynomial(100);
        System.out.println("p2=" + p2);

        runAndMeasureDuration(p1, p2, "SEQUENTIAL", "CLASSIC");

        runAndMeasureDuration(p1, p2, "SEQUENTIAL", "KARATSUBA");

        runAndMeasureDuration(p1, p2, "PARALLEL", "CLASSIC");

        runAndMeasureDuration(p1, p2, "PARALLEL", "KARATSUBA");

        System.out.println("All methods executed successfully.");
    }

    private static void runAndMeasureDuration(Polynomial p1, Polynomial p2, String method, String algorithm)
            throws IOException, InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        Polynomial result = run(p1, p2, method, algorithm);
        long endTime = System.nanoTime();
        double duration = ((double) endTime - (double) startTime) / 1_000_000_000.0;

        printAndWriteDuration(method, algorithm, p1.getDegree(), p2.getDegree(), result, duration);
    }

    private static void printAndWriteDuration(String method, String algorithm, int degree1, int degree2, Polynomial result, double duration)
            throws IOException {
        String message = String.format("%s - %s %s and %s: %s seconds, result=%s",
                method, algorithm, degree1, degree2, duration, result);

        BufferedWriter out = new BufferedWriter(new FileWriter("results.txt", true));
        out.write(message);
        out.newLine();
        out.close();

        System.out.println(message);
    }

    private static Polynomial run(Polynomial p1, Polynomial p2, String method, String algorithm)
            throws InterruptedException, ExecutionException {
        Polynomial result;
        switch (method) {
            case "SEQUENTIAL":
                result = ("CLASSIC".equals(algorithm)) ? SequentialClassic.multiply(p1, p2) : SequentialKaratsuba.multiply(p1, p2);
                break;
            case "PARALLEL":
                result = ("CLASSIC".equals(algorithm)) ? ParallelClassic.multiply(p1, p2) : ParallelKaratsuba.multiply(p1, p2);
                break;
            default:
                throw new IllegalArgumentException("Invalid method: " + method);
        }
        return result;
    }
}
