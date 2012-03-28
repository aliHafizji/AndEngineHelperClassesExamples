/* Copyright 2012 Olie

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package org.andengine.helperclasses.examples.launcher;

import java.util.ArrayList;

import org.andengine.helperclasses.examples.InputTextExample;
import org.andengine.helperclasses.examples.LevelSelectorExample;
import org.andengine.helperclasses.examples.R;
import org.andengine.helperclasses.examples.SliderExample;
import org.andengine.ui.activity.BaseGameActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AndEngineHelperClassesExamplesLauncher extends ListActivity {
	
	private ArrayList<AdapterRow> mHelperActivityList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mHelperActivityList = new ArrayList<AndEngineHelperClassesExamplesLauncher.AdapterRow>();
		mHelperActivityList.add(new AdapterRow("Slider class example", SliderExample.class));
		mHelperActivityList.add(new AdapterRow("Level selector example", LevelSelectorExample.class));
		mHelperActivityList.add(new AdapterRow("Input Text example", InputTextExample.class));
		
		getListView().setAdapter(new HelperLauncherAdapter(this));
		getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
		    	  Intent i = new Intent(AndEngineHelperClassesExamplesLauncher.this, mHelperActivityList.get(myItemInt).getmClass());
		    	  startActivity(i);
		      }                 
		});
	}
	
	private class HelperLauncherAdapter extends BaseAdapter {

		private Context mContext;
		public HelperLauncherAdapter(Context context) {
			this.mContext = context;
		}
		
		@Override
		public int getCount() {
			return mHelperActivityList.size();
		}

		@Override
		public Object getItem(int pos) {
			return mHelperActivityList.get(pos);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int pos, View convertView, ViewGroup parent) {
			TextView textView;
			if(convertView == null)
				convertView = new TextView(mContext);
			textView = (TextView) convertView;
			textView.setText(mHelperActivityList.get(pos).getmRowText());
			textView.setTextSize(24);
			return convertView;
		}
	}
	
	private class AdapterRow {
		String mRowText;
		Class<? extends BaseGameActivity> mClass;
		
		public AdapterRow(String rowText, Class<? extends BaseGameActivity> className) {
			mRowText = rowText;
			mClass = className;
		}

		public String getmRowText() {
			return mRowText;
		}

		public Class<? extends BaseGameActivity> getmClass() {
			return mClass;
		}
	}
}
