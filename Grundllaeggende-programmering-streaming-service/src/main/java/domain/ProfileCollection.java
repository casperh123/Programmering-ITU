package domain;

import exceptions.FileNotSavedException;

import java.util.Map;

public interface ProfileCollection {
    void createProfile(String name) throws FileNotSavedException;
    void deleteProfile(int id) throws FileNotSavedException;
    void setActiveProfile(int id);
    Profile getActiveProfile();
    Map<Integer, Profile> getProfileMap();
    Profile getProfile(int id);
}
