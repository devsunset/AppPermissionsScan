/*
 * @(#)MapSpinnerAdapter.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.util.ArrayList;
import java.util.Map.Entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * <PRE>
 * AppPermissionScan MapSpinnerAdapter
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class MapSpinnerAdapter extends ArrayAdapter<Entry<String, String>> {
	Context context;
	@SuppressWarnings("unused")
	private LayoutInflater inflater = null;
 
	public MapSpinnerAdapter(Context c, int textViewResourceId, ArrayList<Entry<String, String>> arrays) {
		super(c, textViewResourceId, arrays);
		this.inflater = LayoutInflater.from(c);
		this.context = c;
	}
 
	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public Entry<String, String> getItem(int position) {
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}
	
	public String getItemKey(int position) {
		return super.getItem(position).getKey();
	}

	public String getItemValue(int position) {
		return super.getItem(position).getValue();
	}
	public int getPositionWithKey(String key){
		for(int i=0; i<getCount(); i++){
			if(getItemKey(i).equals(key)){
				return i;
			}
		}
		return -1;
	}
	public int getPositionWithValue(String value){
		for(int i=0; i<getCount(); i++){
			if(getItemValue(i).equals(value)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public View getDropDownView(int position, View cvView, ViewGroup parent) {
		View convertView = cvView;

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.common_spinner_item, parent, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(getItemValue(position));
		return convertView;
	}

	@Override
	public View getView(int position, View cvView, ViewGroup parent) {
		View convertView = cvView;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.common_spinner_view, parent, false);
		}

		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(getItemValue(position));
		return convertView;
	}
}