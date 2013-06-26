package com.example.androidtutorial;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	public static final String MESSAGE_TEXT = "MESAGE_TEXT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Is called when user clicks on the send button.
	 * @param view
	 */
	public void sendMessage(View view){
		//create a new itent to start the DisplayMessageActivity
		//give the current context, this=MainActivity
		Intent intent = new Intent(this,DisplayMessageActivity.class);
		EditText editText = (EditText) this.findViewById(R.id.edit_message);
		//get the content of the text field
		String message = editText.getText().toString();
		//give the message to the second activity
		intent.putExtra(MESSAGE_TEXT, message);
		
		//start the activity
		this.startActivity(intent);
		
		
	}

}
