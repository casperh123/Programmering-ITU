package handin2;

import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.List;

public class FileManager {

    public static Object loadFile(File f) throws IOException {
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))) {
            try {
                return in.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            }
        }
    }

    // NOTE: For any object to be saved, the class and any class incorporated in that class must all implement Serializable.
    public static void save(String filename, Object object) {
        try (var out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        } catch (IOException e) {
            System.out.println("File could not be saved");
        }
    }
}
