package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.entities.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    @FXML
   private TextField searchBar;

    @FXML
    private VBox boxCategories;

    @FXML
    private VBox boxKeys;

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

    @FXML
    private void SrcBtn() throws SQLException {
        try{
            String[] keywords = searchBar.getText().split(", ");
            Map<Integer, Integer> problems = new HashMap<>();
            for (String key : keywords) {
                Key_word kw = KeywordFinder.getInstance().findByTitle(key.toLowerCase());
                if (kw != null){
                    for (Problem pr : KPFinder.getInstance().findByKeywordId(kw.getKey_word_id())){
                        if (!problems.containsKey(pr.getProblem_id())){
                            problems.put(pr.getProblem_id(), 1);
                        }
                        else{
                            problems.put(pr.getProblem_id(), problems.get(pr.getProblem_id()) + 1);
                        }
                    }
                }
                else{
                    System.out.println("Keyword " + key + " doesn't exist!!!");
                }

            }
            for(Integer p : problems.keySet()){
                System.out.println(ProblemFinder.getInstance().findById(p).getTitle() +  "     occurred " + problems.get(p) + " times");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    @FXML
    public void pressButton() throws Exception {
        try {
            URL fxmlLocation = getClass().getResource("../fxml/new_lesson.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addButton(Category c){
        Button btn = new Button(c.getTitle());
        btn.setId(c.getCategory_id().toString());
        btn.setDisable(false);

        btn.setOnAction(e -> {
            try {
                boxKeys.getChildren().clear();
                for (Key_word k: KPFinder.getInstance().usedInProblemByCategory(Integer.parseInt(btn.getId()))) {
                    CheckBox check = new CheckBox(k.getTitle());
                    check.setId(k.getKey_word_id().toString());

                    boxKeys.getChildren().add(check);
                }
                Button submit = new Button("Submit");
                submit.setOnMouseClicked(i -> {
                    for (Node ch: boxKeys.getChildren()) {
                        if(ch instanceof CheckBox)
                        {
                            if(((CheckBox) ch).isSelected())
                            {
                                try {
                                    List<Problem> problems = KPFinder.getInstance().findByCK(Integer.parseInt(btn.getId()), Integer.parseInt(ch.getId()));
                                    for (Problem p: problems) {
                                        // vylistovanie miesto printu
                                        System.out.println(p.getTitle());
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                });
                boxKeys.getChildren().add(submit);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        boxCategories.getChildren().add(btn);
    }

}
