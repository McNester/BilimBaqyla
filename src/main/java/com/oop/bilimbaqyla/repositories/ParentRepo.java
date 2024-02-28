package com.oop.bilimbaqyla.repositories;

import com.oop.bilimbaqyla.models.Child;
import com.oop.bilimbaqyla.models.Parent;
import com.oop.bilimbaqyla.services.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

@Repository

public class ParentRepo {
    DataBaseConnection db = new DataBaseConnection();


    public int create(Parent parent){
        String query = String.format(Locale.US, "insert into parent (firstname, lastname, phonenumber, username,passwd,email) values('%s','%s','%s','%s','%s','%s') RETURNING parentid",
                parent.getFirstname(),
                parent.getLastname(),
                parent.getPhoneNumber(),
                parent.getUsername(),
                parent.getPasswd(),
                parent.getEmail());


        int id = db.post(query);


        return id;

    }
    public Parent getParentById(int id){
        ResultSet rs = db.get("SELECT * FROM parent WHERE parentid = "+id);

        try {

            while (rs.next()) {
                String name = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phone = rs.getString("phonenumber");
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                String email = rs.getString("email");

                ArrayList <Integer> childIdList = getChildrenIdByParent(id);

                return new Parent(id,name,lastname,childIdList,phone,username,passwd,email);
            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    public ArrayList<Parent> getAll(){
        ArrayList<Parent> parents = new ArrayList<>();

        try{
            ResultSet rs = db.get("select * from parent");
            while (rs.next()) {
                int id = rs.getInt("parentid");
                String name = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phone = rs.getString("phonenumber");
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                String email = rs.getString("email");

                ArrayList <Integer> childIdList = getChildrenIdByParent(id);

                parents.add(new Parent(id,name,lastname,childIdList,phone,username,passwd,email));
            }

            return parents;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    public void addChildToParent(int parentId,int childId){
        String query = String.format(Locale.US, "insert into childparentlink (childid, parentid) values(%d,%d);",
                childId,
                parentId);
        db.post(query);
    }
    public ArrayList<Child> getAllChildren(){
        ArrayList<Child> children = new ArrayList<>();
        try{
            ResultSet rs = db.get("select * from child");
            while(rs.next()){
                int id = rs.getInt("childid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int parentid = rs.getInt("parentid");
                int age = rs.getInt("age");

                children.add(new Child(id,getChildLessonIdList(id),firstname,lastname,parentid,age,getChildWaitingLessonIdList(id)));

            }
            return children;
        }catch(SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public ArrayList<Integer> getChildLessonIdList(int id){
        ResultSet rs = db.get("SELECT lessonid FROM enrolledchildlessonlink WHERE childid = "+id);

        ArrayList <Integer> lessonIdList =new ArrayList<>();
        try {

            while (rs.next()) {

                lessonIdList.add(rs.getInt("lessonid"));



            }
            return lessonIdList;

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    public ArrayList<Integer> getChildWaitingLessonIdList(int id){
        ResultSet rs = db.get("SELECT lessonid FROM waitingchildlessonlink WHERE childid = "+id);

        ArrayList <Integer> lessonIdList =new ArrayList<>();
        try {

            while (rs.next()) {

                lessonIdList.add(rs.getInt("lessonid"));



            }
            return lessonIdList;

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    public ArrayList<Integer> getChildrenIdByParent(int id){
        ArrayList <Integer> childrenIds = new ArrayList<>();

        try{
            ResultSet rs = db.get("SELECT childid FROM childparentlink WHERE parentid = "+id);
            while(rs.next()){
                childrenIds.add(rs.getInt("childid"));
            }
            return childrenIds;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public boolean checkUsername(String username){
        String query = "SELECT EXISTS (SELECT 1 FROM parent WHERE username = '"+username+"') AS DoesExist;";

        ResultSet rs = db.get(query);
        try {
            if (rs.next()) {
                boolean doesExist = rs.getBoolean("DoesExist");
                return doesExist;
            }
        }catch(SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return  false;
    }

}
