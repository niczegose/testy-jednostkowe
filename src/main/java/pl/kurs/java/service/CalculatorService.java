package pl.kurs.java.service;

public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }

    //subtract, multiply, divide
    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("B cannot be 0!");
        }
        return (double)a / b;

    }
}
