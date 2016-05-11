package com.fxp.contentprovider;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class MyUtil {
	public static String getAppName(Activity activity){
		PackageInfo pkg=null;
		try {
			pkg = activity.getPackageManager().getPackageInfo(activity.getApplication().getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String appName = pkg.applicationInfo.loadLabel(activity.getPackageManager()).toString();		
		return appName;
	}
}
