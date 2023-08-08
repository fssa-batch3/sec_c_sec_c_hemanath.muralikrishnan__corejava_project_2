package com.fssa.agrokart.util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;

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

        String url;
        String userName;
        String passWord;

        if (System.getenv("CI") != null) {
            url = System.getenv("DATABASE_HOST");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
        } else {
            Dotenv env = Dotenv.load();
            url = env.get("DATABASE_HOST");
            userName = env.get("DATABASE_USERNAME");
            passWord = env.get("DATABASE_PASSWORD");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to the database");
        }
        return con;
    }

    private ConnectionUtil(){

    }

}
