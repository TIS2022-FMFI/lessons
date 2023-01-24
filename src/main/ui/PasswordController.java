package main.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.Problem;
import main.entities.ProblemFinder;

import java.sql.SQLException;

public class PasswordController {
    public PasswordField password;
    public Button aply;

    public void checkPassword(){
        //TODO  šifrovanie!!!!!!!!!!!!!!
        if(password.getText().equals("heslo")){
            try {
                Problem problem = ProblemFinder.getInstance().findById(Controller.chosenProblem);
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
            password.setText("");
        }
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) aply.getScene().getWindow();
        stage.close();
    }
}
