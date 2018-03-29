package info.androidhive.slidingmenu;



import info.androidhive.slidingmenu.ProfileFragment.Asyn_getdriverprofile;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TutorialFragment extends Fragment{

	WebView wbhome,wbprofile,wbchat,wbsetting;
	TextView txtheadhome,txtheadprofile,txtheadchat,txtheadsetting;
//	TextView txthome,txtprofile,txtchat,txtsetting;
	ImageButton nexthome,nextprofile,preprofile,nextchat,prechat,presetting,btn_home;
	RelativeLayout layoutbtnhome,layoutbtnprofile,layoutbtnchat,layoutbtnsetting;
	
	ScrollView scrollhome,scrollprofile,scrollchat,scrollsetting;
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
	        btn_home=(ImageButton)rootView.findViewById(R.id.homebutton);
			
			//layout
			layoutbtnhome=(RelativeLayout)rootView.findViewById(R.id.layoutbtnhome);
			layoutbtnprofile=(RelativeLayout)rootView.findViewById(R.id.layoutbtnprofile);
			layoutbtnchat=(RelativeLayout)rootView.findViewById(R.id.layoutbtnchat);
			layoutbtnsetting=(RelativeLayout)rootView.findViewById(R.id.layoutbtnsetting);
			
			
			//scrollview
			scrollhome=(ScrollView)rootView.findViewById(R.id.scrollhome);
			scrollprofile=(ScrollView)rootView.findViewById(R.id.scrollprofile);
			scrollchat=(ScrollView)rootView.findViewById(R.id.scrollchat);
			scrollsetting=(ScrollView)rootView.findViewById(R.id.scrollsetting);
			//header Textview
			txtheadhome=(TextView)rootView.findViewById(R.id.txtheadhome);
			txtheadprofile=(TextView)rootView.findViewById(R.id.txtheadprofile);
			txtheadchat=(TextView)rootView.findViewById(R.id.txtheadchat);
			txtheadsetting=(TextView)rootView.findViewById(R.id.txtheadsetting);
			//contain textview
			wbhome=(WebView)rootView.findViewById(R.id.webhome);
			wbprofile=(WebView)rootView.findViewById(R.id.webprofile);
			wbchat=(WebView)rootView.findViewById(R.id.webchat);
			wbsetting=(WebView)rootView.findViewById(R.id.websetting);
			
		    wbhome.loadUrl("file:///android_asset/home.html");
		    wbprofile.loadUrl("file:///android_asset/profile.html");
		    wbchat.loadUrl("file:///android_asset/chat.html");
		    wbsetting.loadUrl("file:///android_asset/setting.html");
			
			//button next
			nexthome=(ImageButton)rootView.findViewById(R.id.nexthome);
			nextprofile=(ImageButton)rootView.findViewById(R.id.nextprofile);
			nextchat=(ImageButton)rootView.findViewById(R.id.nextchat);
			
			//button previous
			preprofile=(ImageButton)rootView.findViewById(R.id.previousprofile);
			prechat=(ImageButton)rootView.findViewById(R.id.previouschat);
			presetting=(ImageButton)rootView.findViewById(R.id.previoussetting);
			
			//on Load page vislble unvisoble data
			btn_home.setVisibility(View.GONE);
			txtheadhome.setVisibility(View.VISIBLE);
			nexthome.setVisibility(View.VISIBLE);
			wbhome.setVisibility(View.VISIBLE);
			scrollhome.setVisibility(View.VISIBLE);
			
			txtheadprofile.setVisibility(View.GONE);
			wbprofile.setVisibility(View.GONE);
			nextprofile.setVisibility(View.GONE);
			preprofile.setVisibility(View.GONE);
			scrollprofile.setVisibility(View.GONE);
			
			txtheadchat.setVisibility(View.GONE);
			wbchat.setVisibility(View.GONE);
			nextchat.setVisibility(View.GONE);
			prechat.setVisibility(View.GONE);
			scrollchat.setVisibility(View.GONE);
			
			txtheadsetting.setVisibility(View.GONE);
			presetting.setVisibility(View.GONE);
			wbsetting.setVisibility(View.GONE);
			scrollsetting.setVisibility(View.GONE);
			
			
			layoutbtnhome.setVisibility(View.VISIBLE);
			layoutbtnprofile.setVisibility(View.GONE);
			layoutbtnchat.setVisibility(View.GONE);
			layoutbtnsetting.setVisibility(View.GONE);
	        
			nexthome.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					btn_home.setVisibility(View.GONE);
					txtheadhome.setVisibility(View.GONE);
					nexthome.setVisibility(View.GONE);
					wbhome.setVisibility(View.GONE);
					layoutbtnhome.setVisibility(View.GONE);
					
					scrollhome.setVisibility(View.GONE);
					
					txtheadprofile.setVisibility(View.VISIBLE);
					wbprofile.setVisibility(View.VISIBLE);
					nextprofile.setVisibility(View.VISIBLE);
					preprofile.setVisibility(View.VISIBLE);
					scrollprofile.setVisibility(View.VISIBLE);
					layoutbtnprofile.setVisibility(View.VISIBLE);
					
					txtheadchat.setVisibility(View.GONE);
					wbchat.setVisibility(View.GONE);
					nextchat.setVisibility(View.GONE);
					prechat.setVisibility(View.GONE);
					scrollchat.setVisibility(View.GONE);
					layoutbtnchat.setVisibility(View.GONE);
					
					layoutbtnsetting.setVisibility(View.GONE);
					txtheadsetting.setVisibility(View.GONE);
					presetting.setVisibility(View.GONE);
					wbsetting.setVisibility(View.GONE);
					scrollsetting.setVisibility(View.GONE);
				}
			});
	nextprofile.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			btn_home.setVisibility(View.GONE);
			txtheadhome.setVisibility(View.GONE);
			nexthome.setVisibility(View.GONE);
			wbhome.setVisibility(View.GONE);
			scrollhome.setVisibility(View.GONE);
			layoutbtnhome.setVisibility(View.GONE);
			
			txtheadprofile.setVisibility(View.GONE);
			wbprofile.setVisibility(View.GONE);
			nextprofile.setVisibility(View.GONE);
			preprofile.setVisibility(View.GONE);
			scrollprofile.setVisibility(View.GONE);
			layoutbtnprofile.setVisibility(View.GONE);
			
			txtheadchat.setVisibility(View.VISIBLE);
			wbchat.setVisibility(View.VISIBLE);
			nextchat.setVisibility(View.VISIBLE);
			prechat.setVisibility(View.VISIBLE);
			scrollchat.setVisibility(View.VISIBLE);
			layoutbtnchat.setVisibility(View.VISIBLE);
			
			txtheadsetting.setVisibility(View.GONE);
			presetting.setVisibility(View.GONE);
			wbsetting.setVisibility(View.GONE);
			scrollsetting.setVisibility(View.GONE);
			layoutbtnsetting.setVisibility(View.GONE);
		}
	});		
	preprofile.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			btn_home.setVisibility(View.GONE);
			txtheadhome.setVisibility(View.VISIBLE);
			nexthome.setVisibility(View.VISIBLE);
			wbhome.setVisibility(View.VISIBLE);
			scrollhome.setVisibility(View.VISIBLE);
			layoutbtnhome.setVisibility(View.VISIBLE);
			
			txtheadprofile.setVisibility(View.GONE);
			wbprofile.setVisibility(View.GONE);
			nextprofile.setVisibility(View.GONE);
			preprofile.setVisibility(View.GONE);
			scrollprofile.setVisibility(View.GONE);
			layoutbtnprofile.setVisibility(View.GONE);
			
			txtheadchat.setVisibility(View.GONE);
			wbchat.setVisibility(View.GONE);
			nextchat.setVisibility(View.GONE);
			prechat.setVisibility(View.GONE);
			scrollchat.setVisibility(View.GONE);
			layoutbtnchat.setVisibility(View.GONE);
			
			txtheadsetting.setVisibility(View.GONE);
			presetting.setVisibility(View.GONE);
			wbsetting.setVisibility(View.GONE);
			scrollsetting.setVisibility(View.GONE);
			layoutbtnsetting.setVisibility(View.GONE);
		}
	});		

	nextchat.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			btn_home.setVisibility(View.VISIBLE);
			txtheadhome.setVisibility(View.GONE);
			nexthome.setVisibility(View.GONE);
			wbhome.setVisibility(View.GONE);
			scrollhome.setVisibility(View.GONE);
			layoutbtnhome.setVisibility(View.GONE);
			
			txtheadprofile.setVisibility(View.GONE);
			wbprofile.setVisibility(View.GONE);
			nextprofile.setVisibility(View.GONE);
			preprofile.setVisibility(View.GONE);
			scrollprofile.setVisibility(View.GONE);
			layoutbtnprofile.setVisibility(View.GONE);
			
			txtheadchat.setVisibility(View.GONE);
			wbchat.setVisibility(View.GONE);
			nextchat.setVisibility(View.GONE);
			prechat.setVisibility(View.GONE);
			scrollchat.setVisibility(View.GONE);
			layoutbtnchat.setVisibility(View.GONE);
			
			
			txtheadsetting.setVisibility(View.VISIBLE);
			presetting.setVisibility(View.VISIBLE);
			wbsetting.setVisibility(View.VISIBLE);
			scrollsetting.setVisibility(View.VISIBLE);
			layoutbtnsetting.setVisibility(View.VISIBLE);
		}
	});
	prechat.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			btn_home.setVisibility(View.GONE);
			txtheadhome.setVisibility(View.GONE);
			nexthome.setVisibility(View.GONE);
			wbhome.setVisibility(View.GONE);
			scrollhome.setVisibility(View.GONE);
			layoutbtnhome.setVisibility(View.GONE);
			
			txtheadprofile.setVisibility(View.VISIBLE);
			wbprofile.setVisibility(View.VISIBLE);
			nextprofile.setVisibility(View.VISIBLE);
			preprofile.setVisibility(View.VISIBLE);
			scrollprofile.setVisibility(View.VISIBLE);
			layoutbtnprofile.setVisibility(View.VISIBLE);
			
			txtheadchat.setVisibility(View.GONE);
			wbchat.setVisibility(View.GONE);
			nextchat.setVisibility(View.GONE);
			prechat.setVisibility(View.GONE);
			scrollchat.setVisibility(View.GONE);
			layoutbtnchat.setVisibility(View.GONE);
			
			txtheadsetting.setVisibility(View.GONE);
			presetting.setVisibility(View.GONE);
			wbsetting.setVisibility(View.GONE);
			scrollsetting.setVisibility(View.GONE);
			layoutbtnsetting.setVisibility(View.GONE);
		}
	});
	presetting.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			btn_home.setVisibility(View.GONE);
			txtheadhome.setVisibility(View.GONE);
			nexthome.setVisibility(View.GONE);
			wbhome.setVisibility(View.GONE);
			scrollhome.setVisibility(View.GONE);
			layoutbtnhome.setVisibility(View.GONE);
			
			txtheadprofile.setVisibility(View.GONE);
			wbprofile.setVisibility(View.GONE);
			nextprofile.setVisibility(View.GONE);
			preprofile.setVisibility(View.GONE);
			layoutbtnprofile.setVisibility(View.GONE);
			scrollprofile.setVisibility(View.GONE);
			
			txtheadchat.setVisibility(View.VISIBLE);
			wbchat.setVisibility(View.VISIBLE);
			nextchat.setVisibility(View.VISIBLE);
			prechat.setVisibility(View.VISIBLE);
			scrollchat.setVisibility(View.VISIBLE);
			layoutbtnchat.setVisibility(View.VISIBLE);
			
			txtheadsetting.setVisibility(View.GONE);
			presetting.setVisibility(View.GONE);
			wbsetting.setVisibility(View.GONE);
			scrollsetting.setVisibility(View.GONE);
			layoutbtnsetting.setVisibility(View.GONE);
		}
	});
	btn_home.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			btn_home.setVisibility(View.GONE);
			txtheadhome.setVisibility(View.VISIBLE);
			nexthome.setVisibility(View.VISIBLE);
			wbhome.setVisibility(View.VISIBLE);
			scrollhome.setVisibility(View.VISIBLE);
			layoutbtnhome.setVisibility(View.VISIBLE);
			
			txtheadprofile.setVisibility(View.GONE);
			wbprofile.setVisibility(View.GONE);
			nextprofile.setVisibility(View.GONE);
			preprofile.setVisibility(View.GONE);
			scrollprofile.setVisibility(View.GONE);
			layoutbtnprofile.setVisibility(View.GONE);
			
			txtheadchat.setVisibility(View.GONE);
			wbchat.setVisibility(View.GONE);
			nextchat.setVisibility(View.GONE);
			prechat.setVisibility(View.GONE);
			scrollchat.setVisibility(View.GONE);
			layoutbtnchat.setVisibility(View.GONE);
			
			txtheadsetting.setVisibility(View.GONE);
			presetting.setVisibility(View.GONE);
			wbsetting.setVisibility(View.GONE);
			scrollsetting.setVisibility(View.GONE);
			layoutbtnsetting.setVisibility(View.GONE);
		}
	});
	
	 return rootView;	
	}
	
}
               	
    		
	
