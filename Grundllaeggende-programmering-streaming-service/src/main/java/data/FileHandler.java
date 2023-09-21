package data;

import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;

import java.io.File;
import java.util.List;

public interface FileHandler {

    List<String> loadFile(File file) throws FileNotLoadedException;
    void saveFile(List<String> saveData, File file) throws FileNotSavedException;
    void saveFileOverwrite(List<String> saveData, File file) throws FileNotSavedException;
    String getImageURL(String title, String mediaType);
}
