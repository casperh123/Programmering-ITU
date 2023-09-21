package domain;

import data.FileHandler;
import data.FileHandlerImpl;
import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;
import exceptions.MediaAlreadyInArrayException;
import exceptions.MediaNotInArrayException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class ProfileTest {

    private DataHandler dataHandler;
    private FileHandler fileHandler;
    private File testProfileFile;
    private ProfileCollection profileCollection;

    @BeforeEach
    void setUp() {
        dataHandler = DataHandler.getInstance();
        fileHandler = new FileHandlerImpl();
        testProfileFile = new File("lib/profiles/1026245.txt");
        try {
            profileCollection = new ProfileList();
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        dataHandler = null;
        testProfileFile = null;
    }

    @Test
    void removeFromFavoriteList() {

        Profile testProfile = profileCollection.getProfile(10123761);

        try{
            testProfile.addToFavorite("Spider-Man");
            testProfile = new ProfileList().getProfile(10123761);
            assert(testProfile.getFavorites().contains("Spider-Man"));
        } catch (FileNotSavedException e) {
            fail(e.getMessage());
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        } catch (MediaAlreadyInArrayException e) {
            fail(e.getMessage());
        }

        try {
            testProfile.removeFromFavorite("Spider-Man");
            testProfile = new ProfileList().getProfile(10123761);
            assert(!testProfile.getFavorites().contains("Spider-Man"));
        } catch (MediaNotInArrayException e) {
            fail(e.getMessage());
        } catch (FileNotSavedException e) {
            fail(e.getMessage());
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setName() {

        Profile testProfile = profileCollection.getProfile(10123761);
        String randomName = stringGenerator(40);

        testProfile.setName(randomName);

        try {
            testProfile = new ProfileList().getProfile(10123761);
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }
        assert(testProfile.getName().equals(randomName));
    }

    @Test
    void addToFavoriteList() {

        Profile profile = profileCollection.getProfile(10123761);
        List<String> currentFavorites = new ArrayList<>(profile.getFavorites());
        List<String> loadedFavorites = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            String randomMedia = stringGenerator(25);

            currentFavorites.add(randomMedia);

            try {
                profile.addToFavorite(randomMedia);
            } catch (MediaAlreadyInArrayException e) {
                fail(e.getMessage());
            } catch (FileNotSavedException e) {
                fail(e.getMessage());
            }
        }

        try {
            profile = new ProfileList().getProfile(10123761);
            loadedFavorites = profile.getFavorites();
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }

        for(String favorite : currentFavorites) {
            assert(loadedFavorites.contains(favorite));
        }
    }

    private String stringGenerator(int size) {

        String randomString = "";
        String lowercaseAlphabet = "abcdefghijklmn";

        for (int i = 0; i < size; i++) {
            int index = (int)(lowercaseAlphabet.length() * Math.random());
            randomString += lowercaseAlphabet.charAt(index);
        }

        return randomString;

    }
}