package info.androidhive.slidingmenu;

import org.json.JSONException;
import org.json.JSONObject;




import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
	
	WebView myBrowser;
	
//			  myBrowser = (WebView)rootView.findViewById(R.id.mybrowser);
//		        
//		        myBrowser.loadUrl("file:///android_asset/mypage.html");

	public SettingsFragment(){}
	Button btn2,btn3,btn4,back;
	LinearLayout l1;
	
	ImageButton img2,img3,img4;
	TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
	ScrollView scr;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		  View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
       
       btn2=(Button)rootView.findViewById(R.id.button2);
       btn3=(Button)rootView.findViewById(R.id.button3);//rules
       btn4=(Button)rootView.findViewById(R.id.button4);
       back=(Button)rootView.findViewById(R.id.back);
       l1=(LinearLayout)rootView.findViewById(R.id.linearlayout1);
      
       scr=(ScrollView)rootView.findViewById(R.id.scrollview1);
       
       img2=(ImageButton)rootView.findViewById(R.id.img2);
       img3=(ImageButton)rootView.findViewById(R.id.img3);
       img4=(ImageButton)rootView.findViewById(R.id.img4);
       
       txt1=(TextView)rootView.findViewById(R.id.Compnyname);
       txt2=(TextView)rootView.findViewById(R.id.Compnyaddress);
       txt3=(TextView)rootView.findViewById(R.id.Compnymobno);
       txt4=(TextView)rootView.findViewById(R.id.Compnymob);
       txt9=(TextView)rootView.findViewById(R.id.CompnyEmail);
       txt10=(TextView)rootView.findViewById(R.id.Compnyemail);
       txt5=(TextView)rootView.findViewById(R.id.Compnyaddress1);
       txt6=(TextView)rootView.findViewById(R.id.Rules);
       myBrowser = (WebView)rootView.findViewById(R.id.mybrowser);
       
       myBrowser.setVisibility(View.GONE);
       txt1.setVisibility(View.GONE);
       txt2.setVisibility(View.GONE);
       txt3.setVisibility(View.GONE);
       txt4.setVisibility(View.GONE);
       txt5.setVisibility(View.GONE);
       txt6.setVisibility(View.GONE);
       txt9.setVisibility(View.GONE);
       txt10.setVisibility(View.GONE);
      // txt7.setVisibility(View.GONE);
      // txt8.setVisibility(View.GONE);
       back.setVisibility(View.GONE);
       l1.setVisibility(View.GONE);
       scr.setVisibility(View.GONE);
       
       btn4.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());	
			builder.setTitle("Version 1.0")
			.setMessage("With the App User Easily get the Work and directly Interact with Admin.");
		builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getActivity().getApplicationContext(), "You clicked on CLOSE", Toast.LENGTH_SHORT).show();
                }
        });
 
        // Showing Alert Message
        builder.show();
		}
	});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				txt1.setVisibility(View.VISIBLE);
			       txt2.setVisibility(View.VISIBLE);
			       txt3.setVisibility(View.VISIBLE);
			       txt4.setVisibility(View.VISIBLE);
			       txt5.setVisibility(View.VISIBLE);
			       txt9.setVisibility(View.VISIBLE);
			       txt10.setVisibility(View.VISIBLE);
			       
			       img2.setVisibility(View.GONE);
			       img3.setVisibility(View.GONE);
			       img4.setVisibility(View.GONE);
			       
			       btn2.setVisibility(View.GONE);
			       btn3.setVisibility(View.GONE);
			       btn4.setVisibility(View.GONE);
			      back.setVisibility(View.VISIBLE);
			       
			       txt6.setVisibility(View.GONE);
			       myBrowser.setVisibility(View.GONE);
			       //txt7.setVisibility(View.GONE);
			      // txt8.setVisibility(View.GONE);
			       scr.setVisibility(View.GONE);

			}
		});
back.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		txt1.setVisibility(View.GONE);
	       txt2.setVisibility(View.GONE);
	       txt3.setVisibility(View.GONE);
	       txt4.setVisibility(View.GONE);
	       txt5.setVisibility(View.GONE);
	       txt6.setVisibility(View.GONE);
	      /* txt7.setVisibility(View.GONE);
	       txt8.setVisibility(View.GONE);*/
	       txt9.setVisibility(View.GONE);
	       txt10.setVisibility(View.GONE);
	       
	       img2.setVisibility(View.VISIBLE);
	       img3.setVisibility(View.VISIBLE);
	       img4.setVisibility(View.VISIBLE);
	       
	       btn2.setVisibility(View.VISIBLE);
	       btn3.setVisibility(View.VISIBLE);
	       btn4.setVisibility(View.VISIBLE);
	       back.setVisibility(View.GONE);
	       scr.setVisibility(View.GONE);
	       myBrowser.setVisibility(View.GONE);
	       scr.setVisibility(View.GONE);
	       
	      
		
	}
});       

		btn3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		txt1.setVisibility(View.GONE);
	       txt2.setVisibility(View.GONE);
	       txt3.setVisibility(View.GONE);
	       txt4.setVisibility(View.GONE);
	       txt5.setVisibility(View.GONE);
	       txt9.setVisibility(View.GONE);
	       txt10.setVisibility(View.GONE);
	       
	       img2.setVisibility(View.GONE);
	       img3.setVisibility(View.GONE);
	       img4.setVisibility(View.GONE);
	       
	       btn2.setVisibility(View.GONE);
	       btn3.setVisibility(View.GONE);
	       btn4.setVisibility(View.GONE);
	       back.setVisibility(View.GONE);
	       
	       txt6.setVisibility(View.VISIBLE);
	       l1.setVisibility(View.VISIBLE);
	    //   txt7.setVisibility(View.VISIBLE);
	      // txt8.setVisibility(View.VISIBLE);
	       myBrowser.setVisibility(View.VISIBLE);
	       scr.setVisibility(View.VISIBLE);
	       
	       myBrowser.loadUrl("file:///android_asset/rules.html");

	}
});   
return rootView;
	}

}