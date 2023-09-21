package domain;

import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ProfileCollectionTest {

    private ProfileCollection profileCollection;

    @BeforeEach
    void setUp() {
        try {
            profileCollection = new ProfileList();
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        profileCollection = null;
    }

    @Test
    void createAndDeleteProfile() {

        String profileName = stringGenerator(20);
        Profile testProfile;

        try {
            profileCollection.createProfile(profileName);
            assert(profileCollection.getProfileMap().containsKey(10123762));
        } catch (FileNotSavedException e) {
            fail(e.getMessage());
        }

        try {
            profileCollection = new ProfileList();
            assert(profileCollection.getProfileMap().containsKey(10123762));
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }

        try {
            profileCollection.deleteProfile(10123762);
            profileCollection = new ProfileList();
            assert(!profileCollection.getProfileMap().containsKey(10123762));
        } catch (FileNotSavedException e) {
            fail(e.getMessage());
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
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