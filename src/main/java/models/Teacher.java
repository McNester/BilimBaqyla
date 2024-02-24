package models;

import java.util.ArrayList;

public class Teacher {
    private int teacherId;
    private ArrayList<Integer> lessonsIdList;
    private String firstname;
    private String lastname;
    private String username;
    private String passwd;
    private String email;

    public Teacher(int teacherId, ArrayList<Integer> lessonsIdList, String firstname, String lastname, String username, String passwd, String email) {
        this.teacherId = teacherId;
        this.lessonsIdList = lessonsIdList;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.passwd = passwd;
        this.email = email;
    }

    public Teacher(ArrayList<Integer> lessonsIdList, String firstname, String lastname, String username, String passwd, String email) {
        this.lessonsIdList = lessonsIdList;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.passwd = passwd;
        this.email = email;
    }

    public void getAllChildren(){

    }

    public void getAllChildrenByLessonId(){

    }

    public void getLessonsByTeacher(){

    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public ArrayList<Integer> getLessonsIdList() {
        return lessonsIdList;
    }

    public void setLessonsIdList(ArrayList<Integer> lessonsIdList) {
        this.lessonsIdList = lessonsIdList;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
