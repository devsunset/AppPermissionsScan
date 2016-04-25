/*
 * @(#)CustomViewPager.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <PRE>
 * 	AppPermissionScan CustomViewPager
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */
public class CustomViewPager extends ViewPager {
	
	private boolean	enabled;
	
	public CustomViewPager(Context context) {
		super(context);
		init();
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		enabled = true;
	}	
	
	public void setPagingEnabled( boolean isEnabled ) {
		this.enabled = isEnabled;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			if (this.enabled) {
				return super.onTouchEvent(event);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
//			String exceptionAsStrting = sw.toString();
//			Log.e("INFO", exceptionAsStrting);
		}
		return false;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		try {
			if (this.enabled) {
				return super.onInterceptTouchEvent(event);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
//			String exceptionAsStrting = sw.toString();
//			Log.e("INFO", exceptionAsStrting);
		}
		return false;
	}
}
