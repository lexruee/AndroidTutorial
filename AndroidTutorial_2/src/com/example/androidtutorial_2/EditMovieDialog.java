package com.example.androidtutorial_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
/**
 * This class represents a simple edit dialog.
 * @author lex
 *
 */
public class EditMovieDialog extends DialogFragment {
	
	private int pos;
	private ArrayAdapter<String> dataSource;

	/**
	 * Creates a edit movie dialog. 
	 * @param dataSource
	 * @param pos
	 */
	public EditMovieDialog(ArrayAdapter<String> dataSource, int pos) {
		this.dataSource = dataSource;
		this.pos = pos;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		final String movie = this.dataSource.getItem(this.pos);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.edit_movie, null);
		
		
		EditText editText = (EditText) view.findViewById(R.id.edit_movie);
		editText.setText(movie);
		
		builder.setView(view)
				.setPositiveButton(R.string.edit_movie,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								EditText editText = (EditText) view.findViewById(R.id.edit_movie);
								String changedMovie = editText.getText().toString();
								dataSource.remove(movie);
								dataSource.insert(changedMovie,pos);
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								EditMovieDialog.this.getDialog().cancel();
							}
						});

		return builder.create();
	}
}
