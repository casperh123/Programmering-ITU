package exceptions;

import java.io.File;
import java.io.IOException;

public class FileNotSavedException extends IOException {

    private File file;

    public FileNotSavedException(File file) {
        this.file = file;
    }

    public FileNotSavedException(String message) {
        super(message);
    }

    public String getMessage() {
        return file.getName() + " could not be loaded";
    }

}
