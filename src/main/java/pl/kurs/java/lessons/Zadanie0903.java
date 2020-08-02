package pl.kurs.java.lessons;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Zadanie0903 {
    public static void main(String[] args) throws SQLException, ParseException {

        List<String> noworodekList = listAllFromFile("src\\zajecia03\\noworodki.txt");
        List<String> mamyList = listAllFromFile("src\\zajecia03\\mamy.txt");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=UTC", "root", "123456");

        addAllBabies(noworodekList, connection);
        addAllMommies(mamyList, connection);
        babiesLivesMatter(noworodekList, connection);

        connection.close();
    }

    private static List<String> listAllFromFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName), Charset.defaultCharset()).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
            return Collections.emptyList();
        }
    }

    private static void addAllBabies(List<String> babies, Connection connection) throws SQLException, ParseException {
        int countReplay = 0;

        PreparedStatement stmt = connection.prepareStatement("insert into noworodek (id, plec, imie, data_urodzenia, waga, wzrost) values (?, ?, ?, ?, ?, ?)");

        for (String baby : babies) {
            try {
                String[] babyData = baby.split(" ");
                stmt.setInt(1, Integer.parseInt(babyData[0]));
                stmt.setString(2, babyData[1]);
                stmt.setString(3, babyData[2]);
                stmt.setDate(4, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(babyData[3]).getTime()));
                stmt.setInt(5, Integer.parseInt(babyData[4]));
                stmt.setInt(6, Integer.parseInt(babyData[5]));
                stmt.execute();
            } catch (SQLIntegrityConstraintViolationException e) {
                countReplay++;
            }
        }

        stmt.close();
        System.out.println("powtórki nie wpisane do bazy: " + countReplay);
    }

    private static void addAllMommies(List<String> mommies, Connection connection) throws SQLException {
        int countReplay = 0;
        PreparedStatement stmt = connection.prepareStatement("insert into matka (id, imie, wiek) values (?, ?, ?)");

        for (String mommy : mommies) {
            try {
                String[] mommyData = mommy.split(" ");
                stmt.setInt(1, Integer.parseInt(mommyData[0]));
                stmt.setString(2, mommyData[1]);
                stmt.setInt(3, Integer.parseInt(mommyData[2]));
                stmt.execute();
            } catch (SQLIntegrityConstraintViolationException e) {
                countReplay++;
            }
        }

        stmt.close();
        System.out.println("powtórki nie wpisane do bazy: " + countReplay);
    }

    private static void babiesLivesMatter(List<String> babies, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("update noworodek set id_matki=? where id=?");

        for (String baby : babies) {

            String[] babyData = baby.split(" ");
            int babyId = Integer.parseInt(babyData[0]);
            int  babyMommy = Integer.parseInt(babyData[6]);

            stmt.setInt(1, babyMommy);
            stmt.setInt(2, babyId);
            stmt.execute();
        }
        stmt.close();
    }
}
