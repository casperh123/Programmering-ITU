package GUI;

import Exceptions.NoRouteFoundException;
import Exceptions.NoSuchAddressException;
import Exceptions.UnsupportedFormatException;
import MapObjects.Markers.Address;
import PathFinding.TurnDirection;
import handin2.DataSingleton;
import handin2.Settings.DrawSettings;
import handin2.Settings.PathfindingSettings;
import handin2.Settings.Setting;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.io.File;
import java.net.URL;
import java.util.*;

public class MainViewController implements Initializable {

    public MainViewController() {
    }

    // Main pane
    @FXML
    AnchorPane backmostAnchorPane;

    // Search bar components
    @FXML
    Pane navBar;
    @FXML
    TextField searchField;
    @FXML
    Button searchButton;
    @FXML
    ListView<String> listView;

    // Find directions related
    @FXML
    Pane findDirectionsPane;
    @FXML
    Button ruteFinderButton;
    @FXML
    TextField fromTextField;
    @FXML
    ListView<String> fromListView;
    @FXML
    TextField toTextField;
    @FXML
    ListView<String> toListView;
    @FXML
    Button swapToFromTextButton;
    @FXML
    Button hideFindDirectionsButton;
    @FXML
    ToggleButton carButton;
    @FXML
    ToggleButton bicycleButton;
    @FXML
    ToggleButton walkButton;
    @FXML
    Button calculateRoute;

    // Zoom panel and buttons
    @FXML
    VBox zoomPanel;
    @FXML
    Button zoomInButton;
    @FXML
    Button zoomOutButton;
    @FXML
    Button placeMarkerButton;
    @FXML
    ToggleButton changeThemeButton;
    @FXML
    Label zoomLevelLabel;

    // The map object
    @FXML
    private final MapCanvas mapCanvas = new MapCanvas();

    // Extra buttons
    @FXML
    ToggleButton debugButton;
    @FXML
    Button fileSelectorButton;
    @FXML
    TextArea routeGuideTextArea;
    @FXML
    Button clearTextArea;
    @FXML
    Button copyTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Give mapCanvas a reference to this.
        mapCanvas.setMainViewController(this);

        // Set car as default transport type
        PathfindingSettings.setCarMode();
        carButton.setSelected(true);

        // Adding mapCanvas to anchorPane and moving mapCanvas back to make buttons be on top of map
        backmostAnchorPane.getChildren().add(mapCanvas);
        mapCanvas.toBack();

        // Binding components to backMostAnchorPane
        AnchorPane.setBottomAnchor(mapCanvas, 0.);
        AnchorPane.setLeftAnchor(mapCanvas, 0.);
        AnchorPane.setTopAnchor(mapCanvas, 0.);
        AnchorPane.setRightAnchor(mapCanvas, 0.);
        AnchorPane.setRightAnchor(zoomPanel, 10.);
        AnchorPane.setTopAnchor(zoomPanel, 150.);
        AnchorPane.setTopAnchor(fileSelectorButton, 0.);
        AnchorPane.setRightAnchor(fileSelectorButton, 110.);
        AnchorPane.setTopAnchor(debugButton, 0.);
        AnchorPane.setRightAnchor(debugButton, 10.);
        AnchorPane.setLeftAnchor(searchField, 0.);
        AnchorPane.setTopAnchor(searchField, 0.);
        AnchorPane.setTopAnchor(searchButton, 0.);
        AnchorPane.setLeftAnchor(searchButton, searchField.getWidth());
        AnchorPane.setTopAnchor(ruteFinderButton, 0.);
        AnchorPane.setLeftAnchor(ruteFinderButton, 243.);


        // Creating glyphs
        createGlyphOnButton(zoomInButton, "PLUS", 0);
        createGlyphOnButton(zoomOutButton, "MINUS", 0);
        createGlyphOnButton(swapToFromTextButton, "EXCHANGE", 90);
        createGlyphOnButton(carButton, "CAR", 0);
        createGlyphOnButton(hideFindDirectionsButton, "PLUS", 45);
        createGlyphOnButton(placeMarkerButton, "MAP_MARKER", 0);

        carButton.setOnMouseClicked(e -> PathfindingSettings.setCarMode());
        bicycleButton.setOnMouseClicked(e -> PathfindingSettings.setCycleMode());
        walkButton.setOnMouseClicked(e -> PathfindingSettings.setFootMode());


