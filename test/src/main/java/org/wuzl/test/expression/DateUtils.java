package org.wuzl.test.expression;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parse(String input)  {
        DateFormat format = input.length() > 10 ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                : new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
