package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import main.entities.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private VBox boxCategories;

    @FXML
    VBox lessons;

    private List<Button> buttons ;
    private List<Problem> p;



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

        // show all lesson details
        try {
            p = ProblemFinder.getInstance().findAll();
            for (Problem problem : p) {
                lessons.getChildren().add(LessonDetailGenerator.getInstance().lessonDetail(problem));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

                    boxCategories.getChildren().add(check);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        boxCategories.getChildren().add(btn);
    }

}
