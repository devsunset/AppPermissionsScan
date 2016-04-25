/*
 * @(#)AppInfo.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.text.Collator;
import java.util.Comparator;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

/**
 * <PRE>
 * AppPermissionScan AppInfo
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class AppInfo {
	public static interface AppFilter {
		public void init();
		public boolean filterApp(ApplicationInfo info);
	}

	public Drawable mIcon = null;
	public String mAppName = null;
	public String mAppPackge = null;
	public String mAppCategory = null;
	public String mFirstInstallTime = null;
	public String mLastUpdateTime = null;
	public String mVersionCode = null;
	public String mVersionName = null;
	public String mPermissionDesc = null;
	public String mPermission = null;
	
	public static final AppFilter THIRD_PARTY_FILTER = new AppFilter() {
		public void init() {
		}

		@Override
		public boolean filterApp(ApplicationInfo info) {
			if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				return true;
			} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				return true;
			}
			return false;
		}
	};
	
	public static final AppFilter SYSTEM_FILTER = new AppFilter() {
		public void init() {
		}

		@Override
		public boolean filterApp(ApplicationInfo info) {
			if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				return false;
			} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				return false;
			}
			return true;
		}
	};

	public static final Comparator<AppInfo> ALPHA_COMPARATOR = new Comparator<AppInfo>() {
		private final Collator sCollator = Collator.getInstance();
		@Override
		public int compare(AppInfo object1, AppInfo object2) {
			return sCollator.compare(object1.mAppName, object2.mAppName);
		}
	};
	
	public static final Comparator<AppInfo> ALPHA_COMPARATOR_APP = new Comparator<AppInfo>() {
		private final Collator sCollator = Collator.getInstance();
		@Override
		public int compare(AppInfo object1, AppInfo object2) {
			return sCollator.compare(object1.mAppCategory, object2.mAppCategory);
		}
	};
}
