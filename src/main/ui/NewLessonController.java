package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.entities.*;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.sql.SQLException;

public class NewLessonController implements Initializable {

    @FXML
    ChoiceBox<String> newLessCat, primeKey, notPrimeKey;

    @FXML
    Button newLessSave, newLessDelete, addFile;

    @FXML
    TextField newLessAutor, newLessTitle, newLessKeyWord, newLessFileName, imagePath1, imagePath2;

    @FXML
    HTMLEditor newLessDesc;

    @FXML
    VBox files;
    String image1, image2 = null;

    List<String> savedFiles = new ArrayList<>();

    FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.jpeg", "*.gif");

    /**
     * Loads category, prime keywords and not prime keywords to ChoiceBoxes
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (Category c : CategoryFinder.getInstance().findAll()){
                newLessCat.getItems().add(c.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        primeKey.getItems().add("-- Prime keywords --");
        primeKey.setValue("-- Prime keywords --");

        notPrimeKey.getItems().add("-- Not prime keywords --");
        notPrimeKey.setValue("-- Not prime keywords --");
    }

    /**
     * Open browse window to choose first image to show.
     * It can be chosen only from image formats.
     */
    @FXML
    private void browseImgComputer1() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        fileChooser.setTitle("Open Image1");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            image1 = file.getPath();
            imagePath1.setText(image1);
        } else {
            System.out.println("error");
        }
    }

    /**
     * Remove saved first image
     */
    @FXML
    private void removeImg1() {
        image1 = null;
        imagePath1.setText("");
    }

    /**
     * Open browse window to choose second image to show.
     * It can be chosen only from image formats.
     */
    @FXML
    private void browseImgComputer2() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        fileChooser.setTitle("Open Image2");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            image2 = file.getPath();
            imagePath2.setText(image2);
        } else {
            System.out.println("error");
        }
    }

    /**
     * Remove second saved image
     */
    @FXML
    private void removeImg2() {
        image2 = null;
        imagePath2.setText("");
    }

    /**
     * Opens window to brows computer files to add for problem
     */
    @FXML
    private void browseFileComputer() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String path = file.getPath();
            newLessFileName.setText(path);
            files.getChildren().add(showFile(path));
            savedFiles.add(path);
        } else {
            System.out.println("error");
        }
    }

    /**
     * Add file to problem from path
     */
    @FXML
    private void addFile() {
        String path = newLessFileName.getText();
        if (path != null && !path.trim().isEmpty()) {
            files.getChildren().add(showFile(path));
            savedFiles.add(path);
        }
    }

    /**
     * Save problem on click if category is set
     */
    @FXML
    private void saveButton() {
        Problem problem = new Problem();

        // Title
        problem.setTitle(newLessTitle.getText());

        // Category
        try {
            problem.setCategory_id(CategoryFinder.getInstance().findByTitle(newLessCat.getValue()).getCategory_id());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creating new lesson");
            alert.setHeaderText("Category is not set");
            alert.showAndWait();
            ex.printStackTrace();
            return;
        }

        // Image1
        problem.setImage1(image1);

        // Image2
        problem.setImage2(image2);

        // Description
        problem.setDescription(newLessDesc.getHtmlText());

        // Files
        problem.setPath(String.join(";", savedFiles));

        // Author
        problem.setUser_name(newLessAutor.getText());

        // Created at
        problem.setCreated_at(new java.sql.Date(new Date().getTime()));

        try {
            problem.insert();
            // Keywords
            List<String> newKeywords = new ArrayList<>(Arrays.asList(newLessKeyWord.getText().split(";")));
            for (String key : newKeywords) {
                if (key.isEmpty()) {
                    continue;
                }
                Key_word kw;
                try {
                    kw = KeywordFinder.getInstance().findByTitle(key);
                    if (kw == null) {
                        kw = new Key_word();
                        kw.setTitle(key);
                        kw.insert();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Key_word_problem kwp = new Key_word_problem();
                kwp.setProblem_id(problem.getProblem_id());
                kwp.setKey_word_id(kw.getKey_word_id());
                try {
                    kwp.insert();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creating new lesson");
            alert.setHeaderText("New lesson is created successfully");
            alert.showAndWait();
            deleteButton();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creating new lesson");
            alert.setHeaderText("New lesson is not created\nCheck if you are not using diacritic");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    /**
     * Close window
     */
    @FXML
    void deleteButton() {
        Stage stage = (Stage) newLessTitle.getScene().getWindow();
        stage.close();
    }

    /**
     * Create GUI component to show saved file and add remove button next to it for remove from saved files
     * @param fileName absolute path of file
     * @return HBox which contains Textfield with path and Button to remove it
     */
    public HBox showFile(String fileName) {
        TextField file = new TextField(fileName);
        file.setEditable(false);
        file.setPrefSize(700, 60);
        file.setLayoutY(20);
        file.setFont(Font.font("Calibri", FontPosture.ITALIC, 25));
        HBox.setMargin(file, new Insets(0, 0, 0, 30));

        Button remove = new Button("REMOVE");
        remove.setMinSize(50, 50);
        remove.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        remove.setTextAlignment(TextAlignment.CENTER);
        remove.setCursor(Cursor.HAND);
        remove.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #000000; -fx-border-width: 2; -fx-background-color: #FE2C54;");
        HBox.setMargin(remove, new Insets(5, 0, 0, 5));

        HBox output = new HBox(file, remove);
        output.setMinWidth(900);

        remove.setOnAction(e -> {
            files.getChildren().remove(output);
            savedFiles.remove(fileName);
        });
        return output;
    }

    /**
     * On category change load keywords
     */
    @FXML
    private void getKeys() {
        primeKey.getItems().clear();
        primeKey.getItems().add("-- Prime keywords --");
        primeKey.setValue("-- Prime keywords --");

        notPrimeKey.getItems().clear();
        notPrimeKey.getItems().add("-- Not prime keywords --");
        notPrimeKey.setValue("-- Not prime keywords --");
        try {
            for (Key_word kw: KeywordFinder.getInstance().findAll()) {
                if (kw.getPrime()) {
                    primeKey.getItems().add(kw.getTitle());
                }
                else {
                    notPrimeKey.getItems().add(kw.getTitle());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * If prime keyword is chosen from ChoiceBox, add it to keywords Textfield
     */
    @FXML
    private void setPrime() {
        if (primeKey.getValue() == null || primeKey.getValue() == "-- Prime keywords --") {
            return;
        }
        String text = newLessKeyWord.getText();
        if (text == null || text.isEmpty()) {
            newLessKeyWord.setText(primeKey.getValue());
        }
        else {
            newLessKeyWord.setText(text + ";" + primeKey.getValue());

        }
    }

    /**
     * If not prime keyword is chosen from ChoiceBox, add it to keywords Textfield
     */
    @FXML
    private void setNotPrime() {
        if (notPrimeKey.getValue() == null || notPrimeKey.getValue() == "-- Not prime keywords --") {
            return;
        }
        String text = newLessKeyWord.getText();
        if (text == null || text.isEmpty()) {
            newLessKeyWord.setText(notPrimeKey.getValue());
        }
        else {
            newLessKeyWord.setText(text + ";" + notPrimeKey.getValue());

        }
    }
}
