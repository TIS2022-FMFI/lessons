package main.entities;

import java.sql.Connection;
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

    /**
     * Finds all keywords linked with exact problem by problem id
     * @param idP id of problem
     * @return list of Key_word objects which are connected to exact problem
     * @throws SQLException
     */
    public List<Key_word> findByProblemId(int idP) throws SQLException {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        c.setAutoCommit(false);
        try {
            try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE problem_id = ?")) {
                s.setInt(1, idP);

                try (ResultSet r = s.executeQuery()) {

                    List<Key_word> elements = new ArrayList<>();
                    while (r.next()) {

                        Key_word k = KeywordFinder.getInstance().findById(r.getInt("key_word_id"));
                        elements.add(k);
                    }
                    c.commit();
                   return elements;
                }
            }

        } catch (Exception e){
            c.rollback();
            throw e;
        } finally {
            c.setAutoCommit(true);
        }
    }

    /**
     * Finds all problems linked with exact keyword by keyword id
     * @param idK id of keyword
     * @return list of Problem objects which are connected to exact keyword
     * @throws SQLException
     */
    public List<Problem> findByKeywordId(int idK) throws SQLException {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        c.setAutoCommit(false);
        try {
            try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE key_word_id = ?")) {
                s.setInt(1, idK);

                try (ResultSet r = s.executeQuery()) {

                    List<Problem> elements = new ArrayList<>();
                    while (r.next()) {
                        Problem p = ProblemFinder.getInstance().findById(r.getInt("problem_id"));

                        elements.add(p);
                    }
                    c.commit();
                    return elements;
                }
            }
        } catch (Exception e){
            c.rollback();
            throw e;
        } finally {
            c.setAutoCommit(true);
        }
    }

    /**
     * Finds problems which has exact keyword and category
     * @param idC id of category
     * @param idK id of keyword
     * @return list of Problem objects with required category and keywords
     * @throws SQLException
     */
    public List<Problem> findByCK(int idC, int idK) throws SQLException {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        c.setAutoCommit(false);
        try {
            try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE key_word_id = ?")) {
                s.setInt(1, idK);

                try (ResultSet r = s.executeQuery()) {
                    List<Problem> elements = new ArrayList<>();
                    while (r.next()) {
                        Problem p = ProblemFinder.getInstance().findById(r.getInt("problem_id"));
                        if (p.getCategory_id() == idC){
                            elements.add(p);
                        }
                    }
                    c.commit();
                    return elements;
                }
            }
        } catch (Exception e){
            c.rollback();
            throw e;
        } finally {
            c.setAutoCommit(true);
        }
    }

    /**
     * Finds all keywords used in problems by category
     * @param categoryId id of category to search
     * @return list of Keywords which are linked with problem in category
     * @throws SQLException
     */
    public List<Key_word> usedInProblemByCategory(int categoryId) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement(
                "SELECT DISTINCT kw.key_word_id, kw.title, kw.prime FROM key_word_problem kwp LEFT JOIN key_word kw " +
                        "ON kwp.key_word_id = kw.key_word_id WHERE kwp.problem_id IN" +
                        "(SELECT problem_id FROM problem WHERE category_id = ?)")) {
            s.setInt(1, categoryId);

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
     * Finds many to many connection of problem and keyword
     * @param idK id of keyword
     * @param idP id of problem
     * @return new Key-word_problem object
     * @throws SQLException
     */
    public Key_word_problem findByKeywordAndProblem(int idK, int idP) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE key_word_id = ? AND problem_id = ?")) {
            s.setInt(1, idK);
            s.setInt(2, idP);

            try (ResultSet r = s.executeQuery()) {

                Key_word_problem kwp = null;

                if (r.next()) {
                    kwp = new Key_word_problem();
                    kwp.setProblem_id(r.getInt("problem_id"));
                    kwp.setKey_word_id(r.getInt("key_word_id"));
                    kwp.setKey_word_problem_id(r.getInt("key_word_problem_id"));
                }

                return kwp;
            }
        }
    }

    /**
     * Finds all many to many connection by keyword id
     * @param keyword_id id of keyword
     * @return list of Key_word_problem objects with required key_word id
     * @throws SQLException
     */
    public List<Key_word_problem> findKWPByKeywordId(int keyword_id) throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM key_word_problem WHERE key_word_id = ?")){
            s.setInt(1, keyword_id);

            try(ResultSet r = s.executeQuery()){

                List<Key_word_problem> elements = new ArrayList<>();

                while (r.next()){
                    Key_word_problem kwp = new Key_word_problem();
                    kwp.setKey_word_problem_id(r.getInt("key_word_problem_id"));
                    kwp.setProblem_id(r.getInt("problem_id"));
                    kwp.setKey_word_id(r.getInt("key_word_id"));
                }
                return elements;
            }
        }
    }
}
