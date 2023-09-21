package exceptions;

import java.io.File;
import java.io.IOException;

public class FileNotLoadedException extends IOException{

    private String fileName;

    public FileNotLoadedException(File file) {
        this.fileName = file.getName();
    }
    public FileNotLoadedException(String message) {
        super(message);
    }

    public String getMessage() {
        return fileName + " could not be loaded";
    }
}
