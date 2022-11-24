import java.util.ArrayList;
import java.util.Scanner;

public class RunProgram {

    ArrayList<Movie> movies;
    ArrayList<Serie> series;

    User currentUser;


        public void setup() throws Exception {

            Scanner scan = new Scanner(System.in);
            System.out.println("Do you want to run the program online(type online) or offline(type offline)?");

            String input = scan.nextLine();

            if(input.equalsIgnoreCase("online"))
            {
                this.movies = MovieDB.setupMovieDB();
                this.series = SeriesDB.setupSeriesDB();
                this.run_DB();

            }
            if (input.equalsIgnoreCase("offline")) {

                this.movies = FileIO.setupMovies();
                this.series = FileIO.setupSeries();
                this.run_file();
            }

        }

    public void run_DB() throws Exception {

        String name = TextUI_DB.displayLoginScreen();
        currentUser = new User(name);
        TextUI_DB.displayMainMenu(currentUser);


    }

    public void run_file() throws Exception{

            String name = TextUI_File.displayLoginScreen();
            currentUser = new User(name);
            TextUI_File.displayMainMenu(currentUser);

    }



}