package backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {
    
    private static DateFormat dateFormat;
    private static Date date = new Date();;
    
    public static String getCompleteDate() {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
    
    public static String getDay() {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }
    
    public static String getHour() {
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }
}
