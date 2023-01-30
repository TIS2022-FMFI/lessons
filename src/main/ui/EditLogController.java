package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.entities.Problem;

import java.net.URL;
import java.util.ResourceBundle;

public class EditLogController implements Initializable {
    @FXML
    private Text keyword;

    @FXML
    private Label modified;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Problem problem = LessonController.problemToEdit;
        modified.setText(problem.getLast_editor() + " ~ " + problem.getLast_edited_at());
        keyword.setText(problem.getEdit_description());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) keyword.getScene().getWindow();
        stage.close();
    }
}
