package Model.DAO;

import java.util.Calendar;


public class DateUtil
{
    public static String PrepTime (Calendar data) {

       // String stringDate = "\'"+data.get(Calendar.YEAR)+"-"+ prepMonth(data) +"-"+ data.get(Calendar.DAY_OF_MONTH)+" "+ data.get(Calendar.HOUR_OF_DAY)+":"+ data.get(Calendar.MINUTE)+":"+ data.get(Calendar.SECOND)+"\'";
        String stringDate = data.get(Calendar.YEAR)+"-"+ prepMonth(data) +"-"+ data.get(Calendar.DAY_OF_MONTH)+" "+ data.get(Calendar.HOUR_OF_DAY)+":"+ data.get(Calendar.MINUTE)+":"+ data.get(Calendar.SECOND);
        System.out.println(stringDate);
        return stringDate;
    }

    private static int prepMonth(Calendar data)
    {
        return data.get(Calendar.MONTH)+1;
    }
	//2024-06-01 00:00:01
}
