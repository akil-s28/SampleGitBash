package com.ninza.hrm.api.genericUtility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtils 
{

	
public int getRandomNumber()
{
	Random random=new Random();
	int randomInt=random.nextInt(5000);
	return randomInt;
}

public String getLocalDateTime()
{
	String localDateTime = LocalDateTime.now().toString().replace(':', '-');
	return localDateTime;
}

public String getCurrentDateYYYYMMdd()
{
	Date dateObj= new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
	String actDate=sdf.format(dateObj);
	return actDate;
}

public String getCurrentDateddMMYYYY()
{
	Date dateObj= new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
	String actDate=sdf.format(dateObj);
	return actDate;
}

public String getEndDateYYYYMMdd(int days)
{
	Date dateObj=new Date();
	SimpleDateFormat simpleDate=new SimpleDateFormat("yyyy-MM-dd");	
	simpleDate.format(dateObj);

	Calendar cal=simpleDate.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH, days);
	String endDate=simpleDate.format(cal.getTime());
	return endDate;
}

public String getEndDateddMMYYYY(int days)
{
	Date dateObj=new Date();
	SimpleDateFormat simpleDate=new SimpleDateFormat("dd-MM-yyyy");	
	simpleDate.format(dateObj);

	Calendar cal=simpleDate.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH, days);
	String endDate=simpleDate.format(cal.getTime());
	return endDate;
}
}
