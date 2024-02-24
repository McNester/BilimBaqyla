package models;

import java.util.ArrayList;

public class Parent {
    private int parentId;
    private String firstname;
    private String lastname;
    private ArrayList<Integer> childIdList;
    private String phoneNumber;
    private String username;
    private String passwd;
    private String email;

    public Parent(int parentId, String firstname, String lastname, ArrayList<Integer> childIdList, String phoneNumber, String username, String passwd, String email) {
        this.parentId = parentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.childIdList = childIdList;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.passwd = passwd;
        this.email = email;
    }

    public Parent(String firstname, String lastname, ArrayList<Integer> childIdList, String phoneNumber, String username, String passwd, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.childIdList = childIdList;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.passwd = passwd;
        this.email = email;
    }

    public void requestPayment(int childId, double price){
        //from db addChildIdToAdmin(int childId);
        //from db addPriceToAdmin(double price);
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public ArrayList<Integer> getChildIdList() {
        return childIdList;
    }

    public void setChildIdList(ArrayList<Integer> childIdList) {
        this.childIdList = childIdList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
