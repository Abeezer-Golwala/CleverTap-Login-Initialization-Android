package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("MySharedPref" , Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = new Intent(MainActivity2.this,MainActivity.class);

        findViewById(R.id.bt4).setOnClickListener(v->{
            sp.edit().putString("LoggedIn", "true").commit();
            Log.d("Testloggin6",sp.getString("LoggedIn",""));
            Toast.makeText(this,"Logged In", Toast.LENGTH_SHORT).show();
            MainActivity2.this.startActivity(i);
        });
        findViewById(R.id.bt2).setOnClickListener(v->{
            sp.edit().putString("LoggedIn", "false").commit();
            Log.d("Testloggin7",sp.getString("LoggedIn",""));
            Toast.makeText(this,"Logged Out", Toast.LENGTH_SHORT).show();
            MainActivity2.this.startActivity(i);
        });
    }
}