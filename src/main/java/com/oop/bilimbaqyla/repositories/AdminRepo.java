package com.oop.bilimbaqyla.repositories;

import com.oop.bilimbaqyla.models.Admin;
import com.oop.bilimbaqyla.services.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public class AdminRepo {
    DataBaseConnection db = new DataBaseConnection();
    public Admin getAdminById(int adminid){
        ResultSet rs = db.get("SELECT * FROM admins WHERE adminid = "+adminid);
        try {

            while (rs.next()) {
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                ArrayList<Integer> idOfParentsPaymentWaiting = getIdOfParentsWaiting(adminid);
                return new Admin(adminid,username,passwd, idOfParentsPaymentWaiting);
            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    public ArrayList<Integer> getIdOfParentsWaiting(int adminid){
        ArrayList <Integer> parentsId = new ArrayList<>();

        try{
            ResultSet rs = db.get("SELECT parentid FROM adminsparentslink WHERE adminid = "+adminid);
            while(rs.next()){
                parentsId.add(rs.getInt("parentid"));
            }
            return parentsId;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }
    public ArrayList<Admin> getAllAdmin(){
        ArrayList<Admin> admins = new ArrayList<>();
        try{
            ResultSet rs = db.get("select * from admin");
            while (rs.next()) {
                int id = rs.getInt("adminid");
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                ArrayList <Integer> idOfParentsWaiting = getIdOfParentsWaiting(id);

                admins.add(new Admin(id,username,passwd,idOfParentsWaiting));
            }
            return admins;
        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
        }
        return null;
    }


}
