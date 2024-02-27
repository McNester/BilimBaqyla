package com.oop.bilimbaqyla.repositories;

import org.springframework.stereotype.Repository;
import com.oop.bilimbaqyla.models.Teacher;
import com.oop.bilimbaqyla.services.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
@Repository
public class TeacherRepo {
    DataBaseConnection db = new DataBaseConnection();

    public void create(Teacher teacher){
        String query = String.format(Locale.US, "insert into teacher (firstname, lastname, username,passwd,email) values('%s','%s','%s','%s','%s');",
                teacher.getFirstname(),
                teacher.getLastname(),
                teacher.getUsername(),
                teacher.getPasswd(),
                teacher.getEmail());
        db.post(query);
    }

    public Teacher getTeacherById(int id){
        ResultSet rs = db.get("SELECT * FROM teacher WHERE teacherid = "+id);

        try {

            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                String email = rs.getString("email");

                ArrayList <Integer> lessonsIdList = getLessonIdByTeacher(id);

                return new Teacher(id, lessonsIdList, firstname, lastname,username,passwd,email);
            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public ArrayList<Integer> getLessonIdByTeacher(int id){
        ArrayList <Integer> lessonsIds = new ArrayList<>();

        try{
            ResultSet rs = db.get("SELECT lessonid FROM teacherlessonlink WHERE teacherid = "+id);
            while(rs.next()){
                lessonsIds.add(rs.getInt("lessonid"));
            }
            return lessonsIds;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public ArrayList<Teacher> getAll(){
        ArrayList<Teacher> teachers = new ArrayList<>();

        try{
            ResultSet rs = db.get("select * from teacher");
            while (rs.next()) {
                int id = rs.getInt("teacherid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                String email = rs.getString("email");

                ArrayList <Integer> lessonsIdList = getLessonIdByTeacher(id);

                teachers.add(new Teacher(id, lessonsIdList, firstname, lastname,username,passwd,email));
            }

            return teachers;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    public void removeTeacherById(int id){
        String query = String.format(Locale.US, "UPDATE lesson SET teacherid = -1 WHERE teacherid = " + id);
        db.post(query);
    }
}
