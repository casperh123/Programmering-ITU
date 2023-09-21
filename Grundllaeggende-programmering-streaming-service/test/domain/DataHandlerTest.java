package domain;

import data.FileHandler;
import data.FileHandlerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    private DataHandler dataHandler;
    private FileHandler fileHandler;
    private File profileIds;

    @BeforeEach
    void setUp() {
        dataHandler = DataHandler.getInstance();
        fileHandler = new FileHandlerImpl();
        profileIds = new File("src/lib/profiles/profileIds.txt");
    }

    @AfterEach
    void tearDown() {
        dataHandler = null;
        fileHandler = null;
        profileIds = null;
    }

    @Test
    void assembleMediaList() {

        List<Media> mediaList = null;

        try {
            mediaList = dataHandler.assembleMediaList();
        } catch(IOException | IllegalArgumentException e) {
            fail("MediaList could not be assembled");
        }

        assert(mediaList.size() == 200);

    }

    //Done
    @Test
    void assembleMovieList() {

        List<Media> movieList = null;

        try {
            movieList = dataHandler.assembleMovieList();
        } catch(IOException | IllegalArgumentException e) {
            fail("movieList could not be assembled");
        }

        assert(movieList.size() == 100);

    }

    //Done
    @Test
    void assembleSeriesList() {

        List<Media> seriesList = null;

        try {
            seriesList = dataHandler.assembleSeriesList();
        } catch(IOException | IllegalArgumentException e) {
            fail("seriesList could not be assembled");
        }

        assert(seriesList.size() == 100);

    }

    @Test
    void assembleProfileMap() {

        Map<Integer, Profile> profileMap = null;
        int profilesAmount = -1;

        try {
            profilesAmount = fileHandler.loadFile(new File("lib/profiles/profileIds.txt")).size();
            profileMap = dataHandler.assembleProfileMap();
        } catch (IOException e) {
            fail("ProfileMap could not be assembled, or profilesAmount could not be determined");
        }

        assert(profileMap.keySet().size() == profilesAmount );

    }

    @Test
    void saveProfile() {

        int testId = 1026245;
        String profileName = stringGenerator(10);
        List<String> testFavorites = new ArrayList<>();
        List<String> loadedFavorites = null;
        List<String> loadedProfileData = null;

        for (int i = 0; i < 50; i++) {
            testFavorites.add(stringGenerator(15));
        }

        Profile testProfile = new Profile(testId, profileName, testFavorites);

        try {
            dataHandler.saveProfile(testProfile);
            loadedProfileData = fileHandler.loadFile(new File("lib/profiles/1026245.txt"));
        } catch (IOException e) {
            fail("Test profile could not be loaded");
        }

        loadedFavorites = loadedProfileData.subList(2, loadedProfileData.size());

        assertEquals(Integer.parseInt(loadedProfileData.get(0)), testId);
        assertEquals(loadedProfileData.get(1), profileName);
        assert(loadedFavorites.equals(testFavorites));
    }

    @Test
    void saveProfileMap() {

        Map<Integer, Profile> profileMap = null;
        Map<Integer, Profile> comparatorProfileMap = null;
        List<String> addedProfilefavourites = new ArrayList<>();
        Profile addedProfile= new Profile((int) (100000*Math.random()), stringGenerator(10), addedProfilefavourites);

        for (int i = 0; i < 10; i++) {
            addedProfilefavourites.add(stringGenerator(15));
        }

        try {
            profileMap = dataHandler.assembleProfileMap();
        } catch(IOException e) {
            fail("profileMap could not be assembled");
        }

        profileMap.merge(addedProfile.getId(), addedProfile, (a,b) -> a = b);

        try {
            dataHandler.saveProfileMap(profileMap);
            dataHandler.saveProfile(addedProfile);
            comparatorProfileMap = dataHandler.assembleProfileMap();
        } catch (IOException e) {
            fail("Map could not be saved or profileMap could not be assembled");
        }

        for(int profileId : comparatorProfileMap.keySet()) {
            assert(profileMap.containsKey(profileId));
        }
    }

    //TODO Complete test
   /* @Test
    void saveFavoritesToProfile() {

        List<String> saveData = new ArrayList<>;
        List<String> comparisonData = null;

        s

    }*/

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