package com.oop.bilimbaqyla.services;


import java.sql.*;

public class DataBaseConnection {
    private final String POSTGRES_HOST="ep-silent-scene-a4cd772l-pooler.us-east-1.aws.neon.tech";
    private final String PORT = "5432";
    private final String POSTGRES_DATABASE="verceldb";
    private final String connectionString = "jdbc:postgresql://"+POSTGRES_HOST+":"+PORT+"/"+POSTGRES_DATABASE;
    private final String user = "default";
    private final String passwd = "R89LWSkZmQXt";
    private Connection con = null;

    public ResultSet get(String sqlQuery) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString,user,passwd);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            return rs;
        } catch (SQLException e) {
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("driver error: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("could not close the connection: " + e.getMessage());
                }
            }
        }

        return null;

    }

    public void post(String sqlQuery){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString,user,passwd);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("driver error: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("could not close the connection: " + e.getMessage());
                }
            }
        }
    }

}
