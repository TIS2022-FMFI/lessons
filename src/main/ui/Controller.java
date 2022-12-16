package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.entities.Category;
import main.entities.CategoryFinder;
import main.entities.KPFinder;
import main.entities.Key_word;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private VBox box ;

    private List<Button> buttons ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Category> cat = CategoryFinder.getInstance().findAll();
            for (int i = 1; i < cat.size(); i++) {
                addButton(cat.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addButton(Category c){
        Button btn = new Button(c.getTitle());
        btn.setId(c.getCategory_id().toString());
        btn.setDisable(false);

        btn.setOnAction(e -> {

            try {
//                box.getChildren().clear();
                for (Key_word k: KPFinder.getInstance().usedInProblemByCategory(Integer.parseInt(btn.getId()))) {

                    CheckBox check = new CheckBox(k.getTitle());
                    check.getId();

                    box.getChildren().add(check);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        box.getChildren().add(btn);
    }

}
