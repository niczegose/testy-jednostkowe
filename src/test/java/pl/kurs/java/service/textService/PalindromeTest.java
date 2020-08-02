package pl.kurs.java.service.textService;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.kurs.java.service.TextService;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class PalindromeTest {
    TextService underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new TextService();
    }

    @Test
    @Parameters({
            "jaj",
            "a",
            "jajejaj",
            "kamil ślimak",
            "Kobyła ma mały bok"
    })
    public void shouldBePalindrome(String a) {
        assertTrue(underTest.isPalindrome(a));
    }

    @Test
    @Parameters({
            "kij",
            ""
    })
    public void shouldNotBePalindrome(String a) {
        assertFalse(underTest.isPalindrome(a));
    }

    @Test
    public void shouldNotBePalindrome() {
        assertFalse(underTest.isPalindrome(null));
    }
}