package main.ui;

import javafx.application.HostServices;
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

    private HostServices hostServices ;

    public HostServices getHostServices() {
        return hostServices ;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCategories();
        showAllLessons();
    }

    public void showCategories(){
        boxCategories.getChildren().clear();
        try {
            List<Category> cat = CategoryFinder.getInstance().findAll();
            for (int i = 0; i < cat.size(); i++) {
                addButton(cat.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Button reset = new Button("RELOAD PAGE");
        reset.getStyleClass().add("reset");
        boxCategories.getChildren().add(reset);

        reset.setOnAction(e -> {
            showCategories();
            showAllLessons();
        });
    }

    public void showAllKeywords(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/keywords.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("New category");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
//        List<Key_word> keywords = null;
//        try {
//            keywords = KeywordFinder.getInstance().findAll();
//            keywords.sort(Comparator.comparing(Key_word::getTitle));
//            for (Key_word k: keywords) {
//                System.out.println(k.getTitle());
////                Text key = new Text(k.getTitle());
////                keywordList.getChildren().add(key);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            URL fxmlLocation = getClass().getResource("../fxml/.fxml");
//            FXMLLoader loader = new FXMLLoader(fxmlLocation);
//            loader.setController(new NewLessonController());
//            Parent root1 = (Parent) loader.load();
//            Stage stage = new Stage();
//            stage.setResizable(false);
//            stage.setScene(new Scene(root1));
//            stage.setTitle("Keywords");
//            stage.show();
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
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
            loader.setController(new NewLessonController());
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

    public void editKeywordsButton(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/edit_keywords.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("Edit keywords");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editCategoriesButton(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/edit_categories.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("Edit categories");
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
                    int count_selected = 0;
                    Map<Integer, Integer> problems = new HashMap<>();
                    for (Node ch: boxKeys.getChildren()) {
                        if(ch instanceof CheckBox)
                        {
                            if(((CheckBox) ch).isSelected())
                            {
                                count_selected++;
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
                    if(count_selected == 0){
                        try {
                            for(Problem p : ProblemFinder.getInstance().findByCategory(Integer.parseInt(btn.getId()))){
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void makeLesson(Problem problem){
        HBox lesson = new HBox();
        VBox details = new VBox();
        VBox problems = new VBox();
        VBox info = new VBox();
        HBox images = new HBox();
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
            String path1 = "file" + problem.getImage1().replace('\\', '/').substring(1);
            image1 = new ImageView(new Image(path1,150, 150, true, false));
            lesson.getChildren().add(image1);
        }
        ImageView image2 = null;
        if(problem.getImage2() != null){
            String path2 = "file" + problem.getImage2().replace('\\', '/').substring(1);
            image2 = new ImageView(new Image(path2, 150, 150, true, false));
            lesson.getChildren().add(image2);
        }
        Button show = new Button("SHOW");
        show.setOnAction(v -> {
            try {
                chosenProblem = problem.getProblem_id();
                URL fxmlLocation = getClass().getResource("../fxml/show_lesson.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root1 = (Parent) loader.load();
                LessonController lessonController = loader.getController();
                lessonController.setHostServices(hostServices);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.setTitle(problem.getTitle());
                stage.show();
            } catch(Exception exp) {
                exp.printStackTrace();
            }
        });
        buttons.getChildren().add(show);
        if (image1 != null && image2 == null) info.getChildren().addAll(title, text, image1);
        else if (image2 != null && image1 == null) info.getChildren().addAll(title, text, image2);
        else if (image1 != null && image2 != null) {
            images.getChildren().addAll(image1, image2);
            info.getChildren().addAll(title, text, images);
        } else {
            info.getChildren().addAll(title, text);

        }

        problems.getChildren().addAll(title, info);
        details.getChildren().addAll(problems);
        lesson.getChildren().add(details);
        Separator s = new Separator();
        lessons.getChildren().addAll(lesson, buttons, s);

        lesson.getStyleClass().add("hbox");
        details.getStyleClass().add("vbox");
        title.getStyleClass().add("title");
        if (category != null) category.getStyleClass().add("category");
        keywords.getStyleClass().add("keywords");
        if (image1 != null) image1.getStyleClass().add("image1");
        if (image2 != null) image2.getStyleClass().add("image2");
        show.getStyleClass().add("show");
        s.getStyleClass().add("separator");
        buttons.getStyleClass().add("buttons");
        problems.getStyleClass().add("problem");
        info.getStyleClass().add("info");
        images.getStyleClass().add("images");
        text.getStyleClass().add("text");
    }
}
