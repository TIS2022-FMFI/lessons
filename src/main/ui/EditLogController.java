package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.entities.Problem;

import java.net.URL;
import java.util.ResourceBundle;

public class EditLogController implements Initializable {
    @FXML
    private Text editLog;

    @FXML
    private Label modified;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Problem problem = LessonController.problemToEdit;
        modified.setText(problem.getLast_editor() + "\t" + problem.getLast_edited_at());
        String desc = problem.getEdit_description();
        if (desc == null) {
            desc = "";
        }
        editLog.setText(desc);
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) editLog.getScene().getWindow();
        stage.close();
    }
}
