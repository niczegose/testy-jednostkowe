package pl.kurs.java.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalculatorServiceAddTest {

    //parametryzacja w JUnit 4
    //słabe rozwiązanie
    //ze względu na przekazywanie parametrów do konstruktora
    //tyko jedna metoda do testów
    //przez co nie można podzielić wyników na te które mają być ok i te które nie mają sie zgadzać

    private final int a;
    private final int b;
    private final int expected;

    private CalculatorService underTest;

    public CalculatorServiceAddTest(int a, int b, int expected) {
        this.a = a;
        this.b = b;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        underTest = new CalculatorService();
    }

    @Test
    public void shouldReturnSumOfTwoNumbers() {
        assertEquals(expected, underTest.add(a, b));
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {1, 2, 3},
                {0, 0, 0},
        });
    }
}