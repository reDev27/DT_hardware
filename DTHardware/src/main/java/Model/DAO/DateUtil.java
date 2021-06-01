package Model.DAO;

import java.util.Calendar;
import java.util.Scanner;


public class DateUtil
{
    public static String getStringFromCalendar(Calendar data) {
        String stringDate = data.get(Calendar.YEAR)+"-"+ prepMonth(data) +"-"+ data.get(Calendar.DAY_OF_MONTH)+" "+ data.get(Calendar.HOUR_OF_DAY)+":"+ data.get(Calendar.MINUTE)+":"+ data.get(Calendar.SECOND);
        return stringDate;
    }

    public static Calendar getCalendarFromString(String data)
    {
        Calendar calendar=Calendar.getInstance();
        Scanner scanner=new Scanner(data);
        scanner.useDelimiter("-");
        calendar.set(Calendar.YEAR, Integer.parseInt(scanner.next()));
        calendar.set(Calendar.MONTH, Integer.parseInt(scanner.next())-1);
        scanner.useDelimiter(" ");
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(scanner.next()));
        scanner.useDelimiter(":");
        String s=scanner.next();
        s=s.substring(1);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s));
        calendar.set(Calendar.MINUTE, Integer.parseInt(scanner.next()));
        calendar.set(Calendar.SECOND, Integer.parseInt(scanner.next()));
        return calendar;
    }

    private static int prepMonth(Calendar data)
    {
        return data.get(Calendar.MONTH)+1;
    }

	//2024-06-01 00:00:01
}
