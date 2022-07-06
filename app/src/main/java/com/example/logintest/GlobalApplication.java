package com.example.logintest;
import android.app.Application;

import com.clevertap.android.sdk.CleverTapAPI;

public class GlobalApplication extends Application  {
    @Override
    public void onCreate() {
//        SharedPreferences sp = getSharedPreferences("MySharedPref" , Context.MODE_PRIVATE);
//        if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
//            ActivityLifecycleCallback.register(this);
//            Toast.makeText(getApplicationContext(),"Activity Life Cycle Callback",Toast.LENGTH_SHORT).show();
//        }
        CleverTapAPI.setDebugLevel(3);
        super.onCreate();
    }

}
