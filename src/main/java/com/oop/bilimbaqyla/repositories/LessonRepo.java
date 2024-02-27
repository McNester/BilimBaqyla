package com.oop.bilimbaqyla.repositories;


import com.oop.bilimbaqyla.models.Lesson;
import com.oop.bilimbaqyla.services.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@Repository
public class LessonRepo {

    DataBaseConnection db = new DataBaseConnection();

    public ArrayList<Lesson> getAllByChildId(int id){
        ArrayList<Integer> lessonsIdList = getIdOfLessonsByChildId(id);
        ArrayList<Lesson> lessons = new ArrayList<>();

        lessonsIdList.forEach((n) ->{
            lessons.add(getLessonById(id));
        });

        return lessons;
    }

    public void create(Lesson lesson){
        // Convert int array to a string that matches PostgreSQL integer array format
        String ageString = Arrays.toString(lesson.getAge())
                .replace("[", "{")
                .replace("]", "}")
                .replace(" ", "");


        String query = String.format(Locale.US, "insert into lesson ( duration, subjectid, price,timelong,weekday,isactive,iscanceled,teacherid,age) values('%d','%d','%f','%d','%d','%b',%b,%d,%s);",
                lesson.getDuration(),
                lesson.getSubjectId(),
                lesson.getPrice(),
                lesson.getTimelong(),
                lesson.getWeekDay(),
                lesson.isActive(),
                lesson.isCanceled(),
                lesson.getTeacherid(),
                ageString);


        db.post(query);

    }

    public Lesson getLessonByTeacherId(int id){
        ResultSet rs = db.get("select * from lesson where teacherid = "+id);

        try{
            while(rs.next()){
                int lessonid = rs.getInt("lessonid");
                long duration = rs.getLong("duration");
                int subjectid = rs.getInt("subjectid");
                double price = rs.getDouble("price");
                long timelong = rs.getLong("timelong");
                int weekday = rs.getInt("weekday");
                boolean isactive = rs.getBoolean("isactive");
                boolean iscanceled = rs.getBoolean("iscanceled");


                //setting sql array to int[2] age;
                Array age = rs.getArray("age");
                Integer[] integerArray = (Integer[])age.getArray();
                int[] ageArray = new int[integerArray.length];

                for (int i = 0; i < integerArray.length; i++) {
                    ageArray[i] = integerArray[i];
                }

                ArrayList<Integer> childrenIdList = getIdOfChildrenByLessonId(id);


                return new Lesson(lessonid,duration,subjectid,price,timelong,weekday,isactive,iscanceled,childrenIdList,id,ageArray);


            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }

        return null;
    }

    public Lesson getLessonById(int id){
        ResultSet rs = db.get("select * from lesson where lessonid = "+id);

        try{
            while(rs.next()){
                long duration = rs.getLong("duration");
                int subjectid = rs.getInt("subjectid");
                double price = rs.getDouble("price");
                long timelong = rs.getLong("timelong");
                int weekday = rs.getInt("weekday");
                boolean isactive = rs.getBoolean("isactive");
                boolean iscanceled = rs.getBoolean("iscanceled");
                int teacherid = rs.getInt("teacherid");


                //setting sql array to int[2] age;
                Array age = rs.getArray("age");
                Integer[] integerArray = (Integer[])age.getArray();
                int[] ageArray = new int[integerArray.length];

                for (int i = 0; i < integerArray.length; i++) {
                    ageArray[i] = integerArray[i];
                }

                ArrayList<Integer> childrenIdList = getIdOfChildrenByLessonId(id);


                return new Lesson(id,duration,subjectid,price,timelong,weekday,isactive,iscanceled,childrenIdList,teacherid,ageArray);


            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }

        return null;
    }


    public void addChildToLesson(int childid,int lessonid){
        String query = String.format(Locale.US,"insert into enrolledchildlessonlink (childid,lessonid) values(%d,%d)",
                childid,
                lessonid);

        db.post(query);
    }
    public ArrayList<Lesson> getLessonsByActivness(int activness){
        ResultSet rs = db.get("select * from lesson where isactive = "+activness);

        ArrayList<Lesson> lessons = new ArrayList<>();
        try{
            while(rs.next()){
                int lessonid = rs.getInt("lessonid");
                long duration = rs.getLong("duration");
                int subjectid = rs.getInt("subjectid");
                double price = rs.getDouble("price");
                long timelong = rs.getLong("timelong");
                int weekday = rs.getInt("weekday");
                boolean isactive = rs.getBoolean("isactive");
                boolean iscanceled = rs.getBoolean("iscanceled");
                int teacherid = rs.getInt("teacherid");


                //setting sql array to int[2] age;
                Array age = rs.getArray("age");
                Integer[] integerArray = (Integer[])age.getArray();
                int[] ageArray = new int[integerArray.length];

                for (int i = 0; i < integerArray.length; i++) {
                    ageArray[i] = integerArray[i];
                }

                ArrayList<Integer> childrenIdList = getIdOfChildrenByLessonId(lessonid);


                lessons.add( new Lesson(lessonid,duration,subjectid,price,timelong,weekday,isactive,iscanceled,childrenIdList,teacherid,ageArray));


            }
            return lessons;

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }

        return null;
    }
    public ArrayList<Integer> getIdOfLessonsByChildId(int id){
        ResultSet rs = db.get("select lessonid from enrolledchildlessonlink where childid = "+id);
        ArrayList<Integer> idListOfLessons = new ArrayList<>();

        try{
            while(rs.next()){
                idListOfLessons.add(rs.getInt("lessonid"));
            }
            return idListOfLessons;

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }

        return null;

    }

    public ArrayList<Integer> getIdOfChildrenByLessonId(int id){
        ResultSet rs = db.get("select childid from enrolledchildlessonlink where lessonid = "+id);
        ArrayList<Integer> idListOfChildrenIds = new ArrayList<>();

        try{
            while(rs.next()){
                idListOfChildrenIds.add(rs.getInt("childid"));
            }
            return idListOfChildrenIds;

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }

        return null;
    }
}
