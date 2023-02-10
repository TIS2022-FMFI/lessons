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

    /**
     * Load data for editor and editlog from Problem
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Problem problem = LessonController.problemToEdit;
        if (problem.getLast_edited_at() != null) {
            modified.setText(problem.getLast_editor() + "\t" + problem.getLast_edited_at());
        }
        String desc = problem.getEdit_description();
        if (desc == null) {
            desc = "Not edited yet";
        }
        editLog.setText(desc);
    }

    /**
     * On close of window close window
     */
    @FXML
    private void closeWindow(){
        Stage stage = (Stage) editLog.getScene().getWindow();
        stage.close();
    }
}
