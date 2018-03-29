package com.service.pkg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MySchedulReceiver extends BroadcastReceiver
{

	SharedPreferences shre;

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// TODO Auto-generated method stub
		 shre=context.getSharedPreferences("test",context.MODE_PRIVATE);
		 if(shre.getBoolean("is_login", false))//!true==Skip
		 {
		
			Intent service = new Intent(context, MyServices.class);
		    context.startService(service);
		 }
	}

}
