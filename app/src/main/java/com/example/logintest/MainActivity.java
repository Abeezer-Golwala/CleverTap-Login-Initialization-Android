package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  implements CTInboxListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("MySharedPref" , Context.MODE_PRIVATE);

        Log.d("Testloggin1",sp.getString("LoggedIn",""));
        if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
            ActivityLifecycleCallback.register(getApplication());
            Toast.makeText(this,"Activity Life Cycle", Toast.LENGTH_SHORT).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextView =(TextView)findViewById(R.id.textview1);
        Intent i = new Intent(MainActivity.this,MainActivity2.class);
        Log.d("Testloggin3",sp.getString("LoggedIn",""));

        if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
            Log.d("Testloggin4",sp.getString("LoggedIn",""));
            Toast.makeText(this,"SDK Initialized", Toast.LENGTH_SHORT).show();
            CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
            CleverTapAPI.setNotificationHandler((NotificationHandler) new PushTemplateNotificationHandler());
            HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Name", "Abeezer");
            profileUpdate.put("Identity", "T1");
            clevertapDefaultInstance.onUserLogin(profileUpdate);
            CleverTapAPI.createNotificationChannel(getApplicationContext(), "abtest", "abtest", "test ab", NotificationManager.IMPORTANCE_MAX, true);
            mTextView.setText("Clevertap SDK Initialized");
        }
        else
        {
            Log.d("NotInit","Not Initialized");
            Toast.makeText(this,"SDK not Initialized", Toast.LENGTH_SHORT).show();
            mTextView.setText("Clevertap SDK Not Initialized");
        }
        findViewById(R.id.bt2).setOnClickListener(v-> {
            if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
                clevertapDefaultInstance.pushEvent("TestAdd");
                Toast.makeText(this,"Event Pushed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Event Not Pushed", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.bt1).setOnClickListener(v-> MainActivity.this.startActivity(i));
        findViewById(R.id.bt3).setOnClickListener(v->{
            if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
                clevertapDefaultInstance.showAppInbox();
            }
        });
        findViewById(R.id.bt6).setOnClickListener(v->{
            if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
                clevertapDefaultInstance.pushEvent("AbeezerPushEvent");
            }
        });
        findViewById(R.id.bt5).setOnClickListener(v->{
            if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
                clevertapDefaultInstance.pushEvent("abeezerinapnotif");
            }
        });
        findViewById(R.id.bt4).setOnClickListener(v->{
            if(Objects.equals(sp.getString("LoggedIn", ""), "true")) {
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
                clevertapDefaultInstance.pushEvent("Abeezergetmsg");
            }
        });
    }
    @Override
    public void inboxDidInitialize() {}
    @Override
    public void inboxMessagesDidUpdate() {}
}