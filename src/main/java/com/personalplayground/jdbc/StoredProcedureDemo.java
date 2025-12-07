package com.personalplayground.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoredProcedureDemo {

    public static void main(String[] args) {
        System.out.println("Starting StoredProcedureDemo...");

        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        // Try different keywords
        callCountNotesByKeyword(url, user, password, "JDBC");
        callCountNotesByKeyword(url, user, password, "note");
        callCountNotesByKeyword(url, user, password, "does-not-exist");

        System.out.println("StoredProcedureDemo finished.");
    }

    private static void callCountNotesByKeyword(String url, String user, String password,
                                                String keyword) {

        System.out.println("Calling stored procedure count_notes_by_keyword with keyword = " + keyword);

        // JDBC call syntax for stored procedure with 1 IN and 1 OUT parameter
        String callSql = "{ call count_notes_by_keyword(?, ?) }";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             CallableStatement cs = conn.prepareCall(callSql)) {

            // 1. Set IN parameter (position 1)
            cs.setString(1, keyword);

            // 2. Register OUT parameter (position 2) as INTEGER
            cs.registerOutParameter(2, java.sql.Types.INTEGER);

            // 3. Execute the stored procedure
            cs.execute();

            // 4. Read the OUT parameter value
            int count = cs.getInt(2);

            System.out.println("Result from stored procedure:");
            System.out.println("  Keyword: " + keyword);
            System.out.println("  Count of notes with keyword in title: " + count);
            System.out.println("--------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error while calling stored procedure count_notes_by_keyword!");
            JdbcUtils.printSQLException(e);
        }
    }
}
