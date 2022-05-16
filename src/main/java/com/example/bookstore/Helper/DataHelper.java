package com.example.bookstore.Helper;

import java.text.SimpleDateFormat;

public class DataHelper {
    public static String toStirng(String date){
        if(date == null)
            return "";
        return new SimpleDateFormat("yyy-MM-dd").format(date);
    }
}
