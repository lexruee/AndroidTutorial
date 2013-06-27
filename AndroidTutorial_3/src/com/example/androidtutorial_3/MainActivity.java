package com.example.androidtutorial_3;

import com.example.androidtutorial_3.model.Country;
import com.example.androidtutorial_3.model.Model;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Model model;
	private ListView resultList;
	private ArrayAdapter<String> resultAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.model = new Model();
		
		//set initial country
		Country country = this.model.chooseRandom();
		TextView textView = (TextView) this.findViewById(R.id.current_country);
		textView.setText(country.name);
		
		resultList = (ListView) this.findViewById(R.id.result_list);
		resultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		resultList.setAdapter(resultAdapter);
		
		//register a OnKeyListener to the postal code text field
		EditText editText = (EditText) this.findViewById(R.id.postal_code);
		editText.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View view, int arg1, KeyEvent event) {
				EditText editText = (EditText) view;
				String postalCode = editText.getText().toString();
				executeAsyncTask(postalCode);
				return false;
			}
		});
	}

	protected void executeAsyncTask(String postalCode) {
		if (postalCode.length() >= 2) {
			AsyncTask<String, Void, String> task = new MyAsyncTask(model,
					resultAdapter);
			task.execute(postalCode);
		}
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
