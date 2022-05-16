package com.example.bookstore.Helper;

public class IntegerHelper {
    public static boolean BoolInt(String id) {
        if(id == null)
            return false;
        try {
            Integer.parseInt(id);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static Integer Isvalid(String id) {
        if(id == null)
            return null;
        try {
            return Integer.parseInt(id);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
