package pl.kurs.java.service.textService;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.java.service.TextService;

import static org.junit.Assert.*;

public class TextServiceTest {

    private TextService underTest;

    @Before
    public void init(){
        underTest = new TextService();
    }

    @Test
    public void shouldReverseSource(){
        String a="jutro";
        String b="a";
        String result = underTest.reverse(a);
        assertEquals("ortuj",result);
        result = underTest.reverse(b);
        assertEquals("a",result);
        result = underTest.reverse("");
        assertEquals("",result);
    }

    @Test
    public void checkPalindrome(){
        String a="kolok";
        boolean result = underTest.isPalindrome(a);
        assertTrue(result);
    }

    @Test
    public void checkAnagram(){
        String a="coś";
        String b="ktos";
        boolean result =underTest.isAnagram(a,b);
        assertFalse(result);
    }

}