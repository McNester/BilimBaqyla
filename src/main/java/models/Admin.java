package models;

import java.util.ArrayList;

public class Admin {
    private int adminId;
    private String username;
    private String passwd;

    private ArrayList<Integer> idOfParentsPaymentWaiting;

    public Admin(int admin_id, String username, String passwd, ArrayList<Integer> idOfParentsPaymentWaiting) {
        this.adminId = admin_id;
        this.username = username;
        this.passwd = passwd;
        this.idOfParentsPaymentWaiting = idOfParentsPaymentWaiting;
    }

    public void submitChild(int childId, int lessonId){
        // from db addLessonToChild(int childId, int lessonId)
        // from db addChildToLesson(int childId, int lessonId)
        // from db removeLessonFromChildWaitingList(int childId, int lessonId)

    }

    public void createNewLesson(){

    }

    public void getAllLessons(){

    }

    public void getActiveLessons(){

    }

    public void getInactiveLessons(){

    }

    public void getAllTeachers(){

    }

    public void getKidsByParentId(){

    }

    public void getAllParents(){

    }

    public void getTeachersBySubject(){

    }

    public void cancelLessonByTeacherIdAndDay(){

    }

    public void deactivateLesson(){

    }

    public void activateLesson(){

    }

    public void createNewSubject(){

    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public ArrayList<Integer> getIdOfParentsPaymentWaiting() {
        return idOfParentsPaymentWaiting;
    }

    public void setIdOfParentsPaymentWaiting(ArrayList<Integer> idOfParentsPaymentWaiting) {
        this.idOfParentsPaymentWaiting = idOfParentsPaymentWaiting;
    }
}
