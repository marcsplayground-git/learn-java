package com.personalplayground.jdbc;

import java.sql.SQLException;

public final class JdbcUtils {

    // Private constructor to prevent instantiation (utility class)
    private JdbcUtils() {
    }

    /**
     * Print detailed information for a SQLException and all its chained exceptions.
     */
    public static void printSQLException(SQLException ex) {
        // Loop through each SQLException in the chain
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                SQLException sqlEx = (SQLException) e;
                System.err.println("----- SQLException -----");
                System.err.println("Message:    " + sqlEx.getMessage());
                System.err.println("SQLState:   " + sqlEx.getSQLState());
                System.err.println("ErrorCode:  " + sqlEx.getErrorCode());

                // Optional: show the class where it came from
                System.err.println("Exception class: " + sqlEx.getClass().getName());

                // Print where in Java code this happened
                sqlEx.printStackTrace(System.err);
                System.err.println("------------------------");
            } else {
                // Non-SQLException in the chain (rare, but possible)
                System.err.println("Non-SQL exception in chain: " + e.getClass().getName());
                e.printStackTrace(System.err);
            }
        }
    }
}
