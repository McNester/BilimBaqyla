package com.oop.bilimbaqyla.repositories;

import com.oop.bilimbaqyla.models.Child;
import com.oop.bilimbaqyla.services.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

@Repository
public class ChildRepo {
    DataBaseConnection db = new DataBaseConnection();

    public int create(Child child){
        String query = String.format(Locale.US, "insert into child (firstname, lastname, parentid, age) values('%s','%s','%d','%d') RETURNING childid",
                child.getFirstname(),
                child.getLastname(),
                child.getParentId(),
                child.getAge());
        int id = db.post(query);

        return id;

    }

    public Child getChildById(int id){
        ResultSet rs = db.get("SELECT * FROM child WHERE childid = "+id);

        try {

            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int parentid = rs.getInt("parentid");
                int age = rs.getInt("age");

                ParentRepo parentRepo = new ParentRepo();

                ArrayList <Integer> lessonsIdList = parentRepo.getChildLessonIdList(id);
                ArrayList <Integer> waitingLessonsIdList = parentRepo.getChildWaitingLessonIdList(id);

                return new Child(id, lessonsIdList, firstname, lastname, parentid, age, waitingLessonsIdList);
            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public ArrayList<Child> getAll(){
        ArrayList<Child> children = new ArrayList<>();

        try{
            ResultSet rs = db.get("select * from child");
            while (rs.next()) {
                int id = rs.getInt("childid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int parentid = rs.getInt("parentid");
                int age = rs.getInt("age");

                ParentRepo parentRepo = new ParentRepo();

                ArrayList <Integer> lessonsIdList = parentRepo.getChildLessonIdList(id);
                ArrayList <Integer> waitingLessonsIdList = parentRepo.getChildWaitingLessonIdList(id);

                children.add(new Child(id, lessonsIdList, firstname, lastname,parentid,age,waitingLessonsIdList));
            }

            return children;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public ArrayList<Child> getChildrenByParentId(int id) {
        ArrayList<Integer> childrenIds = new ArrayList<>();
        ParentRepo parentRepo = new ParentRepo();
        childrenIds = parentRepo.getChildrenIdByParent(id);
        ArrayList<Child> children = new ArrayList<>();
        childrenIds.forEach((n)->{
            children.add(getChildById(n));
        });
        return children;
    }
}
