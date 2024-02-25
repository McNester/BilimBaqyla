package com.oop.bilimbaqyla.models;

import java.util.ArrayList;

public class Child {
    private int childId;
    private ArrayList<Integer> lessonsIdList;
    private String firstname;
    private String lastname;
    private int parentId;
    private int age;
    private ArrayList<Integer> waitingLessonsIdList;

    public Child(int childId, ArrayList<Integer> lessonsIdList, String firstname, String lastname, int parentId, int age, ArrayList<Integer> waitingLessonsIdList) {
        this.childId = childId;
        this.lessonsIdList = lessonsIdList;
        this.firstname = firstname;
        this.lastname = lastname;
        this.parentId = parentId;
        this.age = age;
        this.waitingLessonsIdList = waitingLessonsIdList;
    }

    public Child(ArrayList<Integer> lessonsIdList, String firstname, String lastname, int parentId, int age, ArrayList<Integer> waitingLessonsIdList) {
        this.lessonsIdList = lessonsIdList;
        this.firstname = firstname;
        this.lastname = lastname;
        this.parentId = parentId;
        this.age = age;
        this.waitingLessonsIdList = waitingLessonsIdList;
    }
    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Integer> getWaitingLessonsIdList() {
        return waitingLessonsIdList;
    }

    public void setWaitingLessonsIdList(ArrayList<Integer> waitingLessonsIdList) {
        this.waitingLessonsIdList = waitingLessonsIdList;
    }


}