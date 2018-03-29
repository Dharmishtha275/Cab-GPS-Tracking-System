/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.noti.pkg;

import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.slidingmenu.ChatFragment;
import info.androidhive.slidingmenu.MainActivity;
import info.androidhive.slidingmenu.R;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint.Join;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * This {@code IntentService} does the actual handling of the GCM message.
 * {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    
   // String appurl, weburl, appname, baseapp,des;
    String isjob;
    
    SharedPreferences usersettingPrefs,shar_test,shar_state;

    
    public GcmIntentService() {
        super("GcmIntentService");
        //shar_test=getSharedPreferences("test",Context.MODE_PRIVATE);
        
    }
    public static final String TAG = "PhotoWalletGCM";

    @Override
    protected void onHandleIntent(Intent intent) {
    	usersettingPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	shar_test=getApplicationContext().getSharedPreferences("test",Context.MODE_PRIVATE);
    	shar_state=getApplicationContext().getSharedPreferences("State_File",Context.MODE_PRIVATE);
    	
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM will be
             * extended in the future with new message types, just ignore any message types you're
             * not interested in, or that you don't recognize.
             */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());
            // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	
            	String chatMsg = extras.toString();
            	String s = chatMsg.substring(8,chatMsg.length()-2);//{"registrationid":[did],"data"":{"message":"ddsd","isjob":0}}
              
            	//String[] sarray = s.split(",");
            	//"data"":{"message":"ddsd","isjob":0}
            	//JSONObject jobj;
            	String isjob="0";
            	/*try {
            		JSONObject jsonobj = new JSONObject(s);
            		//JSONObject jobj = jsonobj.getJSONObject("data");
					 isjob = jsonobj.getString("isjob");
				}
            	catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
            	/*if(isjob.equals("0"))
            	{
            		
            	}
            	else
            	{
            		
            	}*/
            	String[] sarray = s.split(",");
            	for( int p=0;p<=sarray.length-1;p++)
                {
                	String[] parray = sarray[p].split("=");
                	if(parray[0].trim().equals("isjob"))
                	{
                		isjob= parray[1];
                		break;
                	}
                }
            	
            	/*  String[] sarray = s.split(",");
                for( int p=0;p<=sarray.length-1;p++)
                {
                	String[] parray = sarray[p].split("=");
                	if(parray[0].trim().equals("isjob"))
                	{
                		isjob= parray[1];
                	}*/
                	/*else
                		if(parray[0].trim().equals("appurl"))
                    	{
                			appurl=parray[1];
                    	}
                		else
                			if(parray[0].trim().equals("weburl"))
                        	{
                        		weburl = parray[1];
                        	}
                			else
                				if(parray[0].trim().equals("appname"))
                				{
                					appname = parray[1];
                				}
                				else
                					if(parray[0].trim().equals("des"))
                    				{
                    					des = parray[1];
                    				}
                    				else
                    				{
                    					String fack = parray[0];
                    				}*/
             //   }
            	
            	if(shar_test.getBoolean("is_login", false))//!true==Skip
            	{
            		
            		sendNotification(isjob);
                Log.i(TAG, "Received: " + extras.toString());
            	}
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
    
    
/*    ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE);
    
    List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            
            	 
    		         
    if(taskInfo.get(0).topActivity.getClassName().equals("com.contactrev.palstream.gcm.ChatMainActivity"))
    		         
    {

    }*/
    

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String id)
    {
    	// Simple pull of message from the full receipt.
//    	int i = msg.indexOf("message=");
//    	int j = msg.indexOf(", android.support");
//    	
//    	if (i > 0 && j > 0)
//    		msg = msg.substring(i + 8, j);
    	/*if(shar_test.getBoolean("is_login", false)==true)
    	{*/
    	/*if(usersettingPrefs.getBoolean("prefnotificationservice", true) == true)
		{*/
    		/*String notiTitle;
    		if(baseapp.trim().equals("1"))
    		{
    			notiTitle = "New App";
    		}
    		else
    		{
    			notiTitle = "Update App";
    		}*/
    
	        mNotificationManager = (NotificationManager)
	                this.getSystemService(Context.NOTIFICATION_SERVICE);
	
	        Intent intent = new Intent(this,MainActivity.class);
	        intent.putExtra("NotiMessage",(id.equals("0")?true:false));
	        //intent.putExtra("weburl", weburl.trim());
	       // intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        intent.setAction(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);
	        PendingIntent contentIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    
    	
	        
	        
	        NotificationCompat.Builder mBuilder =
	                new NotificationCompat.Builder(this)
	        .setSmallIcon(R.drawable.ic_logo)
	        .setContentTitle("New Event !")
	        .setStyle(new NotificationCompat.BigTextStyle()
	        .bigText((id.equals("0")?"New Message From Admin":"New JOb")))
	        .setSound(Uri.parse("android.resource://" + getApplication().getPackageName() + (id.equals("0")?"/raw/newmessage":"/raw/newjob")))
	        .setContentText((id.equals("0")?"New Message From Admin":"New JOb"));
	        
	        //mBuilder.sound = ;
	
	       mBuilder.setContentIntent(contentIntent);
	       mBuilder.setAutoCancel(true);
	       
	       if(id=="1")//0 message 1 job
	       {
	    	   if(shar_state.getInt("State", 0)==0)
	    		   mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	       }
	       else
	       mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		//}
    }
    /*public String get_notificationAIM(String s)
    {
    	int i =Integer.parseInt(s.trim());
    	switch (i) {
		case 1:
			return "TRY OUR OHTER APP";
		case 2:
			return "UPDATE APPLICATION";
		case 3:
			return "GIVE US REVIEWS!!";
		case 4:
			return "THANKS FOR DOWNLOAD";
		case 5:
			return "NEW VERSION AVAILABLE";
		default:
			break;
		}
    	
    	return "";
    }*/
}
