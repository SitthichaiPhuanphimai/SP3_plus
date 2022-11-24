import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SeriesDB {

    public static String url = "jdbc:mysql://mysql11.unoeuro.com:3306/matechsolutions_dk_db";
    public static String userName = "matechsolutions_dk";
    public static String password = "cenyktwx";
    public static ArrayList<Serie> series = new ArrayList();
    public static ArrayList<Serie> searchedSeries = new ArrayList<>();
    public static Connection connection;


    public static ArrayList<Serie> setupSeriesDB() {
        setupConnection();

        String req = "SELECT * FROM series";

        try {
            Statement statement = connection.createStatement();
            statement.execute(req);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String sName = resultSet.getString("title");

                String sReleaseYear = resultSet.getString("year");

                String sGenre = resultSet.getString("genre");

                float sRating = resultSet.getFloat("rating");

                String sSeason = resultSet.getString("seasons");

                Serie s = new Serie(sName, sReleaseYear, sGenre, sRating, sSeason);

                series.add(s);
            }
            return series;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void showSeries() {
        for (Serie s : series) {
            System.out.println("[" + series.indexOf(s) + "] = " + s.toString());
        }
    }

    public static void setupConnection() {
        try {
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Serie> searchSeries() {
        setupConnection();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your search: ");

        String input = scan.nextLine();

        String search = "SELECT * FROM series WHERE title LIKE " + "'" + "%" + input + "%" + "'" + " OR " + "genre LIKE " + "'" + "%" + input + "%" + "'";

        try {

            Statement statement = connection.createStatement();
            statement.execute(search);

            ResultSet res = statement.getResultSet();

            while (res.next()) {
                String sName = res.getString("title");

                String sReleaseYear = res.getString("year");

                String sGenre = res.getString("genre");

                float sRating = res.getFloat("rating");

                String sSeason = res.getString("seasons");

                Serie s = new Serie(sName, sReleaseYear, sGenre, sRating, sSeason);

                searchedSeries.add(s);
            }
            return searchedSeries;

        } catch (SQLException e) {

        }
        return null;
    }
}
