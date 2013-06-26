package com.example.androidtutorial_2;

import com.example.androidtutorial_2.model.DataSource;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayAdapter<String> dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listview = (ListView) this.findViewById(R.id.listView);

		dataSource = new DataSource(this,
				android.R.layout.simple_list_item_1);

		listview.setAdapter(dataSource);
		//register listview to the context menu
		this.registerForContextMenu(listview);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Save a movie in the adapter
	 * @param view
	 */
	public void saveMovie(View view){
		EditText editText = (EditText) this.findViewById(R.id.add_movie);
		String movie = editText.getText().toString();
		this.dataSource.add(movie);
		this.dataSource.notifyDataSetChanged();
	}
	
	/**
	 * Show the selected movie in a toast view.
	 * @param pos
	 */
	private void showMovie(int pos) {
		String item = this.dataSource.getItem(pos);
		Toast.makeText(this, "Show movie: " + item, Toast.LENGTH_LONG).show();	
	}

	/**
	 * Remove the selected movie.
	 * @param pos
	 */
	private void removeMovie(int pos) {
		String item = this.dataSource.getItem(pos);
		this.dataSource.remove(item);
		this.dataSource.notifyDataSetChanged();
	}
	
	/**
	 * Create a context menu for the movie list view.
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, view, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);	
	}
	
	/**
	 * Handle the context menu actions.
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.show_movie:
			this.showMovie(info.position);
			return true;
		case R.id.remove_movie:
			this.removeMovie(info.position);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}


}
