import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class TextUI_File {

   static ArrayList<Movie> movies = FileIO.setupMovies();
   static ArrayList<Serie> series = FileIO.setupSeries();

        public static String displayLoginScreen() throws Exception {

            Scanner scan = new Scanner(System.in);

            System.out.println("Welcome to start menu");
            System.out.println("Chose your option");
            System.out.println("- Login(type 'login')");
            System.out.println("- Create user(type 'Create user')");

            String userInput = scan.nextLine();
            String user = null;

            if (userInput.equalsIgnoreCase("login")) {


                 user = UserLogin_File.login();

            }

            if (userInput.equalsIgnoreCase("create user"))
            {

               CreateUser_File.createUser();

            }

            return user ;


        }

        public static void mediaFunctions(Media media,User currentUser) throws FileNotFoundException {
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
                    FileIO.createWatchedMedia(currentUser.getUserName(),media);

                    promptStop(media,currentUser);
                    break;
                case 2:
                    System.out.println(media.getName()+" has been saved to your list");
                    FileIO.createSavedMedia(currentUser.getUserName(),media);
                    displayMainMenu(currentUser);
                    break;
                case 3:
                    displayMainMenu(currentUser);
                    break;

            }

        }
        //scan = scan.nextline


        public static void displayMainMenu(User currentUser) throws FileNotFoundException {
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

            switch (userInput){

                case 1:
                 FileIO.searchMoviesList(FileIO.setupMovies());
                    promptEnterKey();
                    movieSelection(currentUser);
                    break;

                case 2:
                    FileIO.searchSeriesList(FileIO.setupSeries());
                    promptEnterKey();
                    seriesSelection(currentUser);
                    break;
                case 3:
                    currentUser.displayMyWatced_file();
                    movieSelection(currentUser);
                    break;
                case 4:
                    currentUser.displayMySaved_file();
                    movieSelection(currentUser);
                    break;
                case 5:
                    System.out.println("Logging out");
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;


            }




        }

        public static void movieSelection(User currentUser) throws FileNotFoundException {
            Scanner scan = new Scanner(System.in);
            System.out.println("****************");
            System.out.println("What would you like to do: ");
            System.out.println("1.Search again");
            System.out.println("2.Select media");
            System.out.println("3.return to Main menu");

            int userInput = scan.nextInt();

            switch (userInput) {
                case 1:
                    FileIO.searchMoviesList(FileIO.setupMovies());
                    promptEnterKey();
                    movieSelection(currentUser);
                    break;
                case 2:
                    Media media = movies.get(getMediaNr());
                    System.out.println("You have selected" + '\n' + media);

                    mediaFunctions(media, currentUser);
                    break;
                case 3:
                    displayMainMenu(currentUser);
                    break;

            }

        }
            public static void seriesSelection(User currentUser) throws FileNotFoundException {
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
                        Media media = series.get(getMediaNr());
                        System.out.println("You have selected"+'\n'+ media);

                       mediaFunctions(media,currentUser);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getMediaNr(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the media number: ");

        int movieNr = scan.nextInt();

        return movieNr;
    }
}









