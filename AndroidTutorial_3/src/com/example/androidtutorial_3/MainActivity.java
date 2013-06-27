package com.example.androidtutorial_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidtutorial_3.model.Country;
import com.example.androidtutorial_3.model.Model;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static String SERVICE_URL = "http://api.geonames.org/postalCodeLookupJSON";
	public static String USER = "lexruee";

	private Model model;
	private ListView resultList;
	private ArrayAdapter<String> resultAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.model = new Model();
		Country country = this.model.chooseRandom();
		
		TextView textView = (TextView) this.findViewById(R.id.current_country);
		textView.setText(country.name);
		
		resultList = (ListView) this.findViewById(R.id.result_list);
		resultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		resultList.setAdapter(resultAdapter);
		
		EditText postalCode = (EditText) this.findViewById(R.id.postal_code);
		postalCode.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View view, int arg1, KeyEvent event) {
				EditText postalCode = (EditText) view;
				String postal = postalCode.getText().toString();

				if(postal.length()>=2){
					AsyncTask<String, Void, String> task = createAsyncTask();
					task.execute(postal);
				}

				return false;
			}
		});
	}

	protected AsyncTask<String, Void, String> createAsyncTask() {
		return  new AsyncTask<String, Void, String>() {
			
			@Override
			protected void onPostExecute(String result) {
				
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			protected String doInBackground(String... postal) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(SERVICE_URL + "?postalcode="
						+ postal[0] + "&country="
						+ model.getCurrentCountryCode() + "&username=" + USER);
				HttpResponse response;
				try {
					response = client.execute(httpGet);
					InputStream is = response.getEntity().getContent();
					BufferedReader bf = new BufferedReader(
							new InputStreamReader(is));
					String line;
					StringBuilder sb = new StringBuilder();
					while ((line = bf.readLine()) != null) {
						sb.append(line);
					}
					bf.close();
					return sb.toString();
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
				
				return "";
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void chooseCountry(View view) {
		new CountryDialog(this.model).show(this.getFragmentManager(),
				"choose_country");
	}

}
