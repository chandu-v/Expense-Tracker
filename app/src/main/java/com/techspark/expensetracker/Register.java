package com.techspark.expensetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    View view;
    TextView textView;
    EditText pin_et,confirm_pin_et;
    Button register_button;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sp = this.getSharedPreferences("EXPENSE",MODE_PRIVATE);
        editor = sp.edit();

        view = findViewById(R.id.toolbar);
        textView = view.findViewById(R.id.header);
        textView.setText("Register");

        pin_et = findViewById(R.id.pin_et);
        confirm_pin_et = findViewById(R.id.confirm_pin_et);
        register_button = findViewById(R.id.register);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = pin_et.getText().toString();
                String confirm_pin = confirm_pin_et.getText().toString();
                if(!pin.equalsIgnoreCase(confirm_pin)){
                    Toast.makeText(getApplicationContext(),"PIN doesn't match",Toast.LENGTH_LONG).show();
                }else{
                    if(pin.length() != 4){
                        Toast.makeText(getApplicationContext(),"PIN should be 4 character length",Toast.LENGTH_LONG).show();
                    }else{
                        editor.putString("IS_REGISTERED","REGISTERED");
                        editor.putString("PIN",pin);
                        editor.apply();
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}