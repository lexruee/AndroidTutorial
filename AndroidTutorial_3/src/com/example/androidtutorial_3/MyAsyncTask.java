package com.example.androidtutorial_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidtutorial_3.model.Model;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public class MyAsyncTask extends AsyncTask<String, Void, String> {
	
	private Model model;
	private ArrayAdapter<String> resultAdapter;

	public MyAsyncTask(Model model,ArrayAdapter<String> resultAdapter){
		this.model = model;
		this.resultAdapter = resultAdapter;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(result==null)
			return;
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray postalcodes = jsonObject.getJSONArray("postalcodes");
			
			List<String> list = new ArrayList<String>();
			for(int i = 0; i <postalcodes.length(); i++){
				JSONObject postalcode = postalcodes.getJSONObject(i);
				list.add(postalcode.getString("placeName") + ", "+ postalcode.getString("postalcode"));
			}
			
			resultAdapter.clear();
			resultAdapter.addAll(list);
			resultAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}

	@Override
	protected String doInBackground(String... postal) {
		DefaultHttpClient client = new DefaultHttpClient();
		Uri serviceURI = Uri.parse(Model.SERVICE_URI);
		Builder uriBuilder = serviceURI.buildUpon();
		uriBuilder.appendQueryParameter("postalcode", postal[0])
				.appendQueryParameter("country", model.getCurrentCountryCode())
				.appendQueryParameter("username", Model.USER);

		HttpGet httpGet = new HttpGet(uriBuilder.build().toString());
		try {
			HttpResponse response = client.execute(httpGet);
			InputStream is = response.getEntity().getContent();
			return this.convertInputStreamToString(is);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String convertInputStreamToString(InputStream is) throws IOException {
		BufferedReader bf = new BufferedReader(
				new InputStreamReader(is));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = bf.readLine()) != null) {
			sb.append(line);
		}
		bf.close();
		return sb.toString();
	}
}
