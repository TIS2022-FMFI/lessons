package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.entities.Problem;

import java.net.URL;
import java.sql.SQLException;

public class DeleteLessonController {
    public PasswordField password;
    public Button apply;
    private final Problem problem = LessonController.problemToDelete;

    public void checkPassword(){
        //TODO  šifrovanie!!!!!!!!!!!!!!
        if(password.getText().equals("heslo")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing Lesson");
            alert.setHeaderText("Lesson successfully removed! Please reload main page.");
            alert.showAndWait();
            closeWindow();
            try {
                problem.delete();
                closeWindow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect password");
            alert.setContentText("Try it again");
            alert.showAndWait();
            closeWindow();
            try {
                URL fxmlLocation = getClass().getResource("../fxml/password.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle(problem.getTitle());
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }
}
