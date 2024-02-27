package com.oop.bilimbaqyla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);

        /*DataBaseConnection db = new DataBaseConnection();
        My own branch

        Hello from serafim's branch

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
        }*/
    }
}
