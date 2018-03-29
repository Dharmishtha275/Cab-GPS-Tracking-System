package com.list.ass;

import java.security.PublicKey;

public class List_Ass {
	
	String Time,Date,Source,Destination,name;//,Lats,Longs,Latd,Longd;
	Float Lats,Longs,Latd,Longd;
	int Id;

	public void Set_destiLatitude(Float Latd)
	{
		this.Latd=Latd;
	}
	public Float get_destiLatitude()
	{
		return Latd;
	}
	
	
	public void Set_destiLong(Float Longd)
	{
		this.Longd=Longd;
	}
	public Float get_destiLong()
	{
		return Longd;
	}
	public void Set_sourceLatitude(Float Lats)
	{
		this.Lats=Lats;
	}
	public Float get_sourceLatitude()
	{
		return Lats;
	}
	
	public void Set_sourceLong(Float Longs)
	{
		this.Longs=Longs;
	}
	public Float get_sourceLong()
	{
		return Longs;
	}
	
	public void Set_Time(String time)
	{
		this.Time=time;
	}
	public String get_Time()
	{
		return Time;
	}
	public void Set_ID(int id)
	{
		this.Id=id;
	}
	public int get_ID()
	{
		return Id;
	}
	
	public void Set_Date(String date)
	{
		this.Date=date;
	}
	public String Get_Date()
	{
		return this.Date;
	}
	
	public void Set_Source(String source)
	{
		this.Source=source;
	}
	public String Get_Source()
	{
		return this.Source;
	}
	
	public void Set_Destination(String destination)
	{
		this.Destination=destination;
	}
	public String Get_Destination()
	{
		return this.Destination;
	}
	
	public void Set_Name(String name)
	{
		this.name=name;
	}
	public String Get_Name()
	{
		return this.name;
	}
}

