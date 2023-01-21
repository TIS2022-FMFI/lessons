package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.entities.*;

import java.awt.*;
import java.lang.reflect.Array;
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

    public VBox lessons;

    public static Integer chosenProblem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lessons.getChildren().clear();
        try {
            List<Category> cat = CategoryFinder.getInstance().findAll();
            for (int i = 1; i < cat.size(); i++) {
                addButton(cat.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<Problem> problems = ProblemFinder.getInstance().findAll();
            for (Problem p: problems) {
                makeLesson(p);
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
            ArrayList<Integer> list = new ArrayList<>();
            LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
            for(Map.Entry<Integer, Integer> entry : problems.entrySet()){
                list.add(entry.getValue());
            }
            Collections.sort(list, new Comparator<Integer>() {
                public int compare(Integer i, Integer ii){
                    return (ii).compareTo(i);
                }
            });
            for (Integer i:list){
                for(Map.Entry<Integer, Integer> entry: problems.entrySet()) {
                    if (entry.getValue().equals(i)){
                        sortedMap.put(entry.getKey(), i);
                    }
                }
            }
            showLessons(sortedMap);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void newLessonButton() throws Exception {
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

    @FXML
    public void newKeywordButton() throws Exception {
        try {
            URL fxmlLocation = getClass().getResource("../fxml/new_keyword.fxml");
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
                    Map<Integer, Integer> problems = new HashMap<>();
                    for (Node ch: boxKeys.getChildren()) {
                        if(ch instanceof CheckBox)
                        {
                            if(((CheckBox) ch).isSelected())
                            {
                                try {
                                    for(Problem p : KPFinder.getInstance().findByCK(Integer.parseInt(btn.getId()), Integer.parseInt(ch.getId()))){
                                        if (!problems.containsKey(p.getProblem_id())){
                                            problems.put(p.getProblem_id(), 1);
                                        }
                                        else{
                                            problems.put(p.getProblem_id(), problems.get(p.getProblem_id()) + 1);
                                        }
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    showLessons(problems);
                });
                boxKeys.getChildren().add(submit);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        boxCategories.getChildren().add(btn);
    }

    public void showLessons(Map<Integer, Integer> problems){
        lessons.getChildren().clear();
        for(Integer p : problems.keySet()){
            try {
                Problem problem = ProblemFinder.getInstance().findById(p);
                makeLesson(problem);
                System.out.println(ProblemFinder.getInstance().findById(p).getTitle() +  "     occurred " + problems.get(p) + " times");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void makeLesson(Problem problem){
        HBox lesson = new HBox();
        VBox details = new VBox();
        Text title = new Text(problem.getTitle());
        Text category = null;
        try {
            category = new Text(CategoryFinder.getInstance().findById(problem.getCategory_id()).getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Text keywords = new Text();
        List<Key_word> keyWords = null;
        try {
            keyWords = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Key_word k: keyWords) {
            if(keywords.getText().equals("")) keywords.setText(k.getTitle());
            else keywords.setText(keywords.getText() + ", " + k.getTitle());
        }
        details.getChildren().addAll(title, category, keywords);
        lesson.getChildren().add(details);
        if(problem.getImage1() != null){
            ImageView image1 = new ImageView(new Image(problem.getImage1()));
            lesson.getChildren().add(image1);
        }
        if(problem.getImage2() != null){
            ImageView image2 = new ImageView(new Image(problem.getImage2()));
            lesson.getChildren().add(image2);
        }
        Button modal = new Button("Modal");
        Button show = new Button("Show");
        show.setOnAction(v -> {
            try {
                chosenProblem = problem.getProblem_id();
                URL fxmlLocation = getClass().getResource("../fxml/show_lesson.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch(Exception exp) {
                exp.printStackTrace();
            }
        });
        lesson.getChildren().addAll(modal, show);
        Separator s = new Separator();
        lessons.getChildren().addAll(lesson, s);
    }
}
