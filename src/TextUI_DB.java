import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUI_DB {

    static ArrayList<Movie> movies = MovieDB.setupMovieDB();
    static ArrayList<Serie> series = SeriesDB.setupSeriesDB();

    public static String displayLoginScreen() throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to start menu");
        System.out.println("Chose your option");
        System.out.println("- Login(type 'Login')");
        System.out.println("- Create user(type 'Create user')");

        String userInput = scan.nextLine();
        String user = null;

        if (userInput.equalsIgnoreCase("login")) {


            user = UserLogin_DB.run();

        }

        if (userInput.equalsIgnoreCase("create user")) {

            CreateUser_DB.createUser();

        }

        return user;
    }

    public static void displayMainMenu(User currentUser) throws FileNotFoundException, SQLException {
        Scanner scan2 = new Scanner(System.in);
        System.out.println("****************");
        System.out.println("Welcome to DreamStream  !");
        System.out.println("What would you like to do today?: ");
        System.out.println("1. Search for a movie. ");
        System.out.println("2. Search for a Series. ");
        System.out.println("3. Display your watched movies. ");
        System.out.println("4. Display your saved movie ");
        System.out.println("5. Exit. ");
        System.out.println("****************");

        int userInput = scan2.nextInt();

        switch (userInput) {

            case 1:
                System.out.println(MovieDB.searchedMovies());
                movieSelection(currentUser);
                break;

            case 2:
                System.out.println(SeriesDB.searchSeries());
                seriesSelection(currentUser);
                break;
            case 3:
                MovieDB.showMyWatched(currentUser);
                promptEnterKey();
                displayMainMenu(currentUser);
                break;
            case 4:
                MovieDB.showMySaved(currentUser);
                promptEnterKey();
                displayMainMenu(currentUser);
                break;
            case 5:
                System.out.println("Logging out");
                System.out.println("Goodbye");
                System.exit(0);
                break;


        }
    }
        public static void movieSelection (User currentUser) throws FileNotFoundException, SQLException {
            Scanner scan = new Scanner(System.in);
            System.out.println("****************");
            System.out.println("What would you like to do: ");
            System.out.println("1.Search again");
            System.out.println("2.Select media");
            System.out.println("3.return to Main menu");

            int userInput = scan.nextInt();

            switch (userInput) {
                case 1:
                    MovieDB.searchedMovies();
                    promptEnterKey();
                    movieSelection(currentUser);
                    break;
                case 2:
                    Media media = TextUI_DB.getMovie();
                    System.out.println("You have selected" + '\n' + media.getName());

                    mediaFunctions(media, currentUser);
                    break;
                case 3:
                    displayMainMenu(currentUser);
                    break;

            }
    }
            public static void seriesSelection(User currentUser) throws FileNotFoundException, SQLException {
                Scanner scan = new Scanner(System.in);
                System.out.println("****************");
                System.out.println("What would you like to do: ");
                System.out.println("1.Search again");
                System.out.println("2.Select media");
                System.out.println("3.return to Main menu");

                int userInput = scan.nextInt();

                switch (userInput){
                    case 1:
                        FileIO.searchMoviesList(FileIO.setupMovies());
                        promptEnterKey();
                        movieSelection(currentUser);
                        break;
                    case 2:
                        Media media = TextUI_DB.getserie();
                        System.out.println("You have selected"+'\n'+ media.getName());

                        mediaFunctions(media,currentUser);
                        break;
                    case 3:
                        displayMainMenu(currentUser);
                        break;

                }


        }

    public static void mediaFunctions(Media media,User currentUser) throws FileNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("****************");
        System.out.println("1. Play ");
        System.out.println("2. Save media ");
        System.out.println("3. Return Home Page ");
        System.out.println("****************");

        int userInput = scan.nextInt();


        switch (userInput){

            case 1:
                currentUser.play(media);
                MovieDB.SaveWatchedMedia(currentUser,media);

                promptStop(media,currentUser);
                break;
            case 2:
                System.out.println(media.getName()+" has been saved to your list");
                MovieDB.saveMedia(currentUser,media);
                displayMainMenu(currentUser);
                break;
            case 3:
                displayMainMenu(currentUser);
                break;

        }

    }

    public static void promptEnterKey(){
        System.out.println("Press enter to continue");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void promptStop(Media media,User currentUser){
        System.out.println("Press enter to stop media");
        try {
            System.in.read();
            mediaFunctions(media,currentUser);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Media getMovie(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Movie title: ");

        String name = scan.nextLine();

        for (Movie m:movies) {
            if (m.getName().contains(name))
                return m;
        }

        return null;
    }

    public static Media getserie(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Serie title: ");

        String name = scan.nextLine();

        for (Serie s:series) {
            if (s.getName().contains(name))
                return s;
        }

        return null;
    }

}