package com.fssa.agrokart.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which holds the connection and close connection methods
 * Using this method, we can create multiple connection without rewriting the same code again and again
 * Using env, we get the database credentials for local and cloud also
 *
 * @author HemanathMuralikrishnan
 */
public class ConnectionUtil {

    public static Connection getConnection() {
        Connection con = null;

        // Check if running in GitHub Actions (using environment variable)
        String cloudDatabaseHost = System.getenv("DATABASE_HOST");
        if (cloudDatabaseHost != null) {
            // Running in GitHub Actions, use GitHub secrets
            String url = cloudDatabaseHost;
            String userName = System.getenv("DATABASE_USERNAME");
            String passWord = System.getenv("DATABASE_PASSWORD");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, userName, passWord);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Unable to connect to the database");
            }
        } else {
            // Running locally, use local .env file
            Dotenv env = Dotenv.load();
            String url = env.get("DATABASE_HOST");
            String userName = env.get("DATABASE_USERNAME");
            String passWord = env.get("DATABASE_PASSWORD");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, userName, passWord);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Unable to connect to the database");
            }
        }

        return con;
    }


    public static void close(ResultSet rs, Statement stmt, PreparedStatement pst, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // No need re throw the exception.
        }
    }

}
