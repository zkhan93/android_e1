package io.github.zkhan93.contactpage.util;

import android.content.Context;
import android.content.SharedPreferences;

import io.github.zkhan93.contactpage.R;

/**
 * Created by 1036870 on 1/13/2016.
 */
public class Util {
    public static String getUsername(Context context){
        SharedPreferences spf=getSharedPref(context);
        return spf.getString(Constants.PREF_KEYS.USERNAME,"");
    }
    public static String getPassword(Context context){
        SharedPreferences spf=getSharedPref(context);
        return spf.getString(Constants.PREF_KEYS.PASSWORD,"");
    }
    public static SharedPreferences getSharedPref(Context context){
        if(context!=null)
            return context.getSharedPreferences(context.getString(R.string.pref_filename),Context.MODE_PRIVATE);
        else
            return null;
    }
}
