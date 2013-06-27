package com.example.androidtutorial_3;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidtutorial_3.model.Model;

public class CountryDialog extends DialogFragment {

	private Model model;

	public CountryDialog(Model model) {
		this.model = model;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.country_title_dialog);
		List<String> countries = model.getCountries();
		//convert list to array
		final CharSequence[] array = countries.toArray(new CharSequence[countries
				.size()]);
		builder.setItems(array, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String country = array[which].toString();
				model.choose(country);
				TextView textView = (TextView) getActivity().findViewById(R.id.current_country);
				textView.setText(country);
				
				if(getActivity() instanceof MainActivity){
					MainActivity main = (MainActivity)  getActivity();
					EditText editText = (EditText) getActivity().findViewById(R.id.postal_code);
					String postalCode = editText.getText().toString();
					main.executeAsyncTask(postalCode);
				}
			}
		});
		return builder.create();
	}
}
