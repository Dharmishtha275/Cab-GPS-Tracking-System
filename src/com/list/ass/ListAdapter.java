package com.list.ass;

import java.util.ArrayList;

import javax.xml.transform.Source;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import info.androidhive.slidingmenu.R;

public class ListAdapter extends BaseAdapter {
	
	ArrayList<List_Ass> listArray;
	Context c;
	LayoutInflater Inflater;

	public ListAdapter(Context c,ArrayList<List_Ass> listArray)
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
		TextView name,time,date,source,destination,s_latitude,s_longnitude,d_latitude,d_longnitude;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		CommonHelper helper;
		
		if(convertView==null)
		{
			convertView=Inflater.inflate(R.layout.childlist,parent,false);
			
			 helper=new CommonHelper();
			helper.name=(TextView) convertView.findViewById(R.id.Cus_Name);
			helper.time=(TextView) convertView.findViewById(R.id.Cus_Time);
			helper.date=(TextView) convertView.findViewById(R.id.Cus_Date);
			helper.source=(TextView) convertView.findViewById(R.id.Cus_Source);
			helper.destination=(TextView) convertView.findViewById(R.id.Cus_Destination);
			helper.s_latitude=(TextView)convertView.findViewById(R.id.Cus_Latitudesource);
			helper.s_longnitude=(TextView)convertView.findViewById(R.id.Cus_Longnitudesource);
			helper.d_latitude=(TextView)convertView.findViewById(R.id.Cus_Latitudedesti);
			helper.d_longnitude=(TextView)convertView.findViewById(R.id.Cus_Longnitudedesti);
			 
			Typeface custom_font = Typeface.createFromAsset(c.getAssets(),"Quicksand-BoldItalic.ttf");
			helper.time.setTypeface(custom_font);
			helper.destination.setTypeface(custom_font);
			helper.date.setTypeface(custom_font);
			helper.source.setTypeface(custom_font);
			helper.name.setTypeface(custom_font);
			
			 
			 
			convertView.setTag(helper);
		}
		else
		{
			helper=(CommonHelper) convertView.getTag();
		}
		
		List_Ass obj=listArray.get(position);
		helper.name.setText(obj.Get_Name());
		helper.time.setText(obj.get_Time());
		helper.date.setText(obj.Get_Date());
		helper.source.setText(obj.Get_Source());
		helper.destination.setText(obj.Get_Destination());
		//helper.s_latitude.setText(obj.get_sourceLatitude());
		//helper.s_longnitude.setText(obj.get_sourceLong());
		//helper.d_latitude.setText(obj.get_destiLatitude());
		//helper.d_longnitude.setText(obj.get_destiLong());
		
		return convertView;
	}

}
