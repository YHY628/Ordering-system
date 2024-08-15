package com.yhy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat simpleDateFormat;

    public static Date toDate(String str, String format) throws Exception {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(str);
    }

}
