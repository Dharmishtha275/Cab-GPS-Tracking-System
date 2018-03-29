package com.list.ass;

import info.androidhive.slidingmenu.R;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class lisAdapter_chat extends BaseAdapter {

	
	ArrayList<list_chat> listArray;
	Context c;
	LayoutInflater Inflater;
	
	public lisAdapter_chat(Context c,ArrayList<list_chat> listArray)
	{

		this.c=c;
		this.listArray=listArray;
		Inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listArray.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listArray.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public class CommonHelper
	{
		TextView msg_driver,msg_admin,from_msg,text_admin,time;
		RelativeLayout relative_driver,relative_admin;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
CommonHelper helper;
		
		if(convertView==null)
		{
			convertView=Inflater.inflate(R.layout.list_chat,parent,false);
			
			 helper=new CommonHelper();
			helper.msg_driver=(TextView) convertView.findViewById(R.id.msg_driver);
			helper.msg_admin=(TextView) convertView.findViewById(R.id.msg_admin);
			helper.from_msg=(TextView) convertView.findViewById(R.id.msg_from);
			helper.time=(TextView) convertView.findViewById(R.id.date);
			helper.relative_admin = (RelativeLayout) convertView.findViewById(R.id.relative_admin);
			helper.relative_driver = (RelativeLayout) convertView.findViewById(R.id.relative_driver);
			
				convertView.setTag(helper);
		}
		else
		{
			helper=(CommonHelper) convertView.getTag();
		}
		
		list_chat obj=listArray.get(position);
		
		if(obj.get_msgfrom().equals("ADMIN"))//ADMIN
		{
			helper.relative_admin.setVisibility(View.VISIBLE);
			helper.msg_admin.setText(obj.get_msg());
			helper.time.setText(obj.get_time());
			helper.relative_driver.setVisibility(View.GONE);
//			helper.msg_adminn.setBackgroundResource(R.drawable.bg_user_bubble);
			
		
			//helper.from_msg.setGravity(Gravity.LEFT);
		}
		else
		{
			helper.relative_driver.setVisibility(View.VISIBLE);
			helper.msg_driver.setText(obj.get_msg());
			helper.time.setText(obj.get_time());
			helper.relative_admin.setVisibility(View.GONE);
			//helper.msg_driver.setBackgroundResource(R.drawable.bg_my_bubble);
		    
			//helper.from_msg.setGravity(Gravity.RIGHT);
			
		}
		
		//helper.msg.setText(obj.get_msg());
		//helper.from_msg.setText(obj.get_msgfrom());
		
	 return convertView;
			 
	}
}
