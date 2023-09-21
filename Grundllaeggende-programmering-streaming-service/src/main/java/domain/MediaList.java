package domain;

import exceptions.FileNotLoadedException;
import exceptions.MediaNotInArrayException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MediaList implements MediaCollection {

    private List<Media> media;
    private String genre;

    public MediaList() throws FileNotLoadedException {
        media = DataHandler.getInstance().assembleMediaList();
        genre = null;
    }

    private MediaList(String genre, List<Media> media) {
        this.media = media;
        this.genre = genre;
    }

    private MediaList(List<Media> media) {
        this.media = media;
        this.genre = null;
    }

    public MediaCollection getCollectionByGenre(String genre){

        List<Media> listOfMedia = new ArrayList<>();
        // For the object "media" of type Media in the list "media", where the list is a field for MediaCollection
        for (Media media : media) {
            if (media.getGenres().contains(genre)) {
                listOfMedia.add(media);
            }
        }
        return new MediaList(genre, listOfMedia);
    }
    //TODO did we not have a method like this one, but for single strings?
    public MediaCollection getCollectionByName(List<String> chosenMedia) {
        List<Media> listOfMedia = new ArrayList<>();

        for (Media media : media) {
            for (String title : chosenMedia) {
                if (media.getTitle().equals(title)) {
                    listOfMedia.add(media);
                }
            }
        }
        return new MediaList(listOfMedia);
    }
    public Media getMediaByName(String chosenMedia) throws MediaNotInArrayException {

        for(Media media : media) {
            if(media.getTitle().equals(chosenMedia)) {
                return media;
            };
        }
        throw new MediaNotInArrayException(chosenMedia);
    }
    public MediaCollection getCollectionByType(String mediaType) throws IOException {
        List<Media> listOfMedia;
        switch (mediaType) {
            case "Movies" -> listOfMedia = DataHandler.getInstance().assembleMovieList();
            case "Series" -> listOfMedia = DataHandler.getInstance().assembleSeriesList();
            default -> {
                listOfMedia = DataHandler.getInstance().assembleMediaList();
                throw new IllegalArgumentException();
            }
        }
        return new MediaList(listOfMedia);
    }
    public void sortByRating() {
        media.sort(Comparator.comparing(Media::getRating).reversed());
    }
    public void sortByRatingReverse() {
        media.sort(Comparator.comparing(Media::getRating));
    }
    public void sortByReleaseYear() {
        media.sort(Comparator.comparing(Media::getReleaseYear));
    }
    public void sortByReleaseYearReverse() {
        media.sort(Comparator.comparing(Media::getReleaseYear).reversed());
    }
    public void sortByAlphabetical() {
        media.sort(Comparator.comparing(Media::getTitle));
    }
    public void sortByAlphabeticalReverse() {
        media.sort(Comparator.comparing(Media::getTitle).reversed());
    }
    public List<Media> getMedia() {
        return media;
    }
    public String getGenre() {
        return genre;
    }
}