        // Makes it possible to lose focus on textfields by pressing elsewhere
        backmostAnchorPane.setOnMousePressed(event -> {
            if (!searchField.equals(event.getSource())) {
                searchField.getParent().requestFocus();
                listView.setVisible(false);
            }
            if (!fromTextField.equals(event.getSource())) {
                fromTextField.getParent().requestFocus();
                fromListView.setVisible(false);
            }
            if (!toTextField.equals(event.getSource())) {
                toTextField.getParent().requestFocus();
                toListView.setVisible(false);
            }
        });

        // Find route from from-to TextFields
        calculateRoute.setOnMouseClicked(e -> {
            try {
                Address source = MapCanvas.getInstance().getTSTFinalAddress(fromTextField.getText());
                Address destination = MapCanvas.getInstance().getTSTFinalAddress(toTextField.getText());

                if(!Setting.debugMode) {
                    hideRouteFinding();
                }

                int sourceID = mapCanvas.getNearestRoad(source).getID();
                int destinationID = mapCanvas.getNearestRoad(destination).getID();

                long start = System.nanoTime();

                List<TurnDirection> directions = mapCanvas.setRoute(
                        sourceID, destinationID, PathfindingSettings.pathFindingMode());

                setDirections(directions);

                System.out.println((System.nanoTime() - start) / 1000000);
            } catch (NoRouteFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Could not find route between addresses" );
                alert.setContentText(ex.toString());

                alert.showAndWait();
            } catch (NoSuchAddressException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Could not find given address");
                alert.setContentText(ex.toString());

                alert.showAndWait();
            }
        });

