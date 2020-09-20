package com.techspark.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.techspark.expensetracker.entity.Log;
import com.techspark.expensetracker.viewmodel.LogViewModel;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.Date;

public class AddExpense extends AppCompatActivity {

    EditText title_ev,description_ev,amount_ev,date_ev;
    Button button;
    LogViewModel logViewModel;
    RadioGroup radioGroup;
    String selectedLogType = "";
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);

        title_ev = findViewById(R.id.title_et);
        description_ev = findViewById(R.id.description_ev);
        amount_ev = findViewById(R.id.amount_ev);
        date_ev = findViewById(R.id.date_et);
        radioGroup = findViewById(R.id.radio_group);
        button = findViewById(R.id.submit_button);
        datePicker = findViewById(R.id.date_picker);

        datePicker.setVisibility(View.GONE);
        date_ev.setText(datePicker.getDayOfMonth()+"/"+(datePicker.getMonth()+1)+"/"+datePicker.getYear());

        date_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    date_ev.setText(datePicker.getDayOfMonth()+"/"+(datePicker.getMonth()+1)+"/"+datePicker.getYear());
                    datePicker.setVisibility(View.GONE);
                }
            });
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.expense:
                        selectedLogType = "-";
                        break;
                    case R.id.earning:
                        selectedLogType = "+";
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = title_ev.getText().toString();
                String description  = description_ev.getText().toString();
                double amount = Double.parseDouble(amount_ev.getText().toString());
                String date = date_ev.getText().toString();

                if(title.equalsIgnoreCase("") ||
                description.equalsIgnoreCase("") ||
                amount == 0 ||
                date.equalsIgnoreCase("") ||
                selectedLogType.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"All Fields are mandatory! Please Fill",Toast.LENGTH_LONG).show();
                }else{

                    Log log = new Log();
                    if(selectedLogType.equalsIgnoreCase("-")){
                        amount = amount*-1;
                    }
                    log.setAmount(amount);
                    log.setDate(date);
                    log.setDescription(description);
                    log.setExpense_name(title.toUpperCase());

                    class InsertLogAsync extends AsyncTask<Log,Void,Void>{

                        @Override
                        protected Void doInBackground(Log... params) {
                            logViewModel.insert(params[0]);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    InsertLogAsync insertLogAsync = new InsertLogAsync();
                    insertLogAsync.execute(log);
                }
            }
        });
    }

}