package com.techspark.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = this.getSharedPreferences("EXPENSE",MODE_PRIVATE);
        editor = sp.edit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sp.getString("IS_REGISTERED","").equalsIgnoreCase("REGISTERED")){
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(),Register.class);
                    startActivity(intent);
                }

            }
        },3000);
    }
}