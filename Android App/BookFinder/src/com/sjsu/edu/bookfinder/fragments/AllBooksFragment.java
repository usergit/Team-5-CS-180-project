package com.sjsu.edu.bookfinder.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sjsu.edu.bookfinder.DetailsActivity;
import com.sjsu.edu.bookfinder.R;
import com.sjsu.edu.bookfinder.utils.ParseJSON;

public class AllBooksFragment extends Fragment {

	private ListView allBooksList;
	private ParseJSON parseJSON;
	private static final int BOOK_REQUEST_CODE = 1;
	
	int[] imageIdsArray;
	
	public AllBooksFragment() {
    	super();
		setRetainInstance(true);
	}
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
    
    	super.onCreate(savedInstanceState);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
		View v = inflater.inflate(R.layout.fragment_all_books,  container, false);
		
		new AsyncTask<String, Void, Void>() {
	    	@Override
	    	protected Void doInBackground(String... params) {
    			parseJSON = new ParseJSON();
    			parseJSON.readJSON(params[0]); 
	            return null;
	    	}
	    	@Override
	    	protected void onPostExecute(Void v) {

	    		allBooksList = (ListView)getActivity().findViewById(R.id.listViewAllBooks);
	    		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, parseJSON.getTitles());
	    		allBooksList.setAdapter(adapter);
	    		allBooksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	        @Override
	    	        public void onItemClick(AdapterView<?> parent, View view,
	    	          int position, long id) {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        String title = parseJSON.getTitles().get(position);
                        intent.putExtra("book_condition", parseJSON.getBookConditions().get(title));
                        intent.putExtra("edition", parseJSON.getBookEditions().get(title));
                        intent.putExtra("email", parseJSON.getBookOwners().get(title));
                        startActivityForResult(intent, BOOK_REQUEST_CODE);
	    	        }});
	    	}
	    }.execute("http://ec2-54-213-121-196.us-west-2.compute.amazonaws.com/bookApp/default/getBooks");
		
		return v;
	}	
}