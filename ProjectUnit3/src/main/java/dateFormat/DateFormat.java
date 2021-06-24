package dateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateFormat {

	 public static Date parseDate(String date)
	    {
	        SimpleDateFormat format = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
	        Date dateRe = null;
	        try {
	            dateRe = format.parse(date);
	        } 
	        catch (ParseException ex) 
	        {
	            System.out.println(ex);
	        }
	        return dateRe;
	    }
}