        // Put eventListener on TextFields, and hiding listViews while not focused
        // For searchField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.equals(newValue, "")) {
                listView.getItems().clear();
            }
            getAutoCompleteMatches(listView, newValue);
        });
        searchField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue || listView.focusedProperty().getValue()) {
                listView.setVisible(true);
            } else {
                listView.setVisible(false);
            }
        });
        listView.setOnMouseClicked(event -> {
            searchField.setText(listView.getSelectionModel().getSelectedItem());
            listView.setVisible(false);
        });
        // For fromTextField
        fromTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.equals(newValue, "")) {
                fromListView.getItems().clear();
            }
            getAutoCompleteMatches(fromListView, newValue);
        });
        fromTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue || fromListView.focusedProperty().getValue()) {
                fromListView.setVisible(true);
            } else {
                fromListView.setVisible(false);
            }
        });
        fromListView.setOnMouseClicked(event -> {
            fromTextField.setText(fromListView.getSelectionModel().getSelectedItem());
            fromListView.setVisible(false);
        });
        // For toTextField
        toTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clears list if the textfield is empty
            if (Objects.equals(newValue, "")) {
                toListView.getItems().clear();
            }
            getAutoCompleteMatches(toListView, newValue);
        });
        toTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue || toListView.focusedProperty().getValue()) {
                toListView.setVisible(true);
            } else {
                toListView.setVisible(false);
            }
        });
        toListView.setOnMouseClicked(event -> {
            toTextField.setText(toListView.getSelectionModel().getSelectedItem());
            toListView.setVisible(false);
        });

        // File chooser
        fileSelectorButton.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(".\\data"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    reload("data/" + selectedFile.getName());
                } catch (UnsupportedFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Could not find file");
                    alert.setContentText(e.toString());

                    alert.showAndWait();
                }
            }
        });

        // Places a marker on the address that is searched for in the outer searchField
        searchButton.setOnMouseClicked(event -> {
            try {
                Address a = mapCanvas.getTSTFinalAddress(searchField.getText());
                Point2D point = new Point2D(a.getX(), a.getY());
                mapCanvas.placeMarkerNoToggle(point);
            } catch (NoSuchAddressException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Could not find given address");
                alert.setContentText(e.toString());

                alert.showAndWait();
            }

        });

    }

    public void setDirections(List<TurnDirection> directions) {

        copyTextArea.setVisible(true);
        copyTextArea.setDisable(false);
        clearTextArea.setVisible(true);
        clearTextArea.setDisable(false);
        routeGuideTextArea.setVisible(true);
        routeGuideTextArea.setDisable(false);
        routeGuideTextArea.clear();
        routeGuideTextArea.appendText("Route: " + System.lineSeparator());

        for(TurnDirection direction : directions) {
            routeGuideTextArea.appendText(direction + System.lineSeparator());
        }
    

        searchButton.setOnMouseClicked(event -> {
            try {
                Address a = mapCanvas.getTSTFinalAddress(searchField.getText());
                Point2D point = new Point2D(a.getX(), a.getY());
                mapCanvas.placeMarkerNoToggle(point);
            } catch (NoSuchAddressException e) {
                throw new RuntimeException(e.getMessage());
            }

        });
    }

    @FXML
    public void showRouteFinding() {
        navBar.setDisable(true);
        navBar.setVisible(false);
        findDirectionsPane.setVisible(true);
        findDirectionsPane.setDisable(false);

    }

    @FXML
    public void hideRouteFinding() {
        navBar.setDisable(false);
        navBar.setVisible(true);
        findDirectionsPane.setVisible(false);
        findDirectionsPane.setDisable(true);
        routeGuideTextArea.setVisible(false);
        copyTextArea.setVisible(false);
        clearTextArea.setVisible(false);
        mapCanvas.resetRoute();
    }

    // Helper method for constructing glyphs in initialize
    @FXML
    private void createGlyphOnButton(ButtonBase button, String glyphName, int rotation) {
        FontAwesome.Glyph glyph = FontAwesome.Glyph.valueOf(glyphName);
        Glyph glyphType = Glyph.create("FontAwesome|" + glyph.name());
        glyphType.setRotate(rotation);
        button.setGraphic(glyphType);
    }

    @FXML
    private void setTextInputAsTo() {
        toTextField.setText(searchField.getText());
        showRouteFinding();
    }

    @FXML
    private void zoomIn() {
        byte amount = 50;
        mapCanvas.zoom((mapCanvas.getWidth() / 2), (mapCanvas.getHeight() / 2), amount);
    }

    @FXML
    private void zoomOut() {
        byte amount = -50;
        mapCanvas.zoom((mapCanvas.getWidth() / 2), (mapCanvas.getHeight() / 2), amount);
    }

    @FXML
    private void swapToFromText() {
        String placeholder;
        placeholder = fromTextField.getText();
        fromTextField.setText(toTextField.getText());
        toTextField.setText(placeholder);
    }

    @FXML
    private void toggleDebugMode() {
        if (debugButton.isSelected()) {
            debugButton.setTextFill(Paint.valueOf("RED"));
        } else debugButton.setTextFill(Paint.valueOf("BLACK"));
        Setting.toggleDebugMode();
    }

    @FXML
    private void chooseCar() {
        carButton.setSelected(true);
        bicycleButton.setSelected(false);
        walkButton.setSelected(false);
    }

    @FXML
    private void chooseBicycle() {
        bicycleButton.setSelected(true);
        carButton.setSelected(false);
        walkButton.setSelected(false);
    }

    @FXML
    private void chooseWalk() {
        walkButton.setSelected(true);
        carButton.setSelected(false);
        bicycleButton.setSelected(false);
    }

    @FXML
    private void toggleMapMarkerMode() {
        mapCanvas.toggleMapMarkerMode();
    }

    public void updateColorOnMarkerButton() {
        FontAwesome.Glyph glyph4 = FontAwesome.Glyph.MAP_MARKER;
        Glyph markerGlyph = Glyph.create("FontAwesome|" + glyph4.name());
        markerGlyph.color(mapCanvas.mapMarkerMode ? Color.web("red") : Color.web("black"));
        placeMarkerButton.setGraphic(markerGlyph);
    }

    @FXML
    private void DarkThemeMode() {
        DrawSettings.toggleDarkThemeMode();
    }

    @FXML
    private void getAutoCompleteMatches(ListView<String> listView, String newValue) {
        try {
            if (newValue == null || newValue.equals("")) {
                return;
            }
            List<Address> addrList = MapCanvas.getInstance().getTST(newValue);
            List<String> addrStringList = new ArrayList<>();
            for (Address address : addrList) {
                addrStringList.add(address.toString());
            }
            listView.getItems().clear();
            listView.getItems().addAll(addrStringList);
        } catch (NoSuchElementException e) {
            listView.getItems().clear();
            listView.getItems().add("No matches found");
        }
    }

    // Used to reload file from FileChooser
    @FXML
    private void reload(String fileName) throws UnsupportedFormatException {
        Setting.setFileSource(fileName);
        DataSingleton.reloadData();
        mapCanvas.reloadMap();
    }

    @FXML
    private void copyTextArea() {
        routeGuideTextArea.selectAll();
        routeGuideTextArea.copy();
        routeGuideTextArea.deselect();
    }

    @FXML
    private void clearTextArea() {
        routeGuideTextArea.clear();
        clearTextArea.setVisible(false);
        copyTextArea.setVisible(false);
        routeGuideTextArea.setVisible(false);
        mapCanvas.resetRoute();
    }

}