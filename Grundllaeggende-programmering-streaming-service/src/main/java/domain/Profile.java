package domain;

import exceptions.FileNotSavedException;
import exceptions.MediaAlreadyInArrayException;
import exceptions.MediaNotInArrayException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private List<String> favorites;
    private int id;
    private DataHandler dataHandler;

    public Profile(int id, String name, List<String> favorites) {
        this.id = id;
        this.name = name;
        this.favorites = favorites;
        this.dataHandler = DataHandler.getInstance();
    }

    public void addToFavorite(String mediaName) throws MediaAlreadyInArrayException, FileNotSavedException {
        if (favorites.contains(mediaName)) {
            throw new MediaAlreadyInArrayException(mediaName);
        } else {
            favorites.add(mediaName);
            dataHandler.saveToProfileFavourites(mediaName, id);
        }
    }

    public void removeFromFavorite(String mediaName) throws MediaNotInArrayException, FileNotSavedException {
        if (favorites.contains(mediaName)) {
            favorites.remove(mediaName);
            dataHandler.saveProfile(this);
        } else {
            throw new MediaNotInArrayException(mediaName);
        }
    }

    public void setName(String name) {

        this.name = name.replaceAll("[^A-Za-z0-9]","");

        try {
            dataHandler.saveProfile(this);
        } catch (IOException e) {
            System.out.println("fuck");
            //TODO proper exception FileNotSaved
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    // Puts profile info into arrayList
    public ArrayList<String> profileInfoFormatter() {

        ArrayList<String> profileInfo = new ArrayList<>();

        profileInfo.add(String.valueOf(getId()));
        profileInfo.add(getName());
        profileInfo.addAll(favorites);

        return profileInfo;
    }

    @Override
    public String toString() {

        String outputString = "Id: " + id + " Name: " + name + " Favorites: ";

        for(String favourite : favorites) {
            outputString += favourite + ", ";
        }

        return outputString;
    }
}