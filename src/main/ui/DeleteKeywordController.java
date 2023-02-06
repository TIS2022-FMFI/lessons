package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteKeywordController implements Initializable {
    public Button closeButton;
    public ChoiceBox<String> keyword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for(Key_word key_word : KeywordFinder.getInstance().findAll()){
                keyword.getItems().add(key_word.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void apply() throws SQLException, IOException {
        if(keyword.getValue() == null){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Choose keyword, please!");
            alert.showAndWait();
            URL fxmlLocation = getClass().getResource("../fxml/delete_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            return;
        }
        Key_word key_word = KeywordFinder.getInstance().findByTitle(keyword.getValue());
        try{
            key_word.delete();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Keyword successfully removed");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Keyword '" + keyword.getValue() + "' doesnt exists.");
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
