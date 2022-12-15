package main.entities;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Category {
    private Integer category_id;
    private String title;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO category (title) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, title);
            s.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM category WHERE category_id = ?")) {
            s.setInt(1, category_id);
            s.executeUpdate();
        }
    }

    public void update()throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE category SET title = ? WHERE category_id=?")){
            s.setString(1, title);
            s.setInt(2, category_id);
            s.executeUpdate();
        }
    }
}
