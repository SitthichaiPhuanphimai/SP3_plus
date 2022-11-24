import java.util.ArrayList;

public class Serie extends Media {
    private String name;
    private String runYear;
    private String genre;
    private float rating;

    private String season;



    public Serie(String name, String runYear, String genre, float rating, String season)
    {
        this.name = name;
        this.runYear = runYear;
        this.genre = genre;
        this.rating = rating;
        this.season = season;
    }

    public String getName()
    {
        return name;
    }

    public String getRunYear()
    {
        return runYear;
    }

    public String getGenre()
    {
        return genre;
    }

    public float getRating()
    {
        return rating;
    }

    public String getSeason()
    {
        return season;
    }

    @Override
    public String toString() {
        return "\n Name: " + name + '\n' +
                "Run year: " + runYear + '\n' +
                "Genre: " + genre + '\n' +
                "Rating: " + rating + '\n'+
                "Episode: "+ season+'\n';

    }
}










//

/*
public class Serie
{
    private String name;

    private ArrayList<String> genre;

    int runYear;
    private int episode;
    private int season;
    private float rating;

    public Serie(String name, int runYear, ArrayList<String> genre,float rating,int episode ) {
        this.name = name;
        this.runYear = runYear;
        this.rating = rating;
        this.episode = episode;


    }

    public String getName()
    {
        return name;
    }

    public int getEpisode()
    {
        return episode;
    }

    public int getSeason()
    {
        return season;
    }

    public float getRating()
    {
        return rating;
    }


    @Override
    public String toString()
    {
        return "Serie{" +
                "name='" + name + '\'' +
                ", episode=" + episode +
                ", season=" + season +
                ", rating=" + rating;
    }
}
*/
