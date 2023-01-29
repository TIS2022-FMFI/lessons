package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.entities.*;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EditLessonController implements Initializable {
    @FXML
    private ChoiceBox<String> newLessCat;

    @FXML
    private Button newLessSave, newLessDelete;

    @FXML
    private TextField newLessAutor, newLessDesc, newLessTitle, newLessKeyWord, newLessFileName, imagePath, filePath, action;

    String image1, image2 = null;

    private final Problem problem = LessonController.problemToEdit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        action.setText("EDIT LESSON");
        newLessDelete.setText("BACK");
        try {
            for (Category c : CategoryFinder.getInstance().findAll()) {
                newLessCat.getItems().add(c.getTitle());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        newLessTitle.setText(problem.getTitle());
        String category;
        try {
            category = CategoryFinder.getInstance().findById(problem.getCategory_id()).getTitle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        newLessCat.setValue(category);
        List<Key_word> key_words = null;
        try {
            key_words = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String keywordText = "";
        for (Key_word kw : key_words) keywordText += kw.getTitle() + ";";
        if (!keywordText.isEmpty()) keywordText = keywordText.substring(0, keywordText.length() - 1);
        newLessKeyWord.setText(keywordText);
        newLessDesc.setText(problem.getDescription());
        newLessFileName.setText(problem.getPath());
        newLessAutor.setText(problem.getUser_name());
    }

    @FXML
    private void browseImgComputer() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            imagePath.setText(file.getPath());
        } else {
            System.out.println("error");
        }
    }

    @FXML
    private void addImgPath(){
        if (image1 == null){
            image1 = imagePath.getText();
            imagePath.setText("Select another image path");
        }
        else {
            image2 = imagePath.getText();
            System.out.println(image1);
            System.out.println(image2);
        }
    }

    @FXML
    private void saveButton(){
        // TODO update problem in database
    }

    @FXML
    private void deleteButton() {
        Stage stage = (Stage) newLessTitle.getScene().getWindow();
        stage.close();
    }
}
