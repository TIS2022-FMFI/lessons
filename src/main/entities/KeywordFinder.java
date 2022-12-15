package main.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.DbContext;

public class KeywordFinder {
    private static final KeywordFinder INSTANCE = new KeywordFinder();

    public static KeywordFinder getInstance() {
        return INSTANCE;
    }

    private KeywordFinder() {
    }

    public Key_word findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM \"key_word\" WHERE key_word_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Key_word c = new Key_word();

                    c.setKey_word_id(r.getInt("key_word_id"));
                    c.setTitle(r.getString("title"));
                    c.setPrime(r.getBoolean("prime"));


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

    public Key_word findByTitle(String titleK) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM \"key_word\" WHERE title = ?")) {
            s.setString(1, titleK);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Key_word c = new Key_word();

                    c.setKey_word_id(r.getInt("key_word_id"));
                    c.setTitle(r.getString("title"));
                    c.setPrime(r.getBoolean("prime"));


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

    public List<Key_word> findAllCustom() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM \"key_word\" WHERE prime = FALSE")) {

            try (ResultSet r = s.executeQuery()) {

                List<Key_word> elements = new ArrayList<>();

                while (r.next()) {
                    Key_word c = new Key_word();

                    c.setKey_word_id(r.getInt("key_word_id"));
                    c.setTitle(r.getString("title"));
                    c.setPrime(r.getBoolean("prime"));

                    elements.add(c);
                }
                return elements;
            }
        }
    }

    public List<Key_word> findAllPrimary() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM \"key_word\" WHERE prime = TRUE")) {

            try (ResultSet r = s.executeQuery()) {

                List<Key_word> elements = new ArrayList<>();

                while (r.next()) {
                    Key_word c = new Key_word();

                    c.setKey_word_id(r.getInt("key_word_id"));
                    c.setTitle(r.getString("title"));
                    c.setPrime(r.getBoolean("prime"));

                    elements.add(c);
                }
                return elements;
            }
        }
    }
}


