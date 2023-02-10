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

    /**
     * Finds keyword by id
     * @param id id of keyword
     * @return new Key_word object which has requested id
     * @throws SQLException
     */
    public Key_word findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word WHERE key_word_id = ?")) {
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

    /**
     * Finds keyword by its title/name
     * @param titleK title of keyword
     * @return new Key_word object with required title
     * @throws SQLException
     */
    public Key_word findByTitle(String titleK) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word WHERE title = ?")) {
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

    /**
     * Finds all Key_words in database
     * @return List of all Key_word objects
     * @throws SQLException
     */
    public List<Key_word> findAll() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word")) {

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

    /**
     * Finds all Key_words in database of selected type. Prime or not prime.
     * @param primeX true if prime keyword, false if not prime keyword
     * @return List of all Key_word objects with given prime bool
     * @throws SQLException
     */
    public List<Key_word> findAllbyPrime(Boolean primeX) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word WHERE prime = ?")) {
            s.setBoolean(1, primeX);
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


