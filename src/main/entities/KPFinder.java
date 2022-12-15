package main.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.DbContext;

public class KPFinder {
    private static final KPFinder INSTANCE = new KPFinder();

    public static KPFinder getInstance() {
        return INSTANCE;
    }

    private KPFinder() {
    }

    public List<Key_word> findByProblemId(int idP) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE problem_id = ?")) {
            s.setInt(1, idP);

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