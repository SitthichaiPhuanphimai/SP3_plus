import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class FileIO {

    Scanner scan = new Scanner(System.in);


    public static ArrayList<String> readData(File file)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> data = new ArrayList<>();

            String currentLine = reader.readLine();
            while (currentLine != null) {

                data.add(currentLine);
                currentLine =reader.readLine();

            }
            return data;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void createSavedMedia(String name,Media media) {
        try {
            FileWriter writer = new FileWriter("src/Data/savedMedia_" + name + ".txt", true);
            writer.write(media.getName()+'\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createWatchedMedia(String name,Media media) {
        try {
            FileWriter writer = new FileWriter("src/Data/WatchedMedia_" + name + ".txt", true);
            writer.write(media.getName()+'\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void writeUser(String userName, String password) {
        try {
            FileWriter writer = new FileWriter("src/Data/users.txt", true);
            writer.write(userName + " " + password + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static ArrayList<Movie> setupMovies()
    {
        ArrayList<Movie> movieList = new ArrayList<>();

        File file = new File("src/Data/movie.txt");

        try
        {
            Scanner readMovies = new Scanner(file);

            while (readMovies.hasNextLine())
            {
                String line = readMovies.nextLine();
                String[] values = line.split(";");

                String name = values[0].trim();

                int releaseYear = Integer.parseInt(values[1].trim());

                String genre = values[2].trim();
              /*  ArrayList<String> genre = new ArrayList<>();
                String[] genreArr = values[2].trim().split(",");
                genre.addAll(Arrays.asList(genreArr));
*/
                float rating = Float.parseFloat(values[3].trim().replace(",", "."));

                movieList.add(new Movie(name, releaseYear, genre, rating));
            }
        } catch (IOException e)
        {
            System.out.println("File not found");
        }
        return movieList;
    }

   public static ArrayList<Serie> setupSeries()
    {
        ArrayList<Serie> seriesList = new ArrayList<>();

        File file = new File("src/Data/series.txt");

        try
        {
            Scanner readSeries = new Scanner(file);

            while (readSeries.hasNextLine())
            {
                String line = readSeries.nextLine();
                String[] values = line.split(";");

                String name = values[0].trim();

                String runYear = values[1].trim();

                String genre = values[2].trim();

                float rating = Float.parseFloat(values[3].trim().replace(",", "."));


                String season = values[4].trim();


                seriesList.add(new Serie(name, runYear, genre, rating, season));
            }
        } catch (IOException e)
        {
            System.out.println("File not found");
        }
        return seriesList;
    }




    public static ArrayList<Movie> searchMoviesList(ArrayList<Movie> movieList){

        System.out.println("Enter your a movie title or press enter to display all movies: ");
        Scanner scan = new Scanner(System.in);
        String searchStr = scan.nextLine();
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : movieList)
        {
            if(m.getName().toLowerCase().contains(searchStr.toLowerCase()))
            {
                result.add(m);
                System.out.println("Media nr. "+movieList.indexOf(m)+": "+(m));

                }

            }

        return result;
    }

    public static ArrayList<Serie> searchSeriesList(ArrayList<Serie> serieList)
    {
        System.out.println("Enter a series title or press enter to display all series: ");
        Scanner scan = new Scanner(System.in);
        String searchStr = scan.nextLine();
        ArrayList<Serie> result = new ArrayList<>();

        for (Serie s : serieList)
        {
            if(s.getName().toLowerCase().contains(searchStr.toLowerCase()))
            {
                result.add(s);
                System.out.println(" Media nr. " + serieList.indexOf(s)+ ": " + (s) ); // +1 ved display til user og -1 til når man skal adde index til user savedMovies

            }

        }

        return result;
    }

    public static ArrayList<String> readMyList(String name)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/savedMedia_"+name+".txt"));
            ArrayList<String> data = new ArrayList<>();

            String currentLine = reader.readLine();
            while (currentLine != null) {
                data.add(currentLine);
                currentLine =reader.readLine();
            }
            return data;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> readMyWatched(String name)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/WatchedMedia_"+name+".txt"));
            ArrayList<String> data = new ArrayList<>();

            String currentLine = reader.readLine();
            while (currentLine != null) {
                data.add(currentLine);
                currentLine =reader.readLine();
            }
            return data;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

