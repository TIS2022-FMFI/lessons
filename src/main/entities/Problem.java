package main.entities;

import java.util.Date;

public class Problem {
    private Integer problem_id;
    private String title;
    private Integer key_word_problem_id;
    private String description;
    private String path;
    private String user_name;
    private Date edited_at;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    private Integer category_id;

    public String getLast_editor() {
        return last_editor;
    }

    public void setLast_editor(String last_editor) {
        this.last_editor = last_editor;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getEdit_description() {
        return edit_description;
    }

    public void setEdit_description(String edit_description) {
        this.edit_description = edit_description;
    }

    private String last_editor;
    private Date created_at;
    private String edit_description;

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setKey_word_problem_id(Integer key_word_problem_id) {
        this.key_word_problem_id = key_word_problem_id;
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

    public void setEdited_at(Date edited_at) {
        this.edited_at = edited_at;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getKey_word_problem_id() {
        return key_word_problem_id;
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

    public Date getEdited_at() {
        return edited_at;
    }
}
