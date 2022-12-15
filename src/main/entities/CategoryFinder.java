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
