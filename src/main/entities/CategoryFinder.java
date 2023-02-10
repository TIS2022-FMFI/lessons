package main.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.DbContext;

public class CategoryFinder {
    private static final CategoryFinder INSTANCE = new CategoryFinder();

    public static CategoryFinder getInstance() {
        return INSTANCE;
    }

    private CategoryFinder() {
    }

    /**
     * Finds category by id
     * @param id id of category
     * @return new Category object which has requested id
     * @throws SQLException
     */
    public Category findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM category WHERE category_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Category c = new Category();

                    c.setCategory_id(r.getInt("category_id"));
                    c.setTitle(r.getString("title"));

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
     * Finds category by its title/name
     * @param t title of category
     * @return new Category object with required title
     * @throws SQLException
     */
    public Category findByTitle(String t) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM category WHERE title = ?")) {
            s.setString(1, t);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Category c = new Category();

                    c.setCategory_id(r.getInt("category_id"));
                    c.setTitle(r.getString("title"));

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
     * Finds all Categories in database
     * @return List of all Category objects
     * @throws SQLException
     */
    public List<Category> findAll() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM category")) {

            try (ResultSet r = s.executeQuery()) {

                List<Category> elements = new ArrayList<>();

                while (r.next()) {
                    Category c = new Category();
                    c.setCategory_id(r.getInt("category_id"));
                    c.setTitle(r.getString("title"));

                    elements.add(c);
                }
                return elements;
            }
        }
    }
}
