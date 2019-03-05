package com.jourif.debuggable;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import java.lang.reflect.*;

import android.util.Log;

import java.lang.Boolean;

public class WebViewDebug extends CordovaPlugin
{
	private static final String TAG = "WebViewDebug";

	/**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
	webView.clearCache(false);
	WebView.setWebContentsDebuggingEnabled(true);
	
	// webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
	try {
    		Method m = WebSettings.class.getMethod("setMixedContentMode", int.class);
    		if ( m == null ) {
        		Log.e("WebSettings", "Error getting setMixedContentMode method");
    		}
    		else {
        		m.invoke(webView.getSettings(), 0); // 2 = MIXED_CONTENT_COMPATIBILITY_MODE
        		Log.i("WebSettings", "Successfully set MIXED_CONTENT_COMPATIBILITY_MODE");
    		}
	}
	catch (Exception ex) {
    		Log.e("WebSettings", "Error calling setMixedContentMode: " + ex.getMessage(), ex);
	}

//         String packageName = cordova.getActivity().getPackageName();
//         try {
//             ApplicationInfo ai = cordova.getActivity().getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);

//             Bundle bundle = ai.metaData;

//             if (bundle.getBoolean("WebViewDebug")) {
//                 webView.setWebContentsDebuggingEnabled(true);
//             }
//         } catch (NameNotFoundException e) {
//             Log.e(TAG, "Name " + packageName + " could not be found");
//         }
    }
}
