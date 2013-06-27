package com.example.androidtutorial_4;

import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ArrayList<HashMap<String, String>> menus;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.menus = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> menu1 = new HashMap<String, String>();
		menu1.put("title", "Menü");
		menu1.put(
				"content",
				"Bami Goreng\nIndonesische Nudelpfanne\nmit Schweinefleisch,\nGemüse und Pilzen\nan Sojasauce\nFleisch: Schweiz\nCHF 6.90 / 12.60");
		HashMap<String, String> menu2 = new HashMap<String, String>();
		menu2.put("title", "Vegimenü");
		menu2.put("content",
				"Pilzragout an Rosmarinsauce mit Gemüsestreifen\nim Reisring\nCHF 6.60 / 12.60");
		HashMap<String, String> menu3 = new HashMap<String, String>();
		menu3.put("title", "Daily Special");
		menu3.put("content",
				"Pizza mit Belag nach Wahl\nMen\u00fcsalat\nFleisch: Schweiz \nCHF 9.90");
		
		menus.add(menu1);
		menus.add(menu2);
		menus.add(menu3);
		
		listView = (ListView) this.findViewById(R.id.menu_list);
		MenuAdapter adapter =  new MenuAdapter(this,this.menus);
		listView.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
