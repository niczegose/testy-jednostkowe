package pl.kurs.java.service.textService;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.kurs.java.service.TextService;

import java.util.StringTokenizer;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class AnagramTest {
    TextService underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new TextService();
    }

    @Test
    @Parameters({
            "lol, oll",
            "hmm, mhm",
            "halo, ha lo",
            "M, m"
    })
    public void shouldBeAnagram(String src, String toCheck) {
        assertTrue(underTest.isAnagram(src, toCheck));
    }

    @Test
    @Parameters({
            "lool, oll",
    })
    public void shouldNotBeAnagram(String src, String toCheck) {
        System.out.println(src.length() + " oraz: " + toCheck.length());
        assertFalse(underTest.isAnagram(src, toCheck));
    }

    @Test
    public void shouldNotBeAnagram() {
        assertFalse(underTest.isAnagram(" ", " "));
        assertFalse(underTest.isAnagram("hey", null));

    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowException(){
        underTest.isAnagram(null, "kijanka");
    }
}