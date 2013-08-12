package com.sjsu.edu.bookfinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import com.sjsu.edu.bookfinder.utils.ParseJSON;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends Activity {
	
	private EditText bookTitle;
	private EditText bookEdition;
	private EditText bookCondition;
	private EditText email;
	
	private Button button;
	
	private static final String POST_URL = "http://ec2-54-213-121-196.us-west-2.compute.amazonaws.com/bookApp/default/insert";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		
		button = (Button)findViewById(R.id.button_post_new);
		bookTitle = (EditText)findViewById(R.id.post_Title);
		bookEdition = (EditText)findViewById(R.id.post_Edition);
		bookCondition = (EditText)findViewById(R.id.post_Condition);
		email = (EditText)findViewById(R.id.post_Email);

		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new AsyncTask<String, Void, Void>() {
                	
					@Override
                	protected Void doInBackground(String... params) {
                        try {
                        	
                        	/*ParseJSON parseJSON = new ParseJSON(bookTitle.getText().toString(), bookEdition.getText().toString(), bookCondition.getText().toString(), email.getText().toString());
                        	JSONObject jsonObject = parseJSON.createJSON();*/
                        	HttpClient httpclient = new DefaultHttpClient();
                        	HttpPost httppost = new HttpPost(params[0]);
                            StringEntity details =new StringEntity("{\"bookTitle\":\"" + bookTitle.getText().toString() + "\"," +
                            				"\"bookEdition\":\"" + bookEdition.getText().toString() + "\", " +
                            				"\"bookCondition\":\"" + bookCondition.getText().toString() + "\"," +
                            				"\"email\":\"" + email.getText().toString() + "\"} ");
                        	httppost.addHeader("content-type", "application/json");
                        	httppost.setEntity(details);
                        	httpclient.execute(httppost);                    			       
                		} catch (IOException e) {
                			Log.e(getClass().toString(), e.getMessage());
                		}
                        return null;
                	}

	            	@Override
	            	protected void onPostExecute(Void v) {
	            		Intent result = new Intent();
	    	        	setResult(Activity.RESULT_OK, result);
	    	        	finish();
	            	}
	            	
                }.execute(POST_URL);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post, menu);
		return true;
	}

}
