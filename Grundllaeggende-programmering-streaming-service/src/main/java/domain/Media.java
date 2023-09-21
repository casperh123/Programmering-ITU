package domain;

import javafx.scene.image.Image;

import java.util.List;

public abstract class Media {

    protected String title;
    protected int releaseYear;
    protected List<String> genres;
    protected double rating;
    protected String posterURL;

    public Media(String title, int releaseYear, List<String> genres, double rating, String posterURL) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.rating = rating;
        this.posterURL = posterURL;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public double getRating() {
        return rating;
    }

    public String getPosterURL() {
        return posterURL;
    }

    @Override
    public String toString() {

        String outputString = "";

        outputString += "Title: " + title + ". ";
        outputString += "Year of release: " + releaseYear + ". ";
        outputString += "Genres: ";

        for(String genre : genres) {
            outputString += genre + " ";
        }

        return outputString;
    }
}
