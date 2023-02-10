package main.entities;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Key_word_problem {
    private Integer key_word_problem_id;
    private Integer problem_id;
    private Integer key_word_id;

    public Integer getKey_word_problem_id() {
        return key_word_problem_id;
    }

    public void setKey_word_problem_id(Integer key_word_problem_id) {
        this.key_word_problem_id = key_word_problem_id;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public Integer getKey_word_id() {
        return key_word_id;
    }

    public void setKey_word_id(Integer key_word_id) {
        this.key_word_id = key_word_id;
    }

    /**
     * Insert object to database table <em>key_word_problem</em>
     * @throws SQLException
     */
    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO key_word_problem (problem_id, key_word_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, problem_id);
            s.setInt(2, key_word_id);
            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                key_word_problem_id = r.getInt(1);
            }
        }

    }

    /**
     * Delete form database table <em>key_word_problem</em> row with key_word_problem_id
     * @throws SQLException
     */
    public void delete() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM key_word_problem WHERE key_word_problem_id = ?")) {
            s.setInt(1, key_word_problem_id);
            s.executeUpdate();
        }
    }

    /**
     * Send update query to database table <em>key_word_problem</em> to update row with data from object
     * @throws SQLException
     */
    public void update()throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE key_word_problem SET problem_id = ?, key_word_id = ? WHERE key_word_problem_id =?")){
            s.setInt(1, problem_id);
            s.setInt(2, key_word_id);
            s.setInt(3, key_word_problem_id);
            s.executeUpdate();
        }
    }

    /**
     * Delete form database table <em>key_word_problem</em> row with Key_word_problem id
     * @throws SQLException
     */
    public void deleteByProblemId()throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM key_word_problem WHERE problem_id = ?")) {
            s.setInt(1, problem_id);
            s.executeUpdate();
        }
    }

    /**
     * Delete form database table <em>key_word_problem</em> row with Key_word_problem id
     * @throws SQLException
     */
    public void deleteByKeywordId()throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM key_word_problem WHERE key_word_id = ?")) {
            s.setInt(1, key_word_id);
            s.executeUpdate();
        }
    }
}
