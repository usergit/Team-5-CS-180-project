package com.sjsu.edu.bookfinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sjsu.edu.bookfinder.PostActivity;
import com.sjsu.edu.bookfinder.R;

public class MyBooksFragment extends Fragment {

	private ListView myBooksList;
	private static final int POST_REQUEST_CODE = 1;
	private String[] book_titles = {"Book1", "Book2", "Book3", "Book4", "Book5"};
	private Button button;
	
	int[] imageIdsArray;
	
	public MyBooksFragment() {
    	super();
		setRetainInstance(true);
	}
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
    
    	super.onCreate(savedInstanceState);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
		View v = inflater.inflate(R.layout.fragment_my_books,  container, false);
		//flipView = new FlipViewController(inflater.getContext());
		myBooksList = (ListView)v.findViewById(R.id.listViewMyBooks);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, book_titles);
		myBooksList.setAdapter(adapter);
		button = (Button)v.findViewById(R.id.button_post_new);

		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivityForResult(intent, POST_REQUEST_CODE);
			}
		});
		
		return v;
	}
	
	
}