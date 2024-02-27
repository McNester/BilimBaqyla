package com.oop.bilimbaqyla.repositories;

import com.oop.bilimbaqyla.models.Child;
import com.oop.bilimbaqyla.models.Parent;
import com.oop.bilimbaqyla.models.Child;
import com.oop.bilimbaqyla.models.Teacher;
import com.oop.bilimbaqyla.services.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

@Repository
public class ChildRepo {
    DataBaseConnection db = new DataBaseConnection();

    public void create(Child child){
        String query = String.format(Locale.US, "insert into child (firstname, lastname, parentid, age) values('%s','%s','%d','%d');",
                child.getFirstname(),
                child.getLastname(),
                child.getParentId(),
                child.getAge());
        db.post(query);
    }

    public Child getChildById(int id){
        ResultSet rs = db.get("SELECT * FROM child WHERE childid = "+id);

        try {

            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int parentid = rs.getInt("parentid");
                int age = rs.getInt("age");

                ArrayList <Integer> lessonsIdList = getLessonIdByTeacher(id);

                return new Teacher(id, lessonsIdList, firstname, lastname,username,passwd,email);
            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
}
