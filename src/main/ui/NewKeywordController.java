package main.ui;

import javafx.fxml.FXML;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.Key_word;
import main.entities.KeywordFinder;

public class NewKeywordController {
    @FXML
    private TextField keyword;

    @FXML
    private Button closeBtn;

    @FXML
    public void apply() throws SQLException {
        Key_word key_word = new Key_word();
        key_word.setTitle(keyword.getText().toLowerCase(Locale.ROOT));
        try{
            key_word.insert();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding keyword");
            alert.setHeaderText("Success added");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Adding keyword");
            alert.setHeaderText("Keyword '" + key_word.getTitle() + "' already exists.");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
