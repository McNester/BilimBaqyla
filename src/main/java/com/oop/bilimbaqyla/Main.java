package com.oop.bilimbaqyla;


import com.oop.bilimbaqyla.services.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DataBaseConnection db = new DataBaseConnection();

        ResultSet rs = db.get("SELECT * FROM admins;\n");
        try {
            while (rs.next()) {
                int id = rs.getInt("adminId");
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");

                System.out.println();
                System.out.println("Admin number "+id+":\n"+"UserName: "+ username+"\nPassword: "+passwd);
                System.out.println();

            }
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
    }
}
