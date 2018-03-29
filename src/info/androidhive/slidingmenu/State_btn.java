package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.R.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class State_btn  extends Activity{

	private GoogleMap googleMap;
	GPSTracker gps;
	
	double latitude, logitude;
	Button btnOnLocation,btnOnFree,btnOnWay,btnOnWork;
	SharedPreferences shar_state,shar_test,shar_job;
	Editor edt_state,edt_test,edt_job;
	JSON_Parser jParser = new JSON_Parser();
	ProgressDialog pDialog ;
	Context context;
	
	LatLng source_latlong, Dest_latlong,driver_latlong;
	
	Float user_s_lat,user_s_long,user_d_lat,user_d_long;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.state_btn);
		getActionBar().setTitle("State");
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#79BAEC")); 
		getActionBar().setBackgroundDrawable(colorDrawable);
				
				shar_state=getSharedPreferences("State_File", Context.MODE_PRIVATE);
				 shar_test=this.getSharedPreferences("test",Context.MODE_PRIVATE);
				 shar_job=this.getSharedPreferences("job",Context.MODE_PRIVATE);
				edt_state=shar_state.edit();
				edt_test=shar_test.edit();
				edt_job=shar_job.edit();
				
				btnOnFree=(Button) findViewById(R.id.btnfree);
				btnOnWay=(Button) findViewById(R.id.btnway);		
				btnOnLocation=(Button) findViewById(R.id.btnlocation);		
				btnOnWork=(Button) findViewById(R.id.btnwork);
				
				State_Change(shar_state.getInt("State", 0));//1
				gps = new GPSTracker(getApplicationContext());
				
				user_s_lat =shar_job.getFloat("s_lat", 0);
				user_s_long = shar_job.getFloat("s_long", 0);
				user_d_lat = shar_job.getFloat("d_lat", 0);
				user_d_long = shar_job.getFloat("d_long", 0);
				
				Dest_latlong = new LatLng(user_d_lat, user_d_long);
				source_latlong = new LatLng(user_s_lat, user_s_long);
				driver_latlong=new LatLng(gps.getLatitude()	, gps.getLongitude());
				
				
				String url = getDirectionsUrl(source_latlong, Dest_latlong);	
				String url1=getDirectionsUrl(driver_latlong,source_latlong);
				
				DownloadTask downloadTask = new DownloadTask();
				DownloadTask downloadTask2 = new DownloadTask();
				
				
				// Start downloading json data from Google Directions API
				downloadTask.execute(url);
				downloadTask2.execute(url1);
				/*DownloadTask dl=new DownloadTask();
				dl.execute(url1);*/
				
				
				//State_Change(shar.getInt("State",0));
				
				btnOnFree.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						edt_state.putInt("State", -1);
						edt_state.commit();
						
