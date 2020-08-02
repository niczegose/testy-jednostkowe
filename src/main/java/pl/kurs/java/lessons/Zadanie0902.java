package pl.kurs.java.lessons;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Zadanie0902 {
    public static void main(String[] args) throws SQLException {
        /*
        wyexportuj bazie z miastami z krajami w taki sposob aby:
        dla kazdego kraju stworzyc plik <nazwa_kraju>.txt
        w tym kraju bedzie informacja w kazdym wierszu:
        miasto1;populacja
        miasto2;populacja
        ...
        ====suma ludnosci: xyz
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "123456");
        PreparedStatement stmt = connection.prepareStatement("select code, name from country");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            //System.out.println(rs.getString("name")+"\t"+rs.getString("code"));
            String countryStr = rs.getString("name");
            String codeStr = rs.getString("code");

            PreparedStatement ctrStmt = connection.prepareStatement("select name,population from city where countryCode='" + codeStr + "'");
            ResultSet ctrRs = ctrStmt.executeQuery();
            List<String> cityList = new ArrayList<>();

            while (ctrRs.next()) {
                cityList.add(ctrRs.getString("name") + ";" + ctrRs.getString("population"));
            }

            ctrRs.close();
            ctrStmt = connection.prepareStatement("select sum(population) as ilosc from city where countryCode='" + codeStr + "'");
            ctrRs = ctrStmt.executeQuery();
            while (ctrRs.next()) {
                cityList.add("suma ludnosci: " + ctrRs.getString("ilosc"));
            }
            saveDataTo("src\\zajecia10\\" + countryStr + ".txt", cityList);
            cityList.clear();
            ctrRs.close();
            ctrStmt.close();
        }

        rs.close();
        stmt.close();
        connection.close();

    }

    public static void saveDataTo(String filePath, List<String> dataList) {
        try {
            Path out = Paths.get(filePath);
            Files.write(out, dataList, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("problem z zapisem");
        }
    }
}
