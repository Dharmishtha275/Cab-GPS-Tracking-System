package info.androidhive.slidingmenu;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imageloader.app.ImageLoader;

public class ProfileFragment extends Fragment 
{
	
	SharedPreferences shar_test,shar_carno; 
	Editor edt_carno;
	JSON_Parser jParser = new JSON_Parser();
	ProgressDialog pDialog ;
	Context context;
	TextView name,address,mobno,emailid,emailid1,taxi_no,verification_no,licence,car_type;
	ImageLoader imageloader;
	ImageView image;
	
	public ProfileFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        shar_test=this.getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
        
       name=(TextView)rootView.findViewById(R.id.drivername);
       address=(TextView)rootView.findViewById(R.id.driveradd);
       mobno=(TextView)rootView.findViewById(R.id.drivermobno);
       emailid=(TextView)rootView.findViewById(R.id.driveremail);
       taxi_no=(TextView)rootView.findViewById(R.id.driverTaxino);
       verification_no=(TextView)rootView.findViewById(R.id.verification);
       licence=(TextView)rootView.findViewById(R.id.driverlicence);
       car_type=(TextView)rootView.findViewById(R.id.type_car);
       emailid1=(TextView)rootView.findViewById(R.id.driveremail1);
       image = (ImageView) rootView.findViewById(R.id.imageView1);
       
       imageloader = new ImageLoader(getActivity());
      
      //image.setT
       
        
        new Asyn_getdriverprofile(  shar_test.getString("driver_id","0")).execute();
        return rootView;
    }
	
	public class Asyn_getdriverprofile extends AsyncTask<String,String,String>
	{
		JSONObject jobj;
		String strParam="";
		String job_id;
		public Asyn_getdriverprofile(String job_id)
		{
			this.job_id=job_id;
		}

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
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			 String strParam = "{\"driver_id\":\""+job_id+"\"}";
			 jobj = jParser.makeHTTPPOST(Constant.WebURL_profile, "POST", strParam);
			 try
				{
				if(jobj.getString("status").equalsIgnoreCase("Sucess"))
				{
					final JSONObject jjobj = jobj.getJSONObject("Driver");
					
					name.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								name.setText(jjobj.getString("Driver_Name"));
								address.setText(jjobj.getString("Driver_Address"));
								mobno.setText(jjobj.getString("Driver_Mobileno"));
								emailid.setText(jjobj.getString("Driver_EmailId"));
								taxi_no.setText(jjobj.getString("Driver_Taxino"));
								verification_no.setText(jjobj.getString("Police_verification_No"));
							       licence.setText(jjobj.getString("Driver_Licence_No"));
							       car_type.setText(jjobj.getString("Type_Car"));
							       emailid1.setText(jjobj.getString("Driver_EmailId"));
							      image.setTag(Constant.IMAG_Url+jjobj.getString("Driver_Photo"));
							      
							      imageloader.DisplayImage(Constant.IMAG_Url+jjobj.getString("Driver_Photo"), getActivity(), image);
							       
							      
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});
				}
				}
			 catch(Exception ex)
			 {
				 
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
	
}
