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
    //TODO  transakcia!!!!!!!!!!
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE problem_id = ?")) {
            s.setInt(1, idP);

            try (ResultSet r = s.executeQuery()) {

                List<Key_word> elements = new ArrayList<>();
                while (r.next()) {
                    Key_word c = KeywordFinder.getInstance().findById(r.getInt("key_word_id"));
                    elements.add(c);
                }

               return elements;
            }
        }
    }


    public List<Problem> findByKeywordId(int idK) throws SQLException {
        //TODO  transakcia!!!!!!!!!!
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE key_word_id = ?")) {
            s.setInt(1, idK);

            try (ResultSet r = s.executeQuery()) {

                List<Problem> elements = new ArrayList<>();
                while (r.next()) {
                    Problem c = ProblemFinder.getInstance().findById(r.getInt("problem_id"));
                    elements.add(c);
                }

                return elements;
            }
        }
    }

    public List<Problem> findByCK(int idC, int idK) throws SQLException {
        //TODO  transakcia!!!!!!!!!! + dorobit ked bude frontend na zadavanie v inej funkcii
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE key_word_id = ?")) {
            s.setInt(1, idK);

            try (ResultSet r = s.executeQuery()) {
                List<Problem> elements = new ArrayList<>();
                while (r.next()) {
                    Problem c = ProblemFinder.getInstance().findById(r.getInt("problem_id"));
                    if (c.getCategory_id() == idC){
                        elements.add(c);
                    }
                }
                return elements;
            }
        }
    }
}
