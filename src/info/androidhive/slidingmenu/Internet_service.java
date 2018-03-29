package info.androidhive.slidingmenu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Internet_service {
	
	
	
	public static boolean checkInternetConenction(Context c){
		
	      ConnectivityManager check = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	      if (check != null) 
	      {
	         NetworkInfo[] info = check.getAllNetworkInfo();
	         if (info != null) 
	            for (int i = 0; i <info.length; i++) 
	            if (info[i].getState() == NetworkInfo.State.CONNECTED)
	            {
	               // Toast.makeText(c, "Internet is connected",Toast.LENGTH_SHORT).show();
	               return true;
	            }

	      }
	      else
	      {
	         Toast.makeText(c, "not conencted to internet",Toast.LENGTH_SHORT).show();
	       }
	      return false;
	   }
	
	public static String getUdId(Context c)
	{

		TelephonyManager TelephonyMgr = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE); 
		String m_deviceId = TelephonyMgr.getDeviceId();
		return m_deviceId;
	}
		
}
