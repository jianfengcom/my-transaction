package com.example.transaction.cac.util;

public class StringUtil {

    public static String getFileName(String src) {
        return src.substring(src.lastIndexOf("/") + 1);
    }

}
