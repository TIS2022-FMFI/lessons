package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.entities.Category;
import main.entities.CategoryFinder;
import main.entities.Problem;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewLessonController implements Initializable {

    @FXML
    private ChoiceBox<String> newLessCat;

    @FXML
    private Button newLessSave, newLessDelete, addFile;

    @FXML
    private TextField newLessAutor, newLessDesc, newLessTitle, newLessKeyWord, newLessFileName, imagePath1, imagePath2;

    @FXML
    private VBox files;
    String image1, image2 = null;

    List<String> savedFiles = new ArrayList<>();

    private FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.jpeg");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (Category c : CategoryFinder.getInstance().findAll()){
                newLessCat.getItems().add(c.getTitle());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @FXML
    private void addFile() {
        String path = newLessFileName.getText();
        if (path != null && !path.trim().isEmpty()) {
            files.getChildren().add(showFile(path));
            savedFiles.add(path);
        }
    }

    @FXML
    private void saveButton() {
        newLessSave.setOnAction(e ->{
            Problem problem = new Problem();
            problem.setTitle(newLessTitle.getText());
            problem.setDescription(newLessDesc.getText());
            if (image1 != null){
                problem.setImage1(image1);
            }
            if (image2 != null){
                problem.setImage2(image2);
            }
            try {
                problem.setCategory_id(CategoryFinder.getInstance().findByTitle(newLessCat.getValue()).getCategory_id());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            problem.setUser_name(newLessAutor.getText());
        });
    }

    @FXML
    private void deleteButton() {

    }

    private HBox showFile(String fileName) {
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
}
