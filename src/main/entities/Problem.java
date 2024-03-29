package main.entities;

import main.DbContext;

import java.sql.*;
import java.time.LocalDate;

public class Problem {
    private Integer problem_id;
    private String title;
    private String description;
    private String path;
    private String image1;
    private String image2;
    private String user_name;
    private LocalDate created_at;
    private LocalDate last_edited_at;
    private String last_editor;
    private String edit_description;
    private Integer category_id;

    /**
     * Insert object to database table <em>problem</em>
     * @throws SQLException
     */
    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().
                prepareStatement("INSERT INTO problem (title, description, path, user_name, create_at, category_id, image1, image2) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, title);
            s.setString(2, description);
            s.setString(3, path);
            s.setString(4, user_name);
            s.setObject(5, created_at);
            s.setInt(6, category_id);
            s.setString(7, image1);
            s.setString(8, image2);
            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                problem_id = r.getInt(1);
            }
        }
    }

    /**
     * Delete form database table <em>problem</em> row with Problem id
     * @throws SQLException
     */
    public void delete() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM problem WHERE problem_id = ?")) {
            s.setInt(1, problem_id);
            Key_word_problem kwp = new Key_word_problem();
            kwp.setProblem_id(problem_id);
            kwp.deleteByProblemId();
            s.executeUpdate();
        }
    }

    /**
     * Send update query to database table <em>problem</em> to update row with data from object
     * @throws SQLException
     */
    public void update()throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().
                prepareStatement("UPDATE problem SET title = ?, description = ?, path = ?, user_name = ?, create_at = ?, last_editor = ?, last_edited_at = ?, edit_description = ?, image1 = ?, image2 = ?, category_id = ? WHERE problem_id = ?")){
            s.setString(1, title);
            s.setString(2, description);
            s.setString(3, path);
            s.setString(4, user_name);
            s.setObject(5, created_at);
            s.setString(6, last_editor);
            s.setObject(7, last_edited_at);
            s.setString(8, edit_description);
            s.setString(9, image1);
            s.setString(10, image2);
            s.setInt(11, category_id);
            s.setInt(12, problem_id);
            s.executeUpdate();
        }
    }

    public Integer getCategory_id() { return category_id; }

    public void setCategory_id(Integer category_id) { this.category_id = category_id; }

    public String getLast_editor() {
        return last_editor;
    }

    public void setLast_editor(String last_editor) {
        this.last_editor = last_editor;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        if (created_at != null) {
            this.created_at = created_at.toLocalDate();
        }
    }

    public String getEdit_description() {
        return edit_description;
    }

    public void setEdit_description(String edit_description) {
        this.edit_description = edit_description;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getImage1() { return image1; }

    public void setImage1(String image1) { this.image1 = image1; }

    public String getImage2() { return image2; }

    public void setImage2(String image2) { this.image2 = image2; }

    public LocalDate getLast_edited_at() { return last_edited_at; }

    public void setLast_edited_at(Date last_edited_at) {
        if (last_edited_at != null) {
            this.last_edited_at = last_edited_at.toLocalDate();
        }
    }
}
