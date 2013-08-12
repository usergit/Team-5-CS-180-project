package com.sjsu.edu.bookfinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.sjsu.edu.bookfinder.fragments.AllBooksFragment;
import com.sjsu.edu.bookfinder.fragments.MyBooksFragment;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {
    
    private String[] tab_titles;
    private String[] tab_icon_names;
    private int[] tab_icon_resIds;
    
	private TabPageIndicator indicator;
	private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		tab_titles = getResources().getStringArray(R.array.home_tab_titles);
		tab_icon_names = getResources().getStringArray(R.array.home_tab_icon_names);
		tab_icon_resIds = getDrawableResIdsArraybyName(getApplicationContext(), tab_icon_names);
		
		MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public static int[] getDrawableResIdsArraybyName(Context appContext, String[] resname) {
		int[] resIds = new int[resname.length];
		
		for (int i=0; i<resname.length; i++) 
		{
			resIds[i] = getDrawableResourceIdByName(appContext, resname[i]);
		}
		return resIds;
	}
    
    public static int getDrawableResourceIdByName(Context appContext, String resname) {
		return appContext.getResources().getIdentifier(resname, "drawable", appContext.getPackageName());
	}
	
	class MyFragmentAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			
			//customisationType = position;
			
			switch (position) {
			case 0:
				return new AllBooksFragment();
				
			case 1:
				return new MyBooksFragment();
			}
			return null;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return tab_titles[position];
		}

		@Override
		public int getIconResId(int index) {
			return tab_icon_resIds[index];
		}

		@Override
		public int getCount() {
			return tab_titles.length;
		}
   }
    
}
