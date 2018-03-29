package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.list.ass.ListAdapter;
import com.list.ass.List_Ass;

public class HomeFragment extends Fragment  {
	ListView lstview;
	ArrayList<List_Ass> arrlst;
	ListAdapter lstadp;
	SharedPreferences shar_state,shar_test,shar_job;
	Editor edt_job,edt_state;
	JSON_Parser jParser = new JSON_Parser();
	ProgressDialog pDialog ;
	Context context;
	Button refresh;
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        refresh=(Button)rootView.findViewById(R.id.Refresh);
        shar_state=this.getActivity().getSharedPreferences("State_File",Context.MODE_PRIVATE);
        shar_test=this.getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
        shar_job=this.getActivity().getSharedPreferences("job",Context.MODE_PRIVATE);
        
        
        edt_job=shar_job.edit();
        edt_state=shar_state.edit();
		
		if(shar_state.getInt("State", 0)==-1)
		{
			edt_state.putInt("State", 0);
			edt_state.commit();
		}
		
		if(shar_state.getInt("State", 0)!=0)
		{
			Intent i = new Intent(getActivity(),State_btn.class);
			startActivity(i);
			getActivity().finish();
		}
        lstview=(ListView)rootView. findViewById(R.id.lstview);
        

		 
		
		
		new Asyn_List().execute();
		
