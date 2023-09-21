package presentation;

import domain.*;
import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;
import exceptions.MediaAlreadyInArrayException;
import exceptions.MediaNotInArrayException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final MediaCollection primaryMediaList;
    private MediaCollection activeMediaList;
    private ProfileCollection profileList;

    public Controller() {
        try {
            primaryMediaList = new MediaList();
            activeMediaList = new MediaList();
            profileList = new ProfileList();
        } catch (FileNotLoadedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button logoButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> sortByComboBox;
    @FXML
    private ComboBox<String> profileComboBox;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private ComboBox<String> mediaComboBox;


    // *** Helper methods ***

    public void sortBy(MediaCollection mediaList, String sortBy) {
        switch(sortBy) {
            case "Sort by" -> {
                redrawMediaPane(mediaPane);
            }
            case "Favorites" -> {
                activeMediaList = primaryMediaList.getCollectionByName(profileList.getActiveProfile().getFavorites());
                redrawMediaPane(mediaPane);
            }
            case "Alphabetical (A-Z)" -> {
                activeMediaList.sortByAlphabetical();
                redrawMediaPane(mediaPane);
            }
            case "Alphabetical (Z-A)" -> {
                activeMediaList.sortByAlphabeticalReverse();
                redrawMediaPane(mediaPane);
            }
            case "Rating (Highest first)" -> {
                activeMediaList.sortByRating();
                redrawMediaPane(mediaPane);
            }
            case "Rating (Lowest first)" -> {
                activeMediaList.sortByRatingReverse();
                redrawMediaPane(mediaPane);
            }
            case "Release year (Newest first)" -> {
                activeMediaList.sortByReleaseYearReverse();
                redrawMediaPane(mediaPane);
            }
            case "Release year (Oldest first)" -> {
                activeMediaList.sortByReleaseYear();
                redrawMediaPane(mediaPane);
            }
        }
    }

    private void clearAndFillMediaList() {
        activeMediaList.getMedia().clear();
        activeMediaList.getMedia().addAll(primaryMediaList.getMedia());
    }

    // *** ActionEvents ***

    EventHandler<ActionEvent> sortByComboHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            switch(sortByComboBox.getValue()) {
                case "Sort by" -> {
                    clearAndFillMediaList();
                    redrawMediaPane(mediaPane);
                }
                case "Favorites" -> {
                    activeMediaList.getMedia().clear();
                    activeMediaList = primaryMediaList.getCollectionByName(profileList.getActiveProfile().getFavorites());
                    redrawMediaPane(mediaPane);
                }
                case "Alphabetical (A-Z)" -> {
                    // clearAndFillMediaList();
                    activeMediaList.sortByAlphabetical();
                    redrawMediaPane(mediaPane);
                }
                case "Alphabetical (Z-A)" -> {
                    clearAndFillMediaList();
                    activeMediaList.sortByAlphabeticalReverse();
                    redrawMediaPane(mediaPane);
                }
                case "Rating (Highest first)" -> {
                    clearAndFillMediaList();
                    activeMediaList.sortByRating();
                    redrawMediaPane(mediaPane);
                }
                case "Rating (Lowest first)" -> {
                    clearAndFillMediaList();
                    activeMediaList.sortByRatingReverse();
                    redrawMediaPane(mediaPane);
                }
                case "Release year (Newest first)" -> {
                    clearAndFillMediaList();
                    activeMediaList.sortByReleaseYearReverse();
                    redrawMediaPane(mediaPane);
                }
                case "Release year (Oldest first)" -> {
                    clearAndFillMediaList();
                    activeMediaList.sortByReleaseYear();
                    redrawMediaPane(mediaPane);
                }
            }
        }
    };
    EventHandler<ActionEvent> genreComboBoxHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            switch(genreComboBox.getValue()) {
                case "All Genres" -> {
                    activeMediaList.getMedia().clear();
                    activeMediaList.getMedia().addAll(primaryMediaList.getMedia());
                    sortBy(activeMediaList, sortByComboBox.getValue());
                    redrawMediaPane(mediaPane);
                }
                default -> {
                    activeMediaList = primaryMediaList.getCollectionByGenre(genreComboBox.getValue());
                    sortBy(activeMediaList, sortByComboBox.getValue());
                    redrawMediaPane(mediaPane);
                }
            }
        }
    };
    EventHandler<ActionEvent> mediaComboBoxHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            switch (mediaComboBox.getValue()) {
                case "All media" -> {
                    activeMediaList.getMedia().clear();
                    activeMediaList.getMedia().addAll(primaryMediaList.getMedia());
                    sortBy(activeMediaList, sortByComboBox.getValue());
                    redrawMediaPane(mediaPane);
                }
                default -> {
                    try {
                        activeMediaList = primaryMediaList.getCollectionByType(mediaComboBox.getValue());
                        sortBy(activeMediaList, sortByComboBox.getValue());
                        redrawMediaPane(mediaPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    };



    //TODO The commented out code doesn't work as intentional. It only clears the pane when used with empty searchField. With any input, it does nothing(Seemingly).
    @FXML
    public void search(MouseEvent event) throws MediaNotInArrayException {
        String searchedName = searchTextField.getText();
        //Set activeMediaList = new collection with all activeMediaList.getMedia().getTitle().contains(searcedName)
        List<String> listOfMedia = new ArrayList<>();
        for (Media media : activeMediaList.getMedia()) {
            if (media.getTitle().contains(searchedName)) {
                listOfMedia.add(media.getTitle());
            }
        }
        activeMediaList = activeMediaList.getCollectionByName(listOfMedia);
        redrawMediaPane(mediaPane);
    }

    @FXML
    public void setDefault(MouseEvent event) {
        if (!(searchTextField == null)) {
            searchTextField.clear();
        }
        sortByComboBox.setValue("Sort by");
        genreComboBox.setValue("All Genres");
        mediaComboBox.setValue("All media");
    }

    @FXML
    private FlowPane mediaPane;


    // Making lists for comboBoxes
    private final String[] sortByOptions = {"Sort by","Favorites", "Alphabetical (A-Z)","Alphabetical (Z-A)",
            "Rating (Highest first)", "Rating (Lowest first)", "Release year (Newest first)", "Release year (Oldest first)"};
    private final String[] profileOptions = {"Change name", "Change profile"};

    //TODO change genres to a HashMap that is filled by looping over all media
    private final String[] genres = new String[]{"All Genres","Action","Adventure","Biography","Comedy","Crime","Drama",
            "Family","Fantasy","Film-Noir","History","Horror","Music","Musical","Mystery","Romance","Sci-Fi",
            "Sport","Talk-Show","Thriller","War","Western"};
    private final String[] mediaTypes = {"All media", "Movies", "Series"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Adding lists to comboBoxes
        sortByComboBox.getItems().addAll(sortByOptions);
        genreComboBox.getItems().addAll(genres);
        mediaComboBox.getItems().addAll(mediaTypes);
        profileComboBox.getItems().addAll(profileOptions);

        sortByComboBox.setValue("Sort by");
        genreComboBox.setValue("All Genres");
        mediaComboBox.setValue("All media");

        //Set Active profile
        profileList.setActiveProfile(1);

        //Add event handlers.
        sortByComboBox.addEventFilter(ActionEvent.ACTION, sortByComboHandler);
        genreComboBox.addEventFilter(ActionEvent.ACTION, genreComboBoxHandler);
        mediaComboBox.addEventFilter(ActionEvent.ACTION, mediaComboBoxHandler);

        //Draw the mediaPane to fill up with most recent mediaList;
        redrawMediaPane(mediaPane);
    }

    //Used to draw the mediaPane on initialization, or whenever the mediaList has been altered in any way.
    public void redrawMediaPane(FlowPane mediaPane) {

        mediaPane.getChildren().clear();

        for(Media media : activeMediaList.getMedia()) {
            mediaPane.getChildren().add(mediaCard(media));
        }
    }

    //Used to generate the cards containing media.
    public VBox mediaCard(Media media) {

        VBox mediaCard = new VBox();
        ImageView mediaPosterWrapper = new ImageView(new Image(media.getPosterURL()));

        mediaCard.setSpacing(10);

        mediaCard.getChildren().add(mediaPosterWrapper);
        mediaCard.getChildren().add(addToFavorites(media));
        mediaCard.setCursor(Cursor.HAND);

        mediaCard.setOnMouseClicked((e) -> {
            singleMediaPane(media);
        });

        return mediaCard;
    }

    public Button addToFavorites(Media media) {
        Button addToFavorites;

        if(profileList.getActiveProfile().getFavorites().contains(media.getTitle())) {
            addToFavorites = new Button("Remove from favorites");
            addToFavorites.setOnMouseClicked((e) -> {
                try {
                    profileList.getActiveProfile().removeFromFavorite(media.getTitle());
                    addToFavorites.setText("Added to favorites");
                    activeMediaList.getMedia().clear();
                    activeMediaList = primaryMediaList.getCollectionByName(profileList.getActiveProfile().getFavorites());
                    redrawMediaPane(mediaPane);
                } catch (MediaNotInArrayException error) {
                    Alert alreadyInFavorites = new Alert(Alert.AlertType.ERROR);
                    alreadyInFavorites.setTitle("Error");
                    alreadyInFavorites.setHeaderText("Could not remove from favorites");
                    alreadyInFavorites.setContentText(error.getMessage());
                    alreadyInFavorites.showAndWait();
                } catch (FileNotSavedException error) {
                    Alert alreadyInFavorites = new Alert(Alert.AlertType.ERROR);
                    alreadyInFavorites.setTitle("Error");
                    alreadyInFavorites.setHeaderText("Could not save favorites to disc");
                    alreadyInFavorites.setContentText(error.getMessage());
                    alreadyInFavorites.showAndWait();
                }
            });
        } else {
            addToFavorites = new Button("Add to favorite");
            addToFavorites.setOnMouseClicked((e) -> {
                try {
                    profileList.getActiveProfile().addToFavorite(media.getTitle());
                    addToFavorites.setText("Added to favorites");
                } catch (MediaAlreadyInArrayException error) {
                    Alert alreadyInFavorites = new Alert(Alert.AlertType.ERROR);
                    alreadyInFavorites.setTitle("Error");
                    alreadyInFavorites.setHeaderText("Could not add to favorites");
                    alreadyInFavorites.setContentText(error.getMessage());
                    alreadyInFavorites.showAndWait();
                } catch (FileNotSavedException error) {
                    Alert alreadyInFavorites = new Alert(Alert.AlertType.ERROR);
                    alreadyInFavorites.setTitle("Error");
                    alreadyInFavorites.setHeaderText("Could not save favorites to disc");
                    alreadyInFavorites.setContentText(error.getMessage());
                    alreadyInFavorites.showAndWait();
                }
            });
        }
        return addToFavorites;
    }

    // Pop up window when clicking on mediaCard
    public void singleMediaPane(Media media) {

        VBox singleMediaPane = new VBox();
        HBox genreBox = new HBox();
        Button playButton = new Button("Play");
        playButton.setStyle("-fx-background-color: green;");

        singleMediaPane.setSpacing(20);
        singleMediaPane.setPrefSize(400, 400);


        singleMediaPane.getChildren().add(new Text(media.getTitle()));
        singleMediaPane.getChildren().add(new ImageView(new Image(media.getPosterURL())));
        singleMediaPane.getChildren().add(new Text("Rating: " + Double.toString(media.getRating())));
        singleMediaPane.getChildren().add(new Text("Release year: " + Integer.toString(media.getReleaseYear())));
        if (media instanceof Series) {
            singleMediaPane.getChildren().add(new Text("Seasons: " + ((Series) media).getEpisodesMap()));
        }
        genreBox.getChildren().add(new Text("Genres: "));
        media.getGenres().forEach((genre) -> genreBox.getChildren().add(new Text(genre + ", ")));

        singleMediaPane.getChildren().add(genreBox);
        singleMediaPane.getChildren().add(playButton);

        playButton.setOnMouseClicked((e) -> {
            if (playButton.getText().equals("Play")) {
                playButton.setText("Stop");
                playButton.setStyle("-fx-background-color: red;");
            } else if (playButton.getText().equals("Stop")) {
                playButton.setText("Play");
                playButton.setStyle("-fx-background-color: green;");
            }
        });

        Scene scene = new Scene(singleMediaPane);
        Stage stage = new Stage();
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

    }

}