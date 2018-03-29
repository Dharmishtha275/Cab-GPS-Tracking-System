package info.androidhive.slidingmenu;
import java.io.IOException;

import org.json.JSONObject;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.service.pkg.GPSTracker;
import com.service.pkg.MyServices.Asyn_Service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	Button btn_login;
	String username;
	String password;
	TextView txt1;
	EditText edt_1,edt_2;
	GPSTracker gps;
	SharedPreferences shar_test,shar_token;
	
	GoogleCloudMessaging gcm;
	String Token;
	
	Editor edit_test,edit_token;
	JSON_Parser jParser = new JSON_Parser();
	ProgressDialog pDialog;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getActionBar().setTitle("Login");
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#79BAEC")); 
		getActionBar().setBackgroundDrawable(colorDrawable);
		txt1=(TextView)findViewById(R.id.textView1);
		
		gps=new GPSTracker(getApplicationContext());
		
		shar_test=getSharedPreferences("test",Context.MODE_PRIVATE);
		edit_test=shar_test.edit();
		
		context = this;
		
		
		shar_token=getSharedPreferences("token",Context.MODE_PRIVATE);
		edit_token=shar_token.edit();
		
		
		
		// GCM register method call
		
		if(shar_test.getBoolean("is_login",false))
		{
			
			Intent i=new Intent(getApplicationContext(),MainActivity.class);
			startActivity(i);
			finish();
		}
		btn_login=(Button) findViewById(R.id.btn_login);
		edt_1=(EditText) findViewById(R.id.edittext1);
		edt_2=(EditText) findViewById(R.id.edittext2);
		
		
		
	}
	
	class Async_Login extends AsyncTask<String, String, String>
	{
		JSONObject jobj;
		String strParam="",username="",password="";
		
		
		public Async_Login(String username, String password)
		{
			this.username = username;
			this.password = password;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setTitle("Loading");
			pDialog.setMessage("Please Wait..");
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			strParam = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
			try
			{
				jobj = jParser.makeHTTPPOST(Constant.WebURL, "POST", strParam.replace("/n", ""));
				
				if(jobj.getString("status").equalsIgnoreCase("Sucess"))
				{ 
					//JSONArray jsonarray=jobj.getJSONArray("Driver_Id");
					String d_id = jobj.getString("Driver_Id");
					
					edit_test.putBoolean("is_login",true);
					edit_test.putString("username",username);
					edit_test.putString("password",password);
					edit_test.putString("driver_id",d_id);
					edit_test.commit();
					
					BackGroundTask();	
					
					Intent i=new Intent(getApplicationContext(),MainActivity.class);
					i.putExtra("name", edt_1.getText().toString());
					startActivity(i);
					finish();
					
					Log.d("login","login");
					
				}
				else
				{
					//Toast.makeText(getApplicationContext(), "Login f", Toast.LENGTH_LONG).show();
					Log.d("fail", "not login");
				}
				
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super .onPostExecute(result);
			pDialog.dismiss();
			try
			{
				final String msg=jobj.getString("msg");
				if(jobj.getString("status").equalsIgnoreCase("Sucess"))
				{
					
					
					Intent startServiceIntent = new Intent();
				    startServiceIntent.setAction("start.location.send");
				    sendBroadcast(startServiceIntent);
					
					Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
					//Log.d("login", "login");
					
				}
				else
				{
					
					txt1.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							txt1.setText(msg);
						}
					});
					
					Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
//					Log.d("fail", "not login");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		}
	}

	
	public void onclick_login(View v)
	{  
	/*edit_test.putBoolean("is_login",true);
	edit_test.putString("username",username);
	edit_test.putString("password",password);
	
	edit_test.commit();*/
	
	//btn_login.setVisibility(View.GONE);	
	username=edt_1.getText().toString();
	password=edt_2.getText().toString();
	//Toast.makeText(getApplicationContext(),username+"Login Click and data stored",Toast.LENGTH_SHORT).show();
	
	
	if(Internet_service.checkInternetConenction(getApplicationContext()))
	{
		new Async_Login(username, password).execute();
		
		
//		Intent i=new Intent(getApplicationContext(),MainActivity.class);
//		i.putExtra("name", edt_1.getText().toString());
//	startActivity(i);
//	}
	}
	else
	{
		Toast.makeText(getApplicationContext(), "Check Internet Connection",Toast.LENGTH_SHORT).show();
	
		
	}
		
	}
	
	private void BackGroundTask()
	{
		 
	        	
	        	
		
	        	new Asyn_driver_latlang(shar_test.getString("driver_id","0"),gps.getLatitude(),gps.getLongitude()).execute();
	        
		new AsyncTask<String, String, String>()
		{

			@Override
			protected String doInBackground(String... params)
			{
				// TODO Auto-generated method stub
				 try 
				 {
					if(gcm==null)
					{
						gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
						
					}
					
						Token = gcm.register(Constant.sender_id);
						
						edit_token.putString("token",Token);
						edit_token.commit();
						new Async_Token(shar_test.getString("driver_id","0"), Token).execute();
						
						
						
					// you have to send token to service with Driver id :)
						
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return null;
			}
		}.execute();
	}
		
	class Async_Token extends AsyncTask<String,String,String>
			{
		JSONObject jobj;
		String strParam="",driver_id="",Token="";
		
		public Async_Token(String driver_id,String Token)
		{
			this.driver_id=driver_id;
			this.Token=Token;
			
		}

			
				
				@Override
				protected String doInBackground(String... params) {
					// TODO Auto-generated method stub
					strParam = "{\"id\":\""+driver_id+"\",\"token\":\""+Token+"\"}";
					try
					{
						jobj = jParser.makeHTTPPOST(Constant.WebURL_Token, "POST", strParam);
						if(jobj.getString("status").equalsIgnoreCase("Sucess"))
						{ 
							//edit_token.putBoolean("reg_in_sertver", true);
							//edit_token.commit();
							
						}
					}
					catch(Exception e)
					{
						
					}
					return null;
				}
				
			
			}
class Asyn_driver_latlang extends AsyncTask<String,String,String>
{
	JSONObject jobj;
	String strparm="";
	String Driver_Id;
	Double Long,Lati;
	
	public Asyn_driver_latlang(String Driver_Id,Double Lati,Double Long)
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
	
