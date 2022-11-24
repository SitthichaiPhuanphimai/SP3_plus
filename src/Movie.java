import java.util.ArrayList;

public class Movie extends Media {
    private String name;

    private int releaseYear;

    private String genre;
    private float rating;


    public Movie(String name, int releaseYear, String genre, float rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;

    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "'\n Name: " + name + '\n' +
                "Release year: " + releaseYear + '\n' +
                "Genre: " + genre + '\n' +
                "Rating: " + rating + '\n';


    }

}