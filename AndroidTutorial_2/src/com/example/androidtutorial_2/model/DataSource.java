package com.example.androidtutorial_2.model;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Extend the ArrayAdapter class and insert some intial data.
 * @author lex
 *
 */
public class DataSource extends ArrayAdapter<String> {
	
	private ArrayList<String> movies;
	
	@SuppressWarnings("serial")
	public DataSource(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		this.movies = new ArrayList<String>() {
			{
				add("Transformers I");
				add("Wreck it Ralph");
				add("Star Wars I");
				add("The last standing");
			}
		};

		this.addAll(this.movies);
	}

}
