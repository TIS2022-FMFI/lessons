package main.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.Problem;
import main.entities.ProblemFinder;

import java.sql.SQLException;

public class PasswordController {
    public TextField password;
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
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) aply.getScene().getWindow();
        stage.close();
    }
}
