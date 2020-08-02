package pl.kurs.java.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class CalculatorServiceDivideTest {

    //parametryzacja wg JUnitParams dla JUnit 4
    //możliwość kilku testów
    //
    //działa tak jak JUnit5 ???
    //
    //ograniczenia: działa tylko ze stringami i intami w stringach

    CalculatorService underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new CalculatorService();
    }

    @Test
    @Parameters({
            "10, 2, 5",
            "12, 6, 2",
            "90, 10, 9"
    })
    public void shouldDivide(int a, int b, double expected) {
        assertEquals(expected, underTest.divide(a, b), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({
            "12, 0",
            "0, 0"
    })
    public void shouldNotAllowedToDivide(int a, int b) {
        underTest.divide(a, b);
    }

}