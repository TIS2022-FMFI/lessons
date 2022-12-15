package main.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.DbContext;

public class ProblemFinder {
    private static final ProblemFinder INSTANCE = new ProblemFinder();

    public static ProblemFinder getInstance() {
        return INSTANCE;
    }

    private ProblemFinder() {
    }

    public Problem findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM problem WHERE problem_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Problem c = new Problem();

                    c.setProblem_id(r.getInt("key_word_id"));
                    c.setTitle(r.getString("title"));
                    c.setDescription(r.getString("description"));
                    c.setPath(r.getString("path"));
                    c.setUser_name(r.getString("user_name"));
                    c.setCreated_at(r.getDate("create_at"));
                    c.setLast_editor(r.getString("last_editor"));
                    c.setEdited_at(r.getDate("last_edited_at"));
                    c.setEdit_description(r.getString("edit_description"));
                    c.setCategory_id(r.getInt("category_id"));

                    if (r.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Problem> findAll() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM problem")) {

            try (ResultSet r = s.executeQuery()) {

                List<Problem> elements = new ArrayList<>();

                while (r.next()) {
                    Problem c = new Problem();

                    c.setProblem_id(r.getInt("key_word_id"));
                    c.setTitle(r.getString("title"));
                    c.setDescription(r.getString("description"));
                    c.setPath(r.getString("path"));
                    c.setUser_name(r.getString("user_name"));
                    c.setCreated_at(r.getDate("create_at"));
                    c.setLast_editor(r.getString("last_editor"));
                    c.setEdited_at(r.getDate("last_edited_at"));
                    c.setEdit_description(r.getString("edit_description"));
                    c.setCategory_id(r.getInt("category_id"));

                    elements.add(c);
                }
                return elements;
            }
        }
    }

}
