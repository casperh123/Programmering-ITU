package domain;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;

public class Series extends Media{

    private int seasons;
    private Map<Integer, Integer> episodesMap;

    public Series(String title, int releaseYear, List<String> genres, double rating,
                  String posterURL, int seasons, Map<Integer, Integer> episodesInSeason) {

        super(title, releaseYear, genres, rating, posterURL);
        this.seasons = seasons;
        this.episodesMap = episodesInSeason;
    }

    public int getSeasons() {
        return seasons;
    }

    public Map<Integer, Integer> getEpisodesMap() {
        return episodesMap;
    }

    @Override
    public String toString() {

        String outputString = "Series - "+ super.toString();

        for(int i = 1; i <= episodesMap.size(); i ++) {
            if(episodesMap.containsKey(i)) {
                outputString += "Season " + i + ": " + episodesMap.get(i) + " episodes. ";
            }
        }
        return outputString;
    }
}
