package domain;

import data.FileHandler;
import data.FileHandlerImpl;
import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;
import java.util.*;

public class DataHandler {

    private static DataHandler dataHandler = null;
    private FileHandler fileHandler;
    private File movieList;
    private File seriesList;
    private File profileIds;

    private DataHandler() {
        movieList = new File("lib/mediaMetaData/film.txt");
        seriesList = new File("lib/mediaMetaData/serier.txt");
        profileIds = new File("lib/profiles/profileIds.txt");
        fileHandler = new FileHandlerImpl();
    }

    public List<Media> assembleMediaList() throws FileNotLoadedException {

        List<Media> mediaList = new ArrayList<>();

        mediaList.addAll(assembleMovieList());
        mediaList.addAll(assembleSeriesList());

        return mediaList;
    }

    public List<Media> assembleMovieList() throws FileNotLoadedException {

        List<String> moviesMetaData = fileHandler.loadFile(movieList);
        List<Media> mediaList = new ArrayList<>();

        for(String movie : moviesMetaData) {
            mediaList.add(movieCreator(movie));
        }

        return mediaList;
    }

    public List<Media> assembleSeriesList() throws FileNotLoadedException {

        List<String> seriesMetaData = fileHandler.loadFile(seriesList);
        List<Media> mediaList = new ArrayList<>();

        for(String series : seriesMetaData) {
            mediaList.add(seriesCreator(series));
        }

        return mediaList;
    }

    public Map<Integer, Profile> assembleProfileMap() throws FileNotLoadedException {

        Map<Integer, Profile> profileMap = new HashMap<>();
        //Fetch saved ids from profileIds.txt
        List<String> idsToLoad = fileHandler.loadFile(profileIds);

        //Fetch data from each individual profile file based on given id.
        for (String profileId : idsToLoad) {

            List<String> profileData = fileHandler.loadFile(new File("lib/profiles/" + profileId + ".txt"));

            int id = Integer.parseInt(profileData.get(0));
            String title = profileData.get(1);
            List<String> favourites = profileData.subList(2, profileData.size());

            profileMap.merge(id, new Profile(id, title, favourites), (a, b) -> a = b);

        }
        return profileMap;
    }

    public void saveProfile(Profile profile) throws FileNotSavedException {

        File profilePath = new File("lib/profiles/" + profile.getId() + ".txt");
        List<String> saveData = profile.profileInfoFormatter();

        fileHandler.saveFileOverwrite(saveData, profilePath);
    }

    public void saveProfileMap(Map<Integer, Profile> profileMap) throws FileNotSavedException{

        List<String> saveData = new ArrayList<>();

        for(int id : profileMap.keySet()) {
            Profile profile = profileMap.get(id);
            saveData.add(Integer.toString(profile.getId()));
        }
        fileHandler.saveFileOverwrite(saveData, profileIds);
    }

    public void saveToProfileFavourites(String mediaTitle, int profileId) throws FileNotSavedException {

        File profilePath = new File("lib/profiles/" + profileId + ".txt");

        fileHandler.saveFile(Arrays.asList(mediaTitle), profilePath);

    }

    private Media movieCreator(String data) {
        String[] dataEntries = inputSanitizer(data);
        String title = dataEntries[0];
        int releaseYear = Integer.parseInt(dataEntries[1]);
        // Splits the genre entry into a new array, and saves this in an arrayList called genres
        ArrayList<String> genres = new ArrayList<>(Arrays.asList(dataEntries[2].split(",")));
        double rating = Double.parseDouble(dataEntries[3]);
        String poster = fileHandler.getImageURL(title, "film");

        return new Movie(title, releaseYear, genres, rating, poster);
    }

    private Media seriesCreator(String data) {
        String[] dataEntries = inputSanitizer(data);
        String title = dataEntries[0];
        String[] releasePeriod = dataEntries[1].split("-");
        int releaseYear = Integer.parseInt(releasePeriod[0]);
        ArrayList<String> genres = new ArrayList<>(Arrays.asList(dataEntries[2].split(",")));
        double rating = Double.parseDouble(dataEntries[3]);
        int seasons = dataEntries[4].split(",").length;
        Map<Integer, Integer> seasonsEpisodes = seasonEpisodeMapAssembler(dataEntries[4].split(","));
        String poster = fileHandler.getImageURL(title, "serie");

        return new Series(title, releaseYear, genres, rating, poster, seasons, seasonsEpisodes);
    }

    private String[] inputSanitizer(String input) {

        String[] dataEntries = input.split(";");
        String[] sanitizedArray = new String[dataEntries.length];

        for(int i = 0; i < dataEntries.length; i++) {
            if (i == 0) {
                sanitizedArray[i] = dataEntries[i];
            } else if (i == 3) {
                sanitizedArray[i] = dataEntries[i].replace(',', '.').replace(" ", "");
            } else {
                sanitizedArray[i] = dataEntries[i].replace(" ", "");
            }
        }

        return sanitizedArray;
    }
    private Map<Integer, Integer> seasonEpisodeMapAssembler(String[] input) {

        Map<Integer, Integer> seasonEpisodeMap = new HashMap<>();

        for(String index : input) {

            String[] newArray = index.split("-");

            if(newArray.length >= 2) {
                seasonEpisodeMap.merge(Integer.parseInt(newArray[0]), Integer.parseInt(newArray[1]), (a, b) -> a = b);
            }
        }

        return seasonEpisodeMap;
    }

    public static DataHandler getInstance() {
        if(dataHandler == null) {
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }
}