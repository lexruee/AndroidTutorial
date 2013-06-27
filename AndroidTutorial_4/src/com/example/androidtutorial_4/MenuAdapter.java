package com.example.androidtutorial_4;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {

	private List<HashMap<String, String>> menus;
	private LayoutInflater inflater;

	public MenuAdapter(Activity activity, List<HashMap<String, String>> menus) {
		this.menus = menus;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return this.menus.size();
	}

	@Override
	public Object getItem(int pos) {
		return this.menus.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.list_row, null);

		TextView title = (TextView) vi.findViewById(R.id.menu_title); // title
		TextView content = (TextView) vi.findViewById(R.id.menu_content); // content
		HashMap<String, String> menu = this.menus.get(position);
		
		title.setText(menu.get("title"));
		content.setText(menu.get("content"));	
		return vi;
	}

}
