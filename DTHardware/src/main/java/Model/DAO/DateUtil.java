package Model.DAO;

import java.util.Calendar;
import java.util.Scanner;


public class DateUtil
{
    public static String getStringFromCalendar(Calendar data) {
        if(data==null)
            return null;
        return data.get(Calendar.YEAR)+"-"+ prepMonth(data) +"-"+ data.get(Calendar.DAY_OF_MONTH)+" "+ data.get(Calendar.HOUR_OF_DAY)+":"+ data.get(Calendar.MINUTE)+":"+ data.get(Calendar.SECOND);
    }

    public static Calendar getCalendarFromString(String data)
    {
        if(data==null)
            return null;
        Calendar calendar=Calendar.getInstance();
        String appoggio=data.substring(0, 4);
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

    public static Calendar getCalendarFromStringWithSubstring(String data)
    {
        if (data == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        String appoggio = data.substring(0, 4);
        calendar.set(Calendar.YEAR, Integer.parseInt(appoggio));
        appoggio = data.substring(5, 7);
        calendar.set(Calendar.MONTH, Integer.parseInt(appoggio) - 1);
        appoggio = data.substring(8, 10);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(appoggio));
        appoggio = data.substring(11, 13);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(appoggio));
        appoggio = data.substring(14, 16);
        calendar.set(Calendar.MINUTE, Integer.parseInt(appoggio));
        appoggio = data.substring(17, 19);
        calendar.set(Calendar.SECOND, Integer.parseInt(appoggio));
        return calendar;
    }

    private static int prepMonth(Calendar data)
    {
        return data.get(Calendar.MONTH)+1;
    }

	//2024-06-01 00:00:01
    //2021-07-02 12:56:41
}
