package com.sjsu.edu.bookfinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	
	private TextView textView;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		Intent intent = getIntent();
		textView = (TextView)findViewById(R.id.details_Condition);
		textView.setText(intent.getStringExtra("book_condition"));
		textView = (TextView)findViewById(R.id.details_Edition);
		textView.setText(intent.getStringExtra("edition"));
		textView = (TextView)findViewById(R.id.details_Email);
		textView.setText(intent.getStringExtra("email"));
		button = (Button)findViewById(R.id.button_OK);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent result = new Intent();
	        	setResult(Activity.RESULT_OK, result);
	        	finish();
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
