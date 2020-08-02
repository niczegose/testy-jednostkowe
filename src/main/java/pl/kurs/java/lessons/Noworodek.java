package pl.kurs.java.lessons;

public class Noworodek {
    private int id;
    private String plec;
    private String imie;
    private String dataUrodzenia;
    private double waga;
    private double wzrost;
    private int idMatki;
    private Matka mama;

    public Noworodek(int id, String plec, String imie, String dataUrodzenia, double waga, double wzrost, Matka matka) {
        this.id = id;
        this.plec = plec;
        this.imie = imie;
        this.dataUrodzenia = dataUrodzenia;
        this.waga = waga;
        this.wzrost = wzrost;
        this.mama = matka;
        this.mama.getListaDzieci().add(this);
    }

    public void setMama(Matka mama) {
        this.mama = mama;
    }

    public String getPlec() {
        return plec;
    }

    public String getImie() {
        return imie;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public double getWaga() {
        return waga;
    }

    public double getWzrost() {
        return wzrost;
    }

    public int getIdMatki() {
        return idMatki;
    }

    public int getId() {
        return id;
    }

    public Matka getMama() {
        return mama;
    }

    @Override
    public String toString() {
        return "Noworodek{" +
                "id=" + id +
                ", plec='" + plec + '\'' +
                ", imie='" + imie + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", waga=" + waga +
                ", wzrost=" + wzrost +
                ", idMatki=" + idMatki +
                '}';
    }
}
