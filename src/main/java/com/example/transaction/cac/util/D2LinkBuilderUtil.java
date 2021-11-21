package com.example.transaction.cac.util;

public class D2LinkBuilderUtil {

    // 三小时下载链接有效
    public static final int three_hour_expires = 60 * 60 * 3;

    public static String buildSimpleToken(long masterId, long linkId, int location) {
        String time = String.valueOf((System.currentTimeMillis()) + (three_hour_expires * 1000));
        String masterIdStr = String.valueOf(masterId);
        String linkIdId = String.valueOf(linkId);
        String idStr = masterIdStr.length() + masterIdStr + linkIdId.length() + linkId + time + location;

        return Mid2Uid(idStr);
    }

    public static String Mid2Uid(String str10) {
        String mid = "";
        for (int i = str10.length() - 7; i > -7; i = i - 7) // 从最后往前以7字节为一组读取字符
        {
            int offset = i < 0 ? 0 : i;
            int len = i < 0 ? str10.length() % 7 : 7;
            String temp = str10.substring(offset, offset + len);
            String url = IntToEnode62(Integer.valueOf(temp));
            for (int j = 0; j < 4 - url.length(); j++) {
                url = "0" + url;
            }
            mid = url + mid;
        }
        return mid;
    }

    private static String[] str62keys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };

    public static String IntToEnode62(Integer int10) {
        String s62 = "";
        int r = 0;
        while (int10 != 0) {
            r = int10 % 62;
            s62 = str62keys[r] + s62;
            int10 = (int) Math.floor(int10 / 62.0);
        }
        return s62;
    }
}
