package com.techspark.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText pin_et;
    Button login_button;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = this.getSharedPreferences("EXPENSE",MODE_PRIVATE);

        pin_et = findViewById(R.id.pin_et);
        login_button = findViewById(R.id.login);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = pin_et.getText().toString();
                if(pin.equalsIgnoreCase(sp.getString("PIN",null))){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("selectedRange","All");
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Enter Correct Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}