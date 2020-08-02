package pl.kurs.java.lessons;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.java.service.CalculatorService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MamyInoworodkiServiceTest {

    //klient - server z połączeniem do bazy danych  <- następne zajęcia

    private MamyInoworodkiService underTest;

    @Before
    public void init() {
        underTest = new MamyInoworodkiService();
    }

    @Test
    public void checkGetNoworodki() throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL res = getClass().getClassLoader().getResource("noworodki.txt");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        URL res2 = getClass().getClassLoader().getResource("mamy.txt");
        File file2 = Paths.get(res2.toURI()).toFile();
        String absolutePath2 = file2.getAbsolutePath();
        //System.out.println(classloader.getResource("noworodki.txt").getFile());
        List<Noworodek> list= underTest.getNoworodki(absolutePath, absolutePath2);
        assertEquals(5, list.size());
    }

    @Test
    public void checkGetMatki() throws URISyntaxException, IOException {
        URL res2 = getClass().getClassLoader().getResource("mamy.txt");
        File file2 = Paths.get(res2.toURI()).toFile();
        String absolutePath2 = file2.getAbsolutePath();
        Map<Integer, Matka> matkaMap = underTest.getMatki(absolutePath2);
        assertEquals(174, matkaMap.size());
    }

    @Test
    public void checkMatki() throws IOException {
        Map<Integer, Matka> matkaMap = underTest.getMatki(null);
        assertNull(matkaMap);
    }


}