package main.entities;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Key_word {
    private Integer key_word_id;
    private String title;
    private Boolean prime = false;

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO key_word (title, prime) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, title.toLowerCase());
            s.setBoolean(2, prime);
            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                key_word_id = r.getInt(1);
            }
        }
    }

    public void delete() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM key_word WHERE key_word_id = ?")) {
            s.setInt(1, key_word_id);
            Key_word_problem kwp = new Key_word_problem();
            kwp.setKey_word_id(key_word_id);
            kwp.deleteByKeywordId();
            s.executeUpdate();
        }
    }

    public void update()throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE key_word SET title = ? WHERE key_word_id=?")){
            s.setString(1, title);
            s.setInt(2, key_word_id);
            s.executeUpdate();
        }
    }

    public Boolean getPrime() { return prime; }

    public void setPrime(Boolean prime) { this.prime = prime; }

    public void setKey_word_id(Integer key_word_id) {
        this.key_word_id = key_word_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getKey_word_id() {
        return key_word_id;
    }

    public String getTitle() {
        return title;
    }
}
