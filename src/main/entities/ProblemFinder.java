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

    /**
     * Finds problem by id
     * @param id id of problem
     * @return new Problem object which has requested id
     * @throws SQLException
     */
    public Problem findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM problem WHERE problem_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Problem c = new Problem();

                    c.setProblem_id(r.getInt("problem_id"));
                    c.setTitle(r.getString("title"));
                    c.setDescription(r.getString("description"));
                    c.setPath(r.getString("path"));
                    c.setUser_name(r.getString("user_name"));
                    c.setCreated_at(r.getDate("create_at"));
                    c.setLast_editor(r.getString("last_editor"));
                    c.setLast_edited_at(r.getDate("last_edited_at"));
                    c.setEdit_description(r.getString("edit_description"));
                    c.setCategory_id(r.getInt("category_id"));
                    c.setImage1(r.getString("image1"));
                    c.setImage2(r.getString("image2"));

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

    /**
     * Finds all Problems in database
     * @return List of all Problem objects
     * @throws SQLException
     */
    public List<Problem> findAll() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM problem")) {

            try (ResultSet r = s.executeQuery()) {

                List<Problem> elements = new ArrayList<>();

                while (r.next()) {
                    Problem c = new Problem();

                    c.setProblem_id(r.getInt("problem_id"));
                    c.setTitle(r.getString("title"));
                    c.setDescription(r.getString("description"));
                    c.setPath(r.getString("path"));
                    c.setUser_name(r.getString("user_name"));
                    c.setCreated_at(r.getDate("create_at"));
                    c.setLast_editor(r.getString("last_editor"));
                    c.setLast_edited_at(r.getDate("last_edited_at"));
                    c.setEdit_description(r.getString("edit_description"));
                    c.setCategory_id(r.getInt("category_id"));
                    c.setImage1(r.getString("image1"));
                    c.setImage2(r.getString("image2"));
                    elements.add(c);
                }
                return elements;
            }
        }
    }

    /**
     * Finds list of Problems which have id of requested category
     * @param id_category id of category
     * @return list of Problem objects from database which have required category id
     * @throws SQLException
     */
    public List<Problem> findByCategory(int id_category) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM problem WHERE category_id = ?")) {

            s.setInt(1, id_category);
            try (ResultSet r = s.executeQuery()) {

                List<Problem> elements = new ArrayList<>();

                while (r.next()) {
                    Problem c = new Problem();

                    c.setProblem_id(r.getInt("problem_id"));
                    c.setTitle(r.getString("title"));
                    c.setDescription(r.getString("description"));
                    c.setPath(r.getString("path"));
                    c.setUser_name(r.getString("user_name"));
                    c.setCreated_at(r.getDate("create_at"));
                    c.setLast_editor(r.getString("last_editor"));
                    c.setLast_edited_at(r.getDate("last_edited_at"));
                    c.setEdit_description(r.getString("edit_description"));
                    c.setCategory_id(r.getInt("category_id"));
                    c.setImage1(r.getString("image1"));
                    c.setImage2(r.getString("image2"));
                    elements.add(c);
                }
                return elements;
            }
        }
    }
}
