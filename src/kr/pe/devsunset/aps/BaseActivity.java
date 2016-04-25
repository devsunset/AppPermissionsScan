/*
 * @(#)BaseActivity.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * <PRE>
 * AppPermissionScan Base Activity
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class BaseActivity extends Activity {
	public final String TAG = getClass().getSimpleName();

	public Context mContext = null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
