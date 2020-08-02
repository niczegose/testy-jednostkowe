package pl.kurs.java.lessons;

import java.util.ArrayList;
import java.util.List;

public class Matka {
    private int id;
    private String imie;
    private int wiek;
    private List<Noworodek> listaDzieci=new ArrayList<>();

    public Matka(int id, String imie, int wiek) {
        this.id = id;
        this.imie = imie;
        this.wiek = wiek;
    }

    public Matka(String[] wyrazy){
        this.id = Integer.parseInt(wyrazy[0]);
        this.imie = wyrazy[1];
        this.wiek = Integer.parseInt(wyrazy[2]);
    }

    public void addDziecko(Noworodek dziecko){
        listaDzieci.add(dziecko);
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public int getWiek() {
        return wiek;
    }

    public List<Noworodek> getListaDzieci() {
        return listaDzieci;
    }

    @Override
    public String toString() {
        return "Matka{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", wiek=" + wiek +
                '}';
    }
}
