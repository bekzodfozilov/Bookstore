package com.example.bookstore.Helper;

public class StringHelper {

    public static boolean Isvalid(String nameuz) {
        if(nameuz != null) {
            return nameuz.trim().length() > 0;
        }
        return false;
    }
}
