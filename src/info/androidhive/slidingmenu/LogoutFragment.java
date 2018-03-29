package info.androidhive.slidingmenu;

import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class LogoutFragment extends Fragment {
	ProgressDialog pDialog;
	Context context;
	JSON_Parser jParser = new JSON_Parser();
	public LogoutFragment(){}
	SharedPreferences shar_state,shar_test,shar_job,shar_token;
	Editor edt_state,edt_test,edt_job,edt_token;
	
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_logout, container, false);
        shar_state=getActivity().getSharedPreferences("State_File", Context.MODE_PRIVATE);
		 shar_test=getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
		 shar_job=getActivity().getSharedPreferences("job",Context.MODE_PRIVATE);
		 shar_token=getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
		
		edt_token=shar_token.edit();
		edt_state=shar_state.edit();
		edt_test=shar_test.edit();
		edt_job=shar_job.edit();
        
       new Async_Logout(shar_test.getString("driver_id","0")).execute();
        return rootView;
    }
    
    class Async_Logout extends AsyncTask<String, String, String>
	{
    	JSONObject jobj;
    	String strParam="";
		String Driver_Id;
		 public Async_Logout(String Driver_Id)
		 {
			 this.Driver_Id=Driver_Id;
		 }
		 @Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
				pDialog=new ProgressDialog(getActivity());
   				pDialog.setTitle("Loading");
				pDialog.setMessage("Please Wait..");
				pDialog.setCancelable(true);
				pDialog.show();
			}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			 String strParam = "{\"Id\":\""+Driver_Id+"\"}";
			try
			{
				jobj = jParser.makeHTTPPOST(Constant.WebURL_logout,"POST",strParam);
				Intent i=new Intent(getActivity().getApplicationContext(),Login.class);
				startActivity(i);
				getActivity().finish();
				edt_state.putInt("State",0);
				edt_state.commit();
				edt_test.putBoolean("is_login",false);
				edt_test.putString("driver_id",null);
				edt_test.commit();
				edt_token.putString("token",null);
				edt_token.commit();
				 edt_job.putInt("job_id",0);
				 edt_job.commit();
				
			}
			catch(Exception e)
			{
				
			}
			return null;
		}
		
		
		@Override
		protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
			super .onPostExecute(result);
			pDialog.dismiss();
	}
	}
     
}
