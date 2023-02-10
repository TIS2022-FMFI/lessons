package main;

import java.sql.Connection;
import java.sql.SQLException;

public class DbContext {
    private static Connection connection;

    /**
     * Get stored connection with database
     * @return Connection object if exists, else error
     * @throws IllegalStateException
     */
    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("Connection must be set before calling this method");
        }
        return connection;
    }

    /**
     * Create new connection with database
     * @param connection connection to set
     * @throws SQLException
     */
    public static void setConnection(Connection connection) throws SQLException {
        if (connection == null) {
            throw new NullPointerException("Connection cannot be null");
        }
        DbContext.connection = connection;
        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
    }

    /**
     * Remove connection
     */
    public static void clear() {
        connection = null;
    }

}
