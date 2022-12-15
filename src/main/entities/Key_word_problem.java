package main.entities;

import main.DbContext;

import java.sql.PreparedStatement;
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

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO \"key_word_problem\" (problem_id, key_word_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, problem_id);
            s.setInt(2, key_word_id);
            s.executeUpdate();
        }
    }
    public void delete() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM \"key_word_problem\" WHERE key_word_problem_id = ?")) {
            s.setInt(1, key_word_problem_id);
            s.executeUpdate();
        }
    }

    public void update()throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE \"key_word_problem\" SET problem_id = ?, key_word_id = ? WHERE key_word_problem_id=?")){
            s.setInt(1, problem_id);
            s.setInt(2, key_word_id);
            s.setInt(3, key_word_problem_id);
            s.executeUpdate();
        }
    }
}
