package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.KPFinder;
import main.entities.Key_word;
import main.entities.Key_word_problem;
import main.entities.KeywordFinder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;

public class DeleteKeywordController{
    public Button closeButton;

    public TextField keyword;

    public void apply() throws SQLException, IOException {
        if(keyword.getText().equals("")){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Enter keyword please!");
            alert.showAndWait();
            URL fxmlLocation = getClass().getResource("../fxml/delete_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            return;
        }
        Key_word key_word = KeywordFinder.getInstance().findByTitle(keyword.getText().toLowerCase(Locale.ROOT));
        try{
            key_word.delete();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Success removed");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Keyword '" + keyword.getText() + "' doesnt exists.");
            alert.showAndWait();
            closeWindow();
            URL fxmlLocation = getClass().getResource("../fxml/delete_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
