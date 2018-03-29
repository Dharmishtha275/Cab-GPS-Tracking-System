package com.service.pkg;



import info.androidhive.slidingmenu.Constant;
import info.androidhive.slidingmenu.JSON_Parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class MyServices extends Service
{
	 SharedPreferences shre,shar_test,shar_driverlatlang;
	 Editor edt_driverlatlang;
	GPSTracker gps;
	
	JSON_Parser jParser = new JSON_Parser();
	//Shared_Custom obj_share;
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		 shar_test=getApplicationContext().getSharedPreferences("test",Context.MODE_PRIVATE);
		
		 
		//obj_share = new Shared_Custom(getApplicationContext());
		//shre = getApplicationContext().getSharedPreferences(Constant_value.LimoRequest, MODE_PRIVATE);
	}
	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) 
	{
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		gps = new GPSTracker(getApplicationContext());
		// check if GPS enabled		
	        if(gps.canGetLocation())
	        {
	        	
	        	double latitude = gps.getLatitude();
	        	double longitude = gps.getLongitude();
	        	String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(new Date());
	        	
	        	Log.e("service Started", "fianl good ");
	        	
	        	
	        	new Asyn_Service(shar_test.getString("driver_id","0"), latitude, longitude).execute();
	        	//{"lat":"33.555","long":"78.555","date":"2015-02-21 20:22:18"}
	       // 	String s="{\"lat\":\""+latitude+"\",\"long\":\""+longitude+"\",\"date\":\""+timeStamp+"\"}";
	        	
	        //	obj_share.add_latlong(s);
	        	
	       // 	if(Constant_method.checkConn(getApplicationContext()))
	       //// 	{
	  //      		new Asyn_location(getApplicationContext(),s).execute();
	        	//}
	        //\	else
	        	///{
	        		
	        //	}
	        	
	        	// \n is for new line
	        	
	        	//new Upload_location(,).execute();
	        	//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
	        }
	        else
	        {
	        	try {
	        		gps.showSettingsAlert();
				}
	        	catch (Exception e) 
	        	{
					// TODO: handle exception
	        		e.printStackTrace();
				}
	        	
	        }
		
		//System.out.println("Runing");
	}
	
	
	public class Asyn_Service extends AsyncTask<String, String, String>
	{
		JSONObject jobj;
		String strparm="";
		String Driver_Id;
		Double Long,Lati;
		
		public Asyn_Service(String Driver_Id,Double Lati,Double Long)
		{
			this.Driver_Id=Driver_Id;
			this.Long=Long;
			this.Lati=Lati;
			
		}
		 
			
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String strParam = "{\"DriverId\":\""+Driver_Id+"\",\"latitude\":\""+Lati+"\",\"longitude\":\""+Long+"\"}";
			try
			{
				jobj = jParser.makeHTTPPOST(Constant.WebURL_location, "POST", strParam);
				if(jobj.getString("status").equalsIgnoreCase("Sucess"))
				{ 
					Log.e("sucess","service call");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
