package pl.kurs.java.service.textService;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.kurs.java.service.TextService;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class ReverseTest {
    TextService underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new TextService();
    }

    @Test
    @Parameters({
            "jubi, ibuj",
            "end, dne",
            ",",
            "a, a",
            "., ."
    })
    public void shouldReverseString(String string, String expected) {
        assertEquals(expected,underTest.reverse(string));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowException() {
        underTest.reverse(null);
    }
}
