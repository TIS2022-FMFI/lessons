package main.ui;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void apply() throws SQLException, IOException {
        if(keyword.getText().equals("")){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creating keyword");
            alert.setHeaderText("Enter keyword please!");
            alert.showAndWait();
            URL fxmlLocation = getClass().getResource("../fxml/new_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            return;
        }
        Key_word key_word = new Key_word();
        key_word.setPrime(true);
        key_word.setTitle(keyword.getText().toLowerCase(Locale.ROOT));
        try{
            key_word.insert();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding keyword");
            alert.setHeaderText("Successfully added");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Adding keyword");
            alert.setHeaderText("Adding new keyword '" + key_word.getTitle() + "' was unsuccessful.\nThis keyword already exists or check if you are not using diacritic");
            alert.showAndWait();
            closeWindow();
            URL fxmlLocation = getClass().getResource("../fxml/new_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
