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

    /**
     * Load all keywords to ChoiceBox for choose to delete
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for(Key_word key_word : KeywordFinder.getInstance().findAllbyPrime(true)){
                keyword.getItems().add(key_word.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete keyword when it´s clicked on button
     * @throws SQLException
     * @throws IOException
     */
    public void apply() throws SQLException, IOException {
        if(keyword.getValue() == null){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing prime keyword");
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
            System.out.println(key_word.getTitle() + key_word.getKey_word_id() + key_word.getPrime());
            key_word.setPrime(false);
            key_word.update();
            System.out.println(key_word.getPrime());
            Key_word pom = KeywordFinder.getInstance().findById(key_word.getKey_word_id());
            System.out.println(pom.getPrime());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing prime keyword");
            alert.setHeaderText("Keyword '" + key_word.getTitle() + "' successfully change to non-prime");
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

    /**
     * On close of window close window
     */
    @FXML
    private void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
