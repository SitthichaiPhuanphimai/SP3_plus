import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MovieDB {

    public static String url = "jdbc:mysql://mysql11.unoeuro.com:3306/matechsolutions_dk_db";
    public static String userName = "matechsolutions_dk";

    public static String password = "cenyktwx";
    public static ArrayList<Movie> moviesDB = new ArrayList();

    public static ArrayList<Movie> searchedMovies = new ArrayList<>();
    public static ArrayList<String> savedMedia = new ArrayList<>();

    public static ArrayList<String> watchedMedia = new ArrayList<>();
    public static Connection connection;


    public static ArrayList<Movie> setupMovieDB() {
        setupConnection();

        String req = "SELECT * FROM movies";

        try {
            Statement statement = connection.createStatement();
            statement.execute(req);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String mName = resultSet.getString("title");

                int mReleaseYear = resultSet.getInt("year");

                String genre = resultSet.getString("genre");

                float mRating = resultSet.getFloat("rating");

                Movie m = new Movie(mName, mReleaseYear, genre, mRating);

                moviesDB.add(m);
            }
            return moviesDB;

        } catch (SQLException e) {
            e.printStackTrace();
        }
//            showMovies();
        return null;
    }


    private static void showMovies() {
        for (Movie m : moviesDB) {
            System.out.println("[" + moviesDB.indexOf(m) + "] = " + m.toString());
        }
    }

    public static void setupConnection() {
        try {
            //connection = DriverManager.getConnection(url, userName, password, );
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Movie> searchedMovies() {
        setupConnection();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your search: ");

        String input = scan.nextLine();

        String search = "SELECT * FROM movies WHERE title LIKE " + "'" + "%" + input + "%" + "'" + " OR " + "genre LIKE " + "'" + "%" + input + "%" + "'";

        try {

            Statement statement = connection.createStatement();
            statement.execute(search);

            ResultSet res = statement.getResultSet();

            while (res.next()) {
                String mName = res.getString("title");

                int mReleaseYear = res.getInt("year");

                String genre = res.getString("genre");

                float mRating = res.getFloat("rating");

                Movie m = new Movie(mName, mReleaseYear, genre, mRating);

                searchedMovies.add(m);
            }
            return searchedMovies;

        } catch (SQLException e) {

        }

        return null;

    }

    public static void saveMedia(User user, Media media) {
        try {

            setupConnection();
            String q = "INSERT INTO Saved(username,media_title) VALUES('" + user.getUserName() + "','" + media.getName() + "')";
            Statement statement = connection.createStatement();
            statement.execute(q);

        } catch (SQLException e) {

        }
    }

    public static void SaveWatchedMedia(User user, Media media)
    {
        try {
            setupConnection();
            String q = "INSERT INTO Watched(username,media_title) VALUES('" + user.getUserName() + "','" + media.getName() + "')";
            Statement statement = connection.createStatement();
            statement.execute(q);
        }catch (SQLException e)
        {

        }

    }

        public static void showMySaved(User user)
        {
            try{

                setupConnection();
                String q = "SELECT * FROM Saved WHERE username = '"+user.getUserName()+"'";
                Statement statement = connection.createStatement();
                statement.execute(q);

                ResultSet res = statement.getResultSet();

                while (res.next())
                {
                    String name = res.getString("media_title");

                    savedMedia.add(name);
                }
                System.out.println(savedMedia);


            } catch (SQLException e)
            {

            }
        }

        public static void showMyWatched(User user)
        {
            try {
                setupConnection();
                String q = "SELECT * FROM Watched WHERE username = '"+user.getUserName()+"'";
                Statement statement = connection.createStatement();
                statement.execute(q);

                ResultSet res = statement.getResultSet();

                while (res.next())
                {
                    String name = res.getString("media_title");

                    watchedMedia.add(name);
                }
                System.out.println(watchedMedia);


            } catch (SQLException e)

            {

            }

        }

}





