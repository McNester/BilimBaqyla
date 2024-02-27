package com.oop.bilimbaqyla.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsChecker {




    public boolean isNameValid(String name){
        if(name.length() <= 1){
            return false;
        }
        return true;
    }
    public boolean isLastnameValid(String lastname){
        if(lastname.length() <= 1){
            return false;
        }
        return true;

    }
    public boolean isPhoneValid(String phone){
        if(phone.length() < 11){
            return false;
        }
        String regex = "^\\+?\\d{10,11}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if(matcher.find() == true){
            return true;
        }
        return false;
    }

    public boolean isEmailValid(String email){
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.find() == true){
            return true;
        }
        return false;
    }
    public boolean isPasswdValid(String passwd){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passwd);
        if(matcher.find() == true){
            return true;
        }
        return false;
    }
}