//						edt_job.putInt("job_id", 0);
//						edt_job.commit();
						new Asyn_State(shar_test.getString("driver_id","0"),""+shar_job.getInt("job_id",0),"On_Free",10).execute();
					
						
						Intent i=new Intent(getApplicationContext(),MainActivity.class);
						startActivity(i);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						
												
					}
				});
				
				btnOnLocation.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						State_Change(3);
						
					}
				});
				
				btnOnWay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						State_Change(2);
					}
				});
				
				btnOnWork.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						State_Change(0);
					}
				});
				
				
				
				latitude = gps.getLatitude();
				logitude = gps.getLongitude();
				
				MarkerOptions marker = new MarkerOptions()
				.position(new LatLng(latitude,logitude));
				
				Bitmap bitmap  = BitmapFactory.decodeResource(getResources(), R.drawable.cab);
				//notBuilder.setLargeIcon(largeIcon);
				marker.icon(BitmapDescriptorFactory.fromBitmap(bitmap));//defaultMarker(bitmap));//BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				
				MarkerOptions marker_s = new MarkerOptions().position(new LatLng(user_s_lat, user_s_long));
				marker_s.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title("SOURCE");
				
				MarkerOptions marker_d = new MarkerOptions().position(new LatLng(user_d_lat, user_d_long));
				marker_d.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("Destination");
				
				
				initilizeMap();
				CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(latitude,logitude)).zoom(20).build();
				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));
				googleMap.addMarker(marker);
				googleMap.addMarker(marker_s);
				googleMap.addMarker(marker_d);
				/*
				 Polyline line = googleMap.addPolyline(new PolylineOptions()
		         .add(new LatLng(user_s_lat,user_s_long),new LatLng(user_d_lat,user_d_long))
		         .width(5)
		         .color(Color.RED));*/
	}
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
		java.lang.System.exit(0);
	}

	public void State_Change(int i)
	{
		switch (i) {
		case 0:
			if(Internet_service.checkInternetConenction(getApplicationContext()))
			{
				if(shar_state.getInt("State", 0)==0)
				{
					CHANGEBUTTON_HIS(0);
				}
				else
			new Asyn_State(shar_test.getString("driver_id","0"),""+shar_job.getInt("job_id",0),"On_Work",i).execute();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Check Internet Connection",Toast.LENGTH_SHORT).show();
			}
			break;
			
		case 1:
			if(Internet_service.checkInternetConenction(getApplicationContext()))
			{
				if(shar_state.getInt("State", 1)==1)
				{
					CHANGEBUTTON_HIS(1);
				}
				else
			new Asyn_State(shar_test.getString("driver_id","0"),""+shar_job.getInt("job_id",0),"On_Free",i).execute();
			}
			else
			{
			Toast.makeText(getApplicationContext(), "Check Internet Connection",Toast.LENGTH_SHORT).show();
			}
			break;
			
		case 2:
			if(Internet_service.checkInternetConenction(getApplicationContext()))
			{
				if(shar_state.getInt("State", 2)==2)
				{
					CHANGEBUTTON_HIS(2);
				}
				else
			new Asyn_State(shar_test.getString("driver_id","0"),""+shar_job.getInt("job_id",0),"On_Way",i).execute();
			}
			else
			{
			Toast.makeText(getApplicationContext(), "Check Internet Connection",Toast.LENGTH_SHORT).show();
			}
			
			break;
			
		case 3:
			if(Internet_service.checkInternetConenction(getApplicationContext()))
			{
				if(shar_state.getInt("State", 3)==3)
				{
					CHANGEBUTTON_HIS(3);
				}
				else
			new Asyn_State(shar_test.getString("driver_id","0"),""+shar_job.getInt("job_id",0),"On_Location",i).execute();
			}
			else
			{
			Toast.makeText(getApplicationContext(), "Check Internet Connection",Toast.LENGTH_SHORT).show();
			}
			
			break;

		default:
			
			
			break;
		}
	}
	
	
	public void onClickFreeOn()
	{
			
			btnOnWork.setVisibility(View.GONE);
			btnOnLocation.setVisibility(View.GONE);
			btnOnFree.setVisibility(View.GONE);
			btnOnWay.setVisibility(View.VISIBLE);
			
			
			
			edt_state.putInt("State",1);
			edt_state.commit();
			
			
			
	}
	
	public void onClickWayOn()
	{
		
		btnOnWay.setVisibility(View.GONE);
		btnOnWork.setVisibility(View.GONE);
		btnOnLocation.setVisibility(View.VISIBLE);
		btnOnFree.setVisibility(View.GONE);
		edt_state.putInt("State",2);
		edt_state.commit();
		
	}
	
	public void onClickLocationOn()
	{
		btnOnWay.setVisibility(View.GONE);
		btnOnWork.setVisibility(View.VISIBLE);
		btnOnLocation.setVisibility(View.GONE);
		btnOnFree.setVisibility(View.VISIBLE);
		edt_state.putInt("State",3);
		edt_state.commit();	
		
	}
	
	
	
	public void onClickWorkOn()
	{
		
		btnOnWay.setVisibility(View.GONE);
		btnOnWork.setVisibility(View.GONE);
		btnOnLocation.setVisibility(View.GONE);
		btnOnFree.setVisibility(View.VISIBLE);
		
		
		//edt.putInt("State",0);
		
	}
	
	public class Asyn_State extends AsyncTask<String, String, String>
	{
		JSONObject jobj;
		String state;

		String strParam="";
		String job_id,driver_id;
		
		
		int case_i;
		
		
		public Asyn_State (String driver_id,String job_id,String state,int case_i)
		{
			this.state = state;
			this.job_id = job_id;
			this.driver_id =driver_id;
			this.case_i =case_i;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(State_btn.this);
			pDialog.setTitle("Loading");
			pDialog.setMessage("Please Wait..");
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			 String strParam = "{\"Job_Id\":\""+job_id+"\",\"Id\":\""+driver_id+"\",\"status\":\""+state+"\"}";
			try
			{
				jobj = jParser.makeHTTPPOST(Constant.WebURL_status, "POST",strParam);
				if(jobj.getString("status").equalsIgnoreCase("Sucess"))
				{
					
					btnOnFree.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							CHANGEBUTTON_HIS(case_i);
						}
					});
					
				}
				else
				{
					
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
			super.onPostExecute(result);
			pDialog.dismiss();
		}
	}
	
	@SuppressLint("NewApi")
	private void initilizeMap()
	{
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) 
			{
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	
	public void CHANGEBUTTON_HIS(int i)
	{
		switch (i)
		{
		case 0:
			onClickWorkOn();
			break;
		case 1:
			onClickFreeOn();
			
			break;
		case 2:
			onClickWayOn();
			break;
		case 3:
			onClickLocationOn();
			break;
		default:
			Toast.makeText(getApplicationContext(),"Job is Done",Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
	private class DownloadTask extends AsyncTask<String, Void, String>{			
		
		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {
				
			// For storing data from web service
			String data = "";
					
			try{
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;		
		}
		
		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);			
			
			ParserTask parserTask = new ParserTask();
			
			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
				
		}		
	}
	
	/** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
    	
    	// Parsing the data in non-ui thread    	
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
			
			JSONObject jObject;	
			List<List<HashMap<String, String>>> routes = null;			           
            
            try{
            	jObject = new JSONObject(jsonData[0]);
            	com.google.service.rout.DirectionsJSONParser parser = new com.google.service.rout.DirectionsJSONParser();
            	
            	// Starts parsing data
            	routes = parser.parse(jObject);    
            }catch(Exception e){
            	e.printStackTrace();
            }
            return routes;
		}
		
		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();
			
			// Traversing through all the routes
			for(int i=0;i<result.size();i++){
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();
				
				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);
				
				// Fetching all the points in i-th route
				for(int j=0;j<path.size();j++){
					HashMap<String,String> point = path.get(j);					
					
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);	
					
					points.add(position);						
				}
				
				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(7);
				lineOptions.color(Color.RED);	
				
			}
			
			// Drawing polyline in the Google Map for the i-th route
			googleMap.addPolyline(lineOptions);							
		}			
    }   
    
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url 
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                
                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }
    
    private String getDirectionsUrl(LatLng origin,LatLng dest){
		
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;		
		
					
		// Sensor enabled
		String sensor = "sensor=false";			
					
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;
					
		// Output format
		String output = "json";
		
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		
		
		return url;
	}
	

}
