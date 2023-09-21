package data;

import exceptions.FileNotLoadedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class FileHandlerImplTest {
    private FileHandler fileHandler;
    private File moviesFile;
    private File seriesFile;
    private File seriesImagePlaceholder;
    private File moviesImagePlaceholder;

    @BeforeEach
    void setUp() {
        this.moviesFile = new File("lib/mediaMetaData/film.txt");
        this.seriesFile = new File("lib/mediaMetaData/serier.txt");
        this.moviesImagePlaceholder = new File("lib/media/filmplakater/Placeholder.jpg");
        this.seriesImagePlaceholder = new File("lib/media/serieforsider/Placeholder.jpg");
        this.fileHandler = new FileHandlerImpl();
    }

    @AfterEach
    void tearDown() {
        moviesFile = null;
        seriesFile = null;
        moviesImagePlaceholder = null;
        seriesImagePlaceholder = null;
        fileHandler = null;

    }

    @Test
    void loadFileContainsAllMedia() {
        List<String> movieList = null;
        List<String> seriesList = null;

        try {
            movieList = fileHandler.loadFile(moviesFile);
            seriesList = fileHandler.loadFile(seriesFile);
        } catch (IOException | IllegalArgumentException e) {
            fail("movieList or seriesList could not be loaded");
        }


        assert(movieList.size() == 100);
        assert(seriesList.size() == 100);
    }

    @Test
    void loadFileNullPointerParameter() {
        assertThrows(FileNotLoadedException.class, () -> fileHandler.loadFile(null));
    }

    @Test
    void loadFileThrowsFileNotFoundExceptionInvalidFile() {
        assertThrows(FileNotLoadedException.class, () -> fileHandler.loadFile(new File("FILE_THAT_DOES_NOT_EXIST")));
    }

    @Test
    void saveFile() {

        File writeTestFile = new File("test/testLib/WriteTest.txt");
        List<String> saveData = new ArrayList<>(Arrays.asList(fillArrayRandomNumberString(5)));
        List<String> comparisonData = new ArrayList<>();
        List<String> fileContentAfterWrite = null;

        try {
            comparisonData = fileHandler.loadFile(writeTestFile);
            comparisonData.addAll(saveData);
        } catch (IOException | IllegalArgumentException e) {
            fail("comparisonData could not be loaded");
        }

        try {
            fileHandler.saveFile(saveData, writeTestFile);
            fileContentAfterWrite = fileHandler.loadFile(writeTestFile);
        } catch (IOException | IllegalArgumentException e) {
            fail("saveData was not saved, or the testWrite file could not be loaded");
        }

        assert(fileContentAfterWrite != null);
        assert(comparisonData.equals(fileContentAfterWrite));
    }

    @Test
    void saveFileOverwrite() {

        File overwriteTestFile = new File("test/testLib/overWriteTest.txt");
        List<String> saveData = new ArrayList<>(Arrays.asList(fillArrayRandomNumberString(200)));
        List<String> comparisonData = null;

        try {
            fileHandler.saveFileOverwrite(saveData, overwriteTestFile);
        } catch(IOException | IllegalArgumentException e) {
            fail("File could not be saved");
        }


        try {
            comparisonData = fileHandler.loadFile(overwriteTestFile);
        } catch (IOException | IllegalArgumentException e) {
            fail("comparisonData could not be loaded");
        }

        assert(comparisonData != null);
        assert(comparisonData.equals(saveData));
    }

    @Test
    /*void getImageReturnsImageMovies() {

        Image poster = fileHandler.getImageURL("12 Angry Men", "film");


        assert(poster != null);

    }

    @Test
    void getImageReturnsImageSeries() {

        Image poster;

        poster = fileHandler.getImageURL("24", "serie");

        assert(poster != null);

    }

   /* @Test
    void getImageNotPlaceholderMovies() {

        Image placeholder = null;
        Image poster = null;


        placeholder = ImageIO.read(moviesImagePlaceholder);
        poster = (BufferedImage) fileHandler.getImageURL("12 Angry Men", "film");


        assert(!imageEquality(poster, placeholder));

    }*/

    //@Test
    /*void getImageNotPlaceholderSeries() {

        BufferedImage placeholder = null;
        BufferedImage poster = null;

        try {
            placeholder = ImageIO.read(seriesImagePlaceholder);
            poster = (BufferedImage) fileHandler.getImageURL("24", "serie");
        } catch(IllegalArgumentException | IOException e) {
            //Uncorrectable error. Placeholder Image is missing.
            fail("Poster or placeholder was not loaded");
        }

        assert(!imageEquality(poster, placeholder));

    }*/

    //@Test
    /*void getImagePlaceholderFallbackMovies() {

        BufferedImage placeholder = null;
        BufferedImage poster = null;

        try {
            placeholder = ImageIO.read(moviesImagePlaceholder);
            poster = (BufferedImage) fileHandler.getImageURL("INVALID_FILE_NAME", "film");
        } catch(IllegalArgumentException | IOException e) {
            //Uncorrectable error. Placeholder Image is missing.
            fail("Placeholder or poster could not be loaded");
        }

        assert(imageEquality(poster, placeholder));
    }*/

    /*@Test
    void getImagePlaceholderFallbackSeries() {

        BufferedImage placeholder = null;
        BufferedImage poster = null;

        try {
            placeholder = ImageIO.read(moviesImagePlaceholder);
            poster = (BufferedImage) fileHandler.getImageURL("INVALID_FILE_NAME", "serie");
        } catch(IllegalArgumentException | IOException e) {
            //Uncorrectable error. Placeholder Image is missing.
            fail("Poster or placeholder could not be loaded");
        }

        assert(imageEquality(poster, placeholder));

    }*/

    /*private boolean imageEquality(BufferedImage first, BufferedImage second) {

        int testWidth = first.getWidth();
        int testHeight = first.getHeight();

        if(testWidth != second.getWidth() && testHeight != second.getHeight()) {
            return false;
        }

        for(int y = 0; y < testHeight; y++) {
            for(int x = 0; x < testWidth; x++) {
                if(first.getRGB(x, y) != second.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }*/

    private String[] fillArrayRandomNumberString(int numbers) {

        String[] array = new String[numbers];

        for(int i = 0; i < numbers; i++) {
            Random random = new Random();
            String randomNumberString = Integer.toString(random.nextInt(100));
            array[i] = randomNumberString;
        }
        return array;
    }

}