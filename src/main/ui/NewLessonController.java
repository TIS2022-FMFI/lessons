package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.entities.Category;
import main.entities.CategoryFinder;
import main.entities.Problem;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewLessonController implements Initializable {

    @FXML
    private ChoiceBox<String> newLessCat;

    @FXML
    private Button newLessSave, newLessDelete;

    @FXML
    private TextField newLessAutor, newLessDesc, newLessTitle, newLessKeyWord, newLessFileName, imagePath, filePath;

    String image1, image2 = null;


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

}
