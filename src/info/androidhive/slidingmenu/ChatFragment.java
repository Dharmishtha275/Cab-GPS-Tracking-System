package info.androidhive.slidingmenu;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.list.ass.ListAdapter;
import com.list.ass.List_Ass;
import com.list.ass.lisAdapter_chat;
import com.list.ass.list_chat;

public class ChatFragment extends Fragment {

	EditText edt;
	
	Button btn;
	ListView lstview;
	
	ArrayList<list_chat> arrlst;
	lisAdapter_chat lstadp;
	JSON_Parser jParser = new JSON_Parser();
	ProgressDialog pDialog ;
	Context context;
	SharedPreferences shar_test;


	public ChatFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
         edt=(EditText)rootView.findViewById(R.id.chatText);
         btn=(Button)rootView.findViewById(R.id.buttonSend);
         lstview=(ListView)rootView.findViewById(R.id.listView1);
         shar_test=this.getActivity().getSharedPreferences("test",Context.MODE_PRIVATE);
         
         
        new Asyn_chat(shar_test.getString("driver_id","0")).execute();
        
       btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String s=edt.getText().toString();
			if(s.equals(""))
			{
				Toast.makeText(getActivity().getApplicationContext(),"You must Enter Message",Toast.LENGTH_SHORT).show();
			}
			else
			{
			new Asyn_msg(shar_test.getString("driver_id","0"), s).execute();
			}
		}
	});
        return rootView;
    }
	
	public class Asyn_chat extends AsyncTask<String, String, String>
	{
		JSONObject jobj;
				
		String strParam="";
		String Driver_Id;
		public Asyn_chat(String Driver_Id)
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
					jobj = jParser.makeHTTPPOST(Constant.WebURL_chat, "POST", strParam);
					if(jobj.getString("Status").equalsIgnoreCase("Sucess"))
					{ 
						JSONArray jsonArray =jobj.getJSONArray("Allmsg");
						arrlst=new ArrayList<list_chat>();
						for(int i=0;i<jsonArray.length();i++)
						{
							list_chat a = new list_chat();
							 JSONObject obj =jsonArray.getJSONObject(i);
							// arrlst.add(a);
							 //String msg= ;
							 a.Set_msg(obj.getString("Msg"));
							 a.Set_msgfrom(obj.getString("Send_Recieve"));
							 a.Set_time(obj.getString("Date_T"));
							 arrlst.add(a);
						}
						lstview.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								lstadp = new lisAdapter_chat(getActivity(), arrlst);
								lstview.setAdapter(lstadp);
								lstview.setSelection(lstview.getCount()-1);
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

	public class Asyn_msg extends AsyncTask<String, String, String>
	{
		JSONObject jobj;
				
		String strParam="";
		String Driver_Id,msg;
		public Asyn_msg(String Driver_Id,String msg)
		{
			this.Driver_Id=Driver_Id;
			this.msg=msg;
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
			 String strParam = "{\"Id\":\""+Driver_Id+"\",\"msg\":\""+msg+"\"}";
				try
				{
					jobj = jParser.makeHTTPPOST(Constant.WebURL_msg, "POST", strParam);
					if(jobj.getString("Status").equalsIgnoreCase("Sucess"))
					{ 
						JSONArray jsonArray =jobj.getJSONArray("Allmsg");
						arrlst=new ArrayList<list_chat>();
						for(int i=0;i<jsonArray.length();i++)
						{
							list_chat a = new list_chat();
							 JSONObject obj =jsonArray.getJSONObject(i);
							// arrlst.add(a);
							 //String msg= ;
							 a.Set_msg(obj.getString("Msg"));
 							 a.Set_msgfrom(obj.getString("Send_Recieve"));
 							 a.Set_time(obj.getString("Date_T"));
							
							 arrlst.add(a);
						}
						lstview.post(new Runnable() {
							
							@Override
							public void run() {
								
								edt.setText("");
								// TODO Auto-generated method stub
								lstadp = new lisAdapter_chat(getActivity(), arrlst);
								lstview.setAdapter(lstadp);
								lstview.setSelection(lstview.getCount()-1);
								//lstview.smoothScrollToPosition(arrlst.size()-1);//smoothScrollBy(lstview.getLe, duration);
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
	}
	}



}
