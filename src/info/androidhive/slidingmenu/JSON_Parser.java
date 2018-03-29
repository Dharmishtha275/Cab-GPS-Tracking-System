package info.androidhive.slidingmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSON_Parser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray Jarray = null;
	static String json = "";
	String res = null;

	public JSON_Parser() {

	}

	// JSONArray {"":"","":""}
	public JSONObject makeHTTPPOST(String url, String method, String param) {
		try {
			HttpClient client = new DefaultHttpClient();
			Log.e("Req ", param);

			HttpPost post = new HttpPost(url);

			StringEntity stringEntity = new StringEntity(param);

			stringEntity.setContentType("application/json");

			post.setHeader("Content-Type", "application/json");

			post.setEntity(stringEntity);

			BasicHttpResponse httpResponse = (BasicHttpResponse) client
					.execute(post);

			// res = EntityUtils.toString(httpResponse.getEntity());
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			// is=EntityUtils.To
			// System.out.println("data Prints ok "+res);

		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			//return new JSONObject(Constants.error);
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.e("data prints of ", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		//JSONArray Jarray_1 = null;
		try {
			// jObj = new JSONObject(res);
			System.out.println("Convert data...");
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("Gt Error");
			e.printStackTrace();
		}
		return jObj;

	}

	//
	public JSONObject makeHttpRequest(String url, String method,List<NameValuePair> param) 
	{
		// TODO Auto-generated method stub
		try {
			// check for request method
			if (method == "POST") {
				System.out.println(" In post Method");
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(param));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				System.out.println(" In post Method");
			} else if (method == "GET") {
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(param, "utf-8");
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			System.out.println(" In post Method");
			json = sb.toString();
			Log.e("data prints", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			 jObj = new JSONObject(json);
			//Jarray = new JSONArray(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		// return jObj;
		return jObj;

	}
}
