package domain;

import javafx.scene.image.Image;

import java.util.List;

public class Movie extends Media{
    public Movie(String title, int releaseYear, List<String> genres, double rating, String posterURL) {
        super(title, releaseYear, genres, rating, posterURL);
    }

    @Override
    public String toString() {
        return "Movie - " + super.toString();
    }

}
