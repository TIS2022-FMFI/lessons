package main.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.entities.KPFinder;
import main.entities.Key_word;
import main.entities.Problem;
import main.entities.ProblemFinder;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LessonController implements Initializable {
    public Text title;
    public TextField keywords;
    public ImageView image1;
    public ImageView image2;
    public TextField description;
    public TextField files;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Problem problem = ProblemFinder.getInstance().findById(Controller.chosenProblem);
            title.setText(problem.getTitle());
            keywords.setText("");
            List<Key_word> keyWords = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
            for (Key_word k: keyWords) {
                if(keywords.getText().equals("")) keywords.setText(k.getTitle());
                else keywords.setText(keywords.getText() + ", " + k.getTitle());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
