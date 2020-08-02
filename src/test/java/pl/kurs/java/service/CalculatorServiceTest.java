package pl.kurs.java.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorServiceTest {

    private CalculatorService underTest;

    @Before
    public void init() {
        underTest = new CalculatorService();
    }

    @Test
    public void shouldAddTwoNumbers() {
        //given
        int a = 5;
        int b = 10;
        //when
        int result = underTest.add(a, b);
        //then
        assertEquals(15, result);
    }

    @Test
    public void shouldSubtractNumbers() {
        int a = 29;
        int b = 2;
        int result = underTest.subtract(a, b);

        assertEquals(27, result);
    }

    @Test
    public void shouldMultiplyNumbers() {
        int a = 5;
        int b = 10;
        int result = underTest.multiply(a, b);
        assertEquals(50, result);
    }

    @Test
    public void shouldDivideNumbers() {
        int a = 10;
        int b = 4;
        double result = underTest.divide(a, b);
        assertEquals(2.5, result, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowedToDivideByZero() {
        int a = 10;
        int b = 0;
        underTest.divide(a, b);
    }

}