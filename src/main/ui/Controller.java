package main.ui;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        try {
            List<Category> cat = CategoryFinder.getInstance().findAll();
            for (int i = 1; i < cat.size(); i++) {
                addButton(cat.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAllLessons();
    }

        Button reset = new Button("RESET");
        reset.getStyleClass().add("reset");
        boxCategories.getChildren().add(reset);

        reset.setOnAction(e -> {
            showAllLessons();
                });
        showAllLessons();
    }

    public void showAllLessons(){
        lessons.getChildren().clear();
        boxKeys.getChildren().clear();
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
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.setTitle("New lesson");
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
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("New keyword");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addButton(Category c){
        Button btn = new Button(c.getTitle());
        btn.getStyleClass().add("btnCat");
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
                Button submit = new Button("SUBMIT");
                submit.getStyleClass().add("submit");
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
        VBox problems = new VBox();
        HBox info = new HBox();
        VBox text = new VBox();
        VBox buttons = new VBox();
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
        text.getChildren().addAll(category, keywords);

        ImageView image1 = null;
        if(problem.getImage1() != null){
            image1 = new ImageView(new Image(problem.getImage1(),150, 150, true, false));
            lesson.getChildren().add(image1);
        }
        ImageView image2 = null;
        if(problem.getImage2() != null){
            image2 = new ImageView(new Image(problem.getImage2(), 150, 150, true, false));
            lesson.getChildren().add(image2);
        }
        Button modal = new Button("MODAL");
        Button show = new Button("SHOW");
        show.setOnAction(v -> {
            try {
                chosenProblem = problem.getProblem_id();
                URL fxmlLocation = getClass().getResource("../fxml/show_lesson.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.setTitle(problem.getTitle());
                stage.show();
            } catch(Exception exp) {
                exp.printStackTrace();
            }
        });
        buttons.getChildren().addAll(modal, show);
        if (image1 != null && image2 == null) info.getChildren().addAll(title, text, image1, buttons);
        if (image2 != null && image1 == null) info.getChildren().addAll(title, text, image2, buttons);
        if (image1 != null && image2 != null) {
            info.getChildren().addAll(title, text, image1, image2, buttons);
        } else {
            info.getChildren().addAll(title, text, buttons);

        }

        problems.getChildren().addAll(title, info);
        details.getChildren().addAll(problems);
        lesson.getChildren().add(details);
        Separator s = new Separator();
        lessons.getChildren().addAll(lesson, s);

        lesson.getStyleClass().add("hbox");
        details.getStyleClass().add("vbox");
        title.getStyleClass().add("title");
        if (category != null) category.getStyleClass().add("category");
        keywords.getStyleClass().add("keywords");
        if (image1 != null) image1.getStyleClass().add("image1");
        if (image2 != null) image2.getStyleClass().add("image2");
        modal.getStyleClass().add("modal");
        show.getStyleClass().add("show");
        s.getStyleClass().add("separator");
        buttons.getStyleClass().add("buttons");
        problems.getStyleClass().add("problem");
        info.getStyleClass().add("info");
        text.getStyleClass().add("text");
    }
}
