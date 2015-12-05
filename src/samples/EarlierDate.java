package samples;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EarlierDate {
	public static void main(String[] args) {
		String strDate = "2";
		
		Integer intdate = Integer.parseInt(strDate);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		//String CurrentDate = df.format(date);		
		
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -intdate);
		
		Date calDate = cal.getTime();
		
		String lastDate = df.format(calDate);
		
		try{
			Date printDate = df.parse(lastDate);
			System.out.println(df.format(printDate));
		}catch(ParseException pe){
			pe.printStackTrace();
		}
		
	
	}
}
