package com.service.pkg;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BrodcastReceiver extends BroadcastReceiver 
{
	//SharedPreferences shre;

	  // Restart service every 30 seconds
	  private static final long REPEAT_TIME = 1000 * 30;

	  @Override
	  public void onReceive(Context context, Intent intent)
	  {
	//	  shre=context.getSharedPreferences(Constant_value.LimoRequest , context.MODE_PRIVATE);
		  
			    AlarmManager service = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			    Intent i = new Intent(context, MySchedulReceiver.class);
			    PendingIntent pending = PendingIntent.getBroadcast(context, 0, i,PendingIntent.FLAG_CANCEL_CURRENT);
			    Calendar cal = Calendar.getInstance();
			    // Start 30 seconds after boot completed
			    cal.add(Calendar.SECOND,5);
		//	    if(!shre.getBoolean(Constant_value.SH_login1_0, false))//!true==Skip
		//		 {
		//			service.cancel(pending);
				
		//			Log.e(" Stop  Services", "Stop  Services /"+shre.getBoolean(Constant_value.SH_login1_0, false));
		//		 }
			    //
			    // Fetch every 30 seconds
			    // InexactRepeating allows Android to optimize the energy consumption
	//		    Log.e(" Call Services", "call Services/"+shre.getBoolean(Constant_value.SH_login1_0, false));
			    service.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), REPEAT_TIME, pending);
			   
				
	    

	    // service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
	    // REPEAT_TIME, pending);

	  }
	} 