		lstview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				// TODO Auto-generated method stub
				final int pos = position;
				AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());	
					
					 //builder.setCancelable(false)
				
				builder.setTitle("Job Activity")
				.setMessage("You Want to conform?")
					 .setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
						 
						 
					 public void onClick(DialogInterface dialog, int id) 
					 {
					 // get user input and set it to result
					
						 
						 Toast.makeText(getActivity().getApplicationContext(), "Accept button click	", Toast.LENGTH_SHORT).show();
						
						 new Asyn_getcust(""+arrlst.get(pos).get_ID(), shar_test.getString("driver_id","0"),arrlst.get(pos).get_sourceLatitude(),arrlst.get(pos).get_sourceLong(),arrlst.get(pos).get_destiLatitude(),arrlst.get(pos).get_destiLong()).execute();
						 
						}
					})
					
				.setNegativeButton("REJECT", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					  dialog.cancel();
					  
						
					  }
				      });
					 // create an alert dialog
					    AlertDialog alertD =builder.create();
					    alertD.show();
					    
					  
			}
		});  
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=getActivity().getIntent();
				getActivity().finish();
				startActivity(intent);
			}
		});
		 
		 return rootView;			 	     
	    }
	
	public class Asyn_List extends AsyncTask<String, String, String>
	{
		JSONObject jobj;
	
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setTitle("Loading");
			pDialog.setMessage("Please Wait...");
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try
			{
				List<NameValuePair> oo = new  ArrayList<NameValuePair>();
				//NameValuePair valuePair = new BasicNameValuePair(name, value)
				jobj = jParser.makeHttpRequest(Constant.WebURL_listCust,"GET",oo);
				
				if(jobj.getString("status").equalsIgnoreCase("Sucess"))
				{ 
					JSONArray jsonArray =jobj.getJSONArray("customerReq");
					arrlst=new ArrayList<List_Ass>();
					
					for(int i=0;i<jsonArray.length();i++)
					{
						List_Ass a = new List_Ass();
						
						 JSONObject obj =jsonArray.getJSONObject(i);
						 String cusname=obj.getString("Cus_Name");
						 String custime= obj.getString("Cus_Time");
						 String cusdate=obj.getString("Cus_Date");
						 String cussource=obj.getString("Cus_Source");
						 String cusdestination=obj.getString("Cus_Destination");
						 int job_id=obj.getInt("ID");

						Float s_latitude=Float.parseFloat(obj.getString("Cus_S_Lat"));
						Float s_Longtitude=Float.parseFloat(obj.getString("Cus_S_Long"));
						Float d_latitude=Float.parseFloat(obj.getString("Cus_D_Lat")); 
						 Float d_Longtitude=Float.parseFloat(obj.getString("Cus_D_Long"));
						 
						/*Double s_Longtitude=obj.getDouble("Cus_S_Long");
						 Double d_latitude=obj.getDouble("Cus_D_Lat"); 
						 Double d_Longtitude=obj.getDouble("Cus_D_Long");*/
						 
						 
						 a.Set_sourceLatitude(s_latitude);
						 a.Set_sourceLong(s_Longtitude);
						 a.Set_destiLatitude(d_latitude);
						 a.Set_destiLong(d_Longtitude);
						
//						String s_latitude=obj.getString("Cus_S_Lat");
//						 String s_Longtitude=obj.getString("Cus_S_Long");
//						 String d_latitude=obj.getString("Cus_D_Lat"); 
//						 String d_Longtitude=obj.getString("Cus_D_Long");
//						 
//						 a.Set_sourceLatitude(Long.parseLong(s_latitude.toString()));
//						 a.Set_sourceLong(Long.parseLong(s_Longtitude.toString()));
//						 a.Set_destiLatitude(Long.parseLong(d_latitude.toString()));
//						 a.Set_destiLong(Long.parseLong(d_Longtitude.toString()));
//						 
						 a.Set_Name(cusname);
						 a.Set_Time(custime);
						 a.Set_Date(cusdate);
						 a.Set_Source(cussource);
						 a.Set_Destination(cusdestination);
						 a.Set_ID(job_id);
						// a.Set_Name(name);
						 
						 arrlst.add(a);
						 
					}
					lstview.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							lstadp = new ListAdapter(getActivity(), arrlst);
							lstview.setAdapter(lstadp);
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
		super .onPostExecute(result);
		pDialog.dismiss();
		try
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	}
	
public class Asyn_getcust extends AsyncTask<String,String,string>
{
	JSONObject jobj;
	
	String strParam="";
	String job_id,driver_id;
	Float s_lat,s_long,d_lat,d_long;
	
	
	public Asyn_getcust(String job_id, String driver_id,Float s_lat,Float s_long,Float d_lat,Float d_long)
	{
		this.job_id = job_id;
		this.driver_id = driver_id;
		this.s_lat= s_lat;
		this.s_long =s_long;
		this.d_lat = d_lat;
		this.d_long = d_long;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(getActivity());
		pDialog.setTitle("Loading");
		pDialog.setMessage("Please Wait..");
		pDialog.setCancelable(true);
		pDialog.show();
	}
	@Override
	protected string doInBackground(String... params) {
		// TODO Auto-generated method stub
		 String strParam = "{\"job_id\":\""+job_id+"\",\"driver_id\":\""+driver_id+"\"}";
		try
		{
			jobj = jParser.makeHTTPPOST(Constant.WebURl_cusjob, "POST", strParam);
			if(jobj.getString("status").equalsIgnoreCase("Sucess"))
			{ 
//				JSONArray jsonArray =jobj.getJSONArray("customerReq");
//				arrlst=new ArrayList<List_Ass>();
				
				// job accepted sucesscufully 
				Log.d("success","job Accepted Succesfully");
				
							edt_state.putInt("State",1);
							edt_state.commit();
							
							 
							 edt_job.putInt("job_id",Integer.parseInt(job_id.toString()));
							 edt_job.putFloat("s_lat",s_lat);
							 edt_job.putFloat("s_long",s_long);
							 edt_job.putFloat("d_lat", d_lat);
							 edt_job.putFloat("d_long", d_long);
							 
							 
							 edt_job.commit();
							Intent i=new Intent(getActivity(),State_btn.class);
							getActivity().startActivity(i);
							getActivity().finish();							
				
			}
			else
			{
				Log.d("fail","job Not Accepted ");
				Toast.makeText(getActivity(), jobj.getString("msg"), Toast.LENGTH_LONG).show();

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void onPostExecute(string result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pDialog.dismiss();
		try {
			if(jobj.getString("status").equalsIgnoreCase("Sucess"))
			{
			}
			else
			{
				Toast.makeText(getActivity(), jobj.getString("msg"), Toast.LENGTH_LONG).show();

			
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}

