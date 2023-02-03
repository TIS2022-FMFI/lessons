package main.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.entities.Key_word;
import main.entities.KeywordFinder;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class KeywordsController implements Initializable {
    public VBox keywordList;
    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Key_word> keywords = KeywordFinder.getInstance().findAll();
            keywords.sort(Comparator.comparing(Key_word::getTitle));
            keywordList.getChildren().clear();
            for (Key_word k: keywords) {
                Text key = new Text(k.getTitle());
                if(k.getPrime()) key.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                else key.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                keywordList.getChildren().add(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
