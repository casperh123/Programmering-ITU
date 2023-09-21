package handin2;

import Exceptions.UnsupportedFormatException;
import GUI.App;
import MapObjects.Markers.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DataSingletonTest {

    DataSingleton instance;
    @BeforeEach
    void setUp() throws UnsupportedFormatException {

    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

}