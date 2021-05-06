package Model.DAO;

import java.util.Calendar;


public class dateUtil
{
    public static String PrepTime (Calendar data)  {

        String stringDate = data.get(Calendar.YEAR)+"-"+ data.get(Calendar.MONTH)+"-"+ data.get(Calendar.DAY_OF_WEEK)+" "+ data.get(Calendar.HOUR_OF_DAY)+":"+ data.get(Calendar.MINUTE)+":"+ data.get(Calendar.SECOND);
        return stringDate;
    }
	//2024-06-01 00:00:01
}
