package pl.kurs.java.lessons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MamyInoworodkiService {
    public static void getMatkiINoworodki(String matkiPath, String nowordokiPath) throws IOException, ParseException {
        //Scanner czytajNoworodki = new Scanner(new File("C:\\Users\\black\\IdeaProjects\\tester\\src\\zajecia03\\noworodki.txt"));
        Scanner czytajNoworodki = new Scanner(new File(nowordokiPath));
        //Scanner czytajMatki = new Scanner((new File("C:\\Users\\black\\IdeaProjects\\tester\\src\\zajecia03\\mamy.txt")));

        //Map<Integer, Matka> matki = Files.lines(Paths.get("C:\\Users\\black\\IdeaProjects\\tester\\src\\zajecia03\\mamy.txt"), Charset.defaultCharset())
        Map<Integer, Matka> matki = Files.lines(Paths.get(nowordokiPath), Charset.defaultCharset())
                .map(line -> line.split(" ")).map(Matka::new).collect(Collectors.toMap(Matka::getId, Function.identity()));

        List<Noworodek> noworodki = new ArrayList<>();
        while (czytajNoworodki.hasNextLine()) {
            String[] linia = czytajNoworodki.nextLine().split("\\s", 7);
            noworodki.add(new Noworodek(Integer.parseInt(linia[0]), linia[1], linia[2], linia[3], Double.parseDouble(linia[4]), Double.parseDouble(linia[5]), matki.get(Integer.parseInt(linia[6]))));
        }
//
//        System.out.println("Najwyższym chłopcem jest: " + nawjwyzszyChlopiec(noworodki).getImie() + ", ma on " + nawjwyzszyChlopiec(noworodki).getWzrost() + "cm wzrostu");
//        System.out.println("Najwyższą dzieczynką jest: " + nawjwyzszaDziewczynka(noworodki).getImie() + ", ma ona " + nawjwyzszaDziewczynka(noworodki).getWzrost() + "cm wzrostu");
//        System.out.println("Najwiecej dzieci urodziło sie w dniu: " + rekordowyDzien(noworodki).getKey() + ", było ich: " + rekordowyDzien(noworodki).getValue());
//        System.out.println("Młode matki z grubymi dziećmi: " + mlodaMatkaGrubeDziecko(noworodki));
//        System.out.println("Dziewczynki które odziedziczyły imie po matce to: ");
//        alikeMother(noworodki).forEach(n -> System.out.println(n.getImie() + ", urodzona: " + n.getDataUrodzenia()));
//        System.out.println("Bliźnięta urodziły się w dniach: " + datyBlizniakow(noworodki));
    }

    public  Map<Integer, Matka> getMatki(String matkiPath) throws IOException {
        if (matkiPath==null){
            return null;
        }
        return Files.lines(Paths.get(matkiPath), Charset.defaultCharset())
                .map(line -> line.split(" ")).map(Matka::new).collect(Collectors.toMap(Matka::getId, Function.identity()));
    }

    public List<Noworodek> getNoworodki(String noworodkiPath, String matkiPath) throws IOException {
        Scanner czytajNoworodki = new Scanner(new File(noworodkiPath));
        Map<Integer, Matka> matki = getMatki(matkiPath);
        List<Noworodek> noworodki = new ArrayList<>();
        while (czytajNoworodki.hasNextLine()) {
            String[] linia = czytajNoworodki.nextLine().split("\\s", 7);
            noworodki.add(new Noworodek(Integer.parseInt(linia[0]), linia[1], linia[2], linia[3], Double.parseDouble(linia[4]), Double.parseDouble(linia[5]), matki.get(Integer.parseInt(linia[6]))));
        }
        return noworodki;
    }

    public static void wczytajMatkDzieciom(List<Noworodek> listaDzieci, List<Matka> listaMatek) {
        listaDzieci.forEach(e -> e.setMama(MamyInoworodkiService.wybierzMatke(e, listaMatek)));
    }

    private static Matka wybierzMatke(Noworodek dziecko, List<Matka> listaMatek) {
        return listaMatek.stream().filter(m -> m.getId() == dziecko.getIdMatki()).findFirst().get();
    }

    public static Noworodek nawjwyzszyChlopiec(List<Noworodek> listaDzieci) {
        return najwyzszeDziecko(listaDzieci, e -> e.getPlec().equals("s"));
    }

    public static Noworodek nawjwyzszaDziewczynka(List<Noworodek> listaDzieci) {
        return najwyzszeDziecko(listaDzieci, e -> e.getPlec().equals("c"));
    }

    public static Noworodek najwyzszeDziecko(List<Noworodek> listaDzieci, Predicate<Noworodek> filtrujPlec) {
        return listaDzieci.stream().filter(filtrujPlec).max((p1, p2) -> Double.compare(p1.getWzrost(), p2.getWzrost())).get();
    }

    public static Map.Entry<String, Long> rekordowyDzien(List<Noworodek> listaDzieci) {
        return listaDzieci.stream().collect(Collectors.groupingBy(Noworodek::getDataUrodzenia, Collectors.counting()))
                .entrySet().stream().max((p1, p2) -> Long.compare(p1.getValue(), p2.getValue())).get();
    }

    public static List<String> mlodaMatkaGrubeDziecko(List<Noworodek> listaDzieci) {
        return listaDzieci.stream().filter(n -> n.getWaga() > 4000 && n.getMama().getWiek() < 25).map(n -> n.getMama().getImie()).collect(Collectors.toList());
    }

    public static List<Noworodek> alikeMother(List<Noworodek> listaDzieci) {
        return listaDzieci.stream().filter(n -> n.getImie().equals(n.getMama().getImie())).collect(Collectors.toList());
    }

    public static Set<String> datyBlizniakow(List<Noworodek> listaDzieci) {
        return listaDzieci.stream().collect(Collectors.groupingBy(Noworodek::getMama, Collectors.toList())).values().stream()
                .map(MamyInoworodkiService::zbiorDatBlizniakow).flatMap(Collection::stream).collect(Collectors.toCollection(TreeSet::new));
    }

    private static Set<String> zbiorDatBlizniakow(Collection<Noworodek> listaDzieci) {
        return listaDzieci.stream().collect(Collectors.groupingBy(Noworodek::getDataUrodzenia, Collectors.counting())).entrySet().stream()
                .filter(e -> e.getValue() == 2).map(Map.Entry::getKey).collect(Collectors.toSet());
    }
}
