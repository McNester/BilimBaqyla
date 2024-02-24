package models;

import java.util.ArrayList;

public class Lesson {
    private int lessonId;
    private Long duration;
    private int subjectId;
    private double price;
    private Long timelong;
    private int weekDay;
    private boolean isActive;
    private boolean isCanceled;
    private ArrayList<Integer> ChildIdList;
    private int teacherid;
    private int[] age = new int[2];

    public Lesson(int lessonId, Long duration, int subjectId, double price, Long timelong, int weekDay, boolean isActive, boolean isCanceled, ArrayList<Integer> childIdList, int teacherid, int[] age) {
        this.lessonId = lessonId;
        this.duration = duration;
        this.subjectId = subjectId;
        this.price = price;
        this.timelong = timelong;
        this.weekDay = weekDay;
        this.isActive = isActive;
        this.isCanceled = isCanceled;
        ChildIdList = childIdList;
        this.teacherid = teacherid;
        this.age = age;
    }

    public Lesson(Long duration, int subjectId, double price, Long timelong, int weekDay, boolean isActive, boolean isCanceled, ArrayList<Integer> childIdList, int teacherid, int[] age) {
        this.duration = duration;
        this.subjectId = subjectId;
        this.price = price;
        this.timelong = timelong;
        this.weekDay = weekDay;
        this.isActive = isActive;
        this.isCanceled = isCanceled;
        ChildIdList = childIdList;
        this.teacherid = teacherid;
        this.age = age;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getTimelong() {
        return timelong;
    }

    public void setTimelong(Long timelong) {
        this.timelong = timelong;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public ArrayList<Integer> getChildIdList() {
        return ChildIdList;
    }

    public void setChildIdList(ArrayList<Integer> childIdList) {
        ChildIdList = childIdList;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int[] getAge() {
        return age;
    }

    public void setAge(int[] age) {
        this.age = age;
    }
}
