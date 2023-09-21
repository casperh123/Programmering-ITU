package domain;

import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class ProfileList implements ProfileCollection {

    private DataHandler dataHandler;

    private Map<Integer, Profile> profileMap;
    private Profile activeProfile;
    private int currentID;

    public ProfileList() throws FileNotLoadedException {
        this.dataHandler = DataHandler.getInstance();
        this.profileMap = dataHandler.assembleProfileMap();
        this.currentID = getMaxID() + 1;
    }

    @Override
    public void createProfile(String name) throws FileNotSavedException {

        Profile newProfile = new Profile(this.currentID ,name, new ArrayList<>());

        //Iterate CurrentID
        currentID++;

        //push new profile to the Map.
        profileMap.merge(newProfile.getId(), newProfile, (a, b) -> a = b);

        //save the profile and newly modified profileMap to disc.
        dataHandler.saveProfile(newProfile);
        dataHandler.saveProfileMap(profileMap);
    }

    @Override
    public void deleteProfile(int id) throws FileNotSavedException {

        File profileFile = new File("lib/profiles/" + id + ".txt");
        //TODO ?Que pasa Exeptioooon?
        if(profileFile.delete()) {
            profileMap.remove(id);
            dataHandler.saveProfileMap(profileMap);
        }
    }
    public void setActiveProfile(int id) {
        activeProfile = profileMap.get(id);
    }
    public Profile getActiveProfile() {
        return activeProfile;
    }
    public Map<Integer, Profile> getProfileMap() {
        return profileMap;
    }

    public Profile getProfile(int id) {
        return profileMap.get(id);
    };

    private int getMaxID() {
        int highestID = Integer.MIN_VALUE;
        for (int ID : profileMap.keySet()) {
            if (ID >= highestID) {
                highestID = ID;
            }
        }
        return highestID;
    }

}
