/*
 * @(#)AppContent.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <PRE>
 * AppPermissionScan App Content Tab
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class AppContent extends FragmentActivity implements OnItemSelectedListener{

	private final int TAB_DOWNLOAD = 0;
	private final int TAB_SYSTEM = 1;
	private final int TAB_PERMISSION = 2;

	private CustomViewPager vpAppContent = null;
	private LinearLayout lpermiss = null;
	private RelativeLayout llTab1 = null;
	private RelativeLayout llTab2 = null;
	private RelativeLayout llTab3 = null;
	private View vTab1 = null;
	private View vTab2 = null;
	private View vTab3 = null;
	private TextView tvTab1 = null;
	private TextView tvTab2 = null;
	private TextView tvTab3 = null;
	public static Spinner app_permission = null;
	private TextView permission_desc = null;
	private MapSpinnerAdapter permissionTypeAdapter;

	private ArrayList<Fragment> list = null;
	private ListItem itemInfo = null;
	
	public boolean boolBackKeyPressFlag = false;
	
	public static String PERMISSION_CODE = "";
	
	public static boolean PERMISSION_SEARCH = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_content);

		vpAppContent = (CustomViewPager) findViewById(R.id.vp_appcontent);
		
		lpermiss = (LinearLayout) findViewById(R.id.permission_select);
		
		llTab1 = (RelativeLayout) findViewById(R.id.ll_tab1);
		llTab2 = (RelativeLayout) findViewById(R.id.ll_tab2);
		llTab3 = (RelativeLayout) findViewById(R.id.ll_tab3);
		
		vTab1 = findViewById(R.id.v_line_tab1);
		vTab2 = findViewById(R.id.v_line_tab2);
		vTab3 = findViewById(R.id.v_line_tab3);
		
		tvTab1 = (TextView) findViewById(R.id.tv_tab1);
		tvTab2 = (TextView) findViewById(R.id.tv_tab2);
		tvTab3 = (TextView) findViewById(R.id.tv_tab3);

		initFragments();

		llTab1.setOnClickListener(mOnClickListener);
		llTab2.setOnClickListener(mOnClickListener);
		llTab3.setOnClickListener(mOnClickListener);

		itemInfo = new ListItem();
		
		app_permission = (Spinner)findViewById(R.id.app_permission);
		
		permissionTypeAdapter = getPermissionsOption(this);
		app_permission.setAdapter(permissionTypeAdapter);
		
		app_permission.setOnItemSelectedListener(this);
		
		permission_desc = (TextView)findViewById(R.id.permission_desc);
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	// public methods
	// ------------------------------------------------------------------------------------------------------------------------------
	public void setCurrentItem(int position) {
		setTab(position);
		vpAppContent.setCurrentItem(position);
	}

	public void setTab(int position) {
		clearEnable();
		enableLine(position);
	}

	public ListItem getItemInfo() {
		return this.itemInfo;
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	// private methods
	// ------------------------------------------------------------------------------------------------------------------------------
	private void initFragments() {
		list = new ArrayList<Fragment>();
		list.add(new FragmentDownload());
		list.add(new FragmentSystem());
		list.add(new FragmentPermission());

		vpAppContent.setAdapter(new PagerAdapter(getSupportFragmentManager()));
		vpAppContent.setOnPageChangeListener(mOnPageChangeListener);
		vpAppContent.setPagingEnabled(true);
		vpAppContent.setOffscreenPageLimit(3);
		vpAppContent.requestDisallowInterceptTouchEvent(true);
		
		enableLine(TAB_DOWNLOAD);
	}

	private void enableLine(int position) {
		switch (position) {
		case TAB_DOWNLOAD:
			int enable1 = getResources().getColor(R.color.tab_enable1);
			vTab1.setBackgroundColor(enable1);
			tvTab1.setTextColor(enable1);
			lpermiss.setVisibility(View.GONE);
			break;
		case TAB_SYSTEM:
			int enable2 = getResources().getColor(R.color.tab_enable2);
			vTab2.setBackgroundColor(enable2);
			tvTab2.setTextColor(enable2);
			lpermiss.setVisibility(View.GONE);
			break;
		case TAB_PERMISSION:
			int enable3 = getResources().getColor(R.color.tab_enable3);
			vTab3.setBackgroundColor(enable3);
			tvTab3.setTextColor(enable3);
			lpermiss.setVisibility(View.VISIBLE);
			break;
		}
	}

	private void clearEnable() {
		int disableLine = getResources().getColor(R.color.tab_disable);
		int disableText = getResources().getColor(R.color.tab_text_disable);
		vTab1.setBackgroundColor(disableLine);
		vTab2.setBackgroundColor(disableLine);
		vTab3.setBackgroundColor(disableLine);
		tvTab1.setTextColor(disableText);
		tvTab2.setTextColor(disableText);
		tvTab3.setTextColor(disableText);
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	// Listener
	// ------------------------------------------------------------------------------------------------------------------------------
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_tab1:
				setCurrentItem(TAB_DOWNLOAD);
				break;
			case R.id.ll_tab2:
				setCurrentItem(TAB_SYSTEM);
				break;
			case R.id.ll_tab3:
				setCurrentItem(TAB_PERMISSION);
				break;
			}
		}
	};

	OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
		@SuppressWarnings("unused")
		int beforePageIndex = TAB_DOWNLOAD;

		@Override
		public void onPageSelected(int index) {
			setTab(index);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (ViewPager.SCROLL_STATE_IDLE == state) {
				beforePageIndex = vpAppContent.getCurrentItem();
			}
		}
	};

	// ------------------------------------------------------------------------------------------------------------------------------
	// inner class
	// ------------------------------------------------------------------------------------------------------------------------------
	private class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}
	};
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if( keyCode == KeyEvent.KEYCODE_BACK ){
			if (!boolBackKeyPressFlag) {
				Toast.makeText(getBaseContext(), getResources().getString(R.string.main_back_finish), Toast.LENGTH_SHORT).show();
				boolBackKeyPressFlag = true;
				mKillBackKeyHandler.sendEmptyMessageDelayed(0, 1500);
				return true;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	@SuppressLint("HandlerLeak")
	Handler mKillBackKeyHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				boolBackKeyPressFlag = false;
			}
		}
	};
	
	
	public static MapSpinnerAdapter getPermissionsOption(Context context){
		String[] permissionArr = null;
		permissionArr = context.getResources().getStringArray(R.array.permissions_array);
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		if(permissionArr != null){
			for(int i=0; i<permissionArr.length; i++){
				String data[] = permissionArr[i].split("\\|");			
				if(data.length == 2){
					map.put(data[1] , data[0]);
				}else{
					map.put("",data[0]);
				}
				
			}
		}
		return new MapSpinnerAdapter(context, 0, new ArrayList<Map.Entry<String, String>>(map.entrySet()));
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		if(PERMISSION_SEARCH){
			PERMISSION_SEARCH = false;
			app_permission.setEnabled(false);
			MapSpinnerAdapter adapter = (MapSpinnerAdapter) app_permission.getAdapter();
			String code_desc = adapter.getItem(app_permission.getSelectedItemPosition()).getKey();
			
		    PackageManager packageManager = getPackageManager();
			PermissionInfo pinfo = null;
			PermissionGroupInfo pginfo = null;
			
			String desc1 = "";
			String desc2 = "";
			String desc3 = "";
			String desc4 = "";
			
			try {
				pinfo = packageManager.getPermissionInfo(code_desc, PackageManager.GET_META_DATA);
				pginfo = packageManager.getPermissionGroupInfo(pinfo.group, PackageManager.GET_META_DATA);
				desc1 = pginfo.loadLabel(packageManager).toString();
				desc2 = pginfo.loadDescription(packageManager).toString();
				desc3 = pinfo.loadLabel(packageManager).toString();
				desc4 = pinfo.loadDescription(packageManager).toString();
			} catch (Exception e) {
				//e.printStackTrace();
				 desc1 = "";
				 desc2 = "";
				 desc3 = "";
				 desc4 = "";
			}
	 	    
	 	    if(!"".equals(desc1)){
	 	    	code_desc = code_desc+"<br>"+desc1+"<br>"+desc2+"<br>"+desc3+"<br>"+desc4+"<br>";
	 	    }
			
			String code= adapter.getItem(app_permission.getSelectedItemPosition()).getValue();
			permission_desc.setText(Html.fromHtml(code_desc+ (PermissionUtil.checkDanger(code) ? getString(R.string.danger_permission)+"<br>" : "")) );
			PERMISSION_CODE = code;
			
			FragmentPermission contentFragment = (FragmentPermission) list.get(TAB_PERMISSION);
			contentFragment.startTask();
		}else{
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.processing), Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {}
	
	
}