package com.techspark.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techspark.expensetracker.adapter.FilterByDateAdapter;
import com.techspark.expensetracker.adapter.LogsAdapter;
import com.techspark.expensetracker.entity.Log;
import com.techspark.expensetracker.viewmodel.LogViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView,filter_by_date;
    LogViewModel logViewModel;
    TextView total_sum_tv;
    double totalSum;
    LogsAdapter logsAdapter;
    String selectedRange;
    DatePicker datePicker;
    List<Log> dataSet;
    ImageView emptyImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        selectedRange = intent.getStringExtra("selectedRange");

        floatingActionButton = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recycler_view);
        filter_by_date = findViewById(R.id.filter_by_date);
        total_sum_tv = findViewById(R.id.total_sum_tv);
        datePicker = findViewById(R.id.date_picker_main);
        emptyImageView = findViewById(R.id.no_expense);
        emptyImageView.setVisibility(View.GONE);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddExpense.class);
                startActivity(intent);
            }
        });


        logsAdapter = new LogsAdapter(this);
        recyclerView.setAdapter(logsAdapter);

        List<String> dataset = new ArrayList<>();
        dataset.add("All");
        dataset.add("Today");
        dataset.add("Yesterday");
        dataset.add("Custom Date");
        FilterByDateAdapter filterByDateAdapter = new FilterByDateAdapter(this,dataset);
        filter_by_date.setAdapter(filterByDateAdapter);


        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);
        /*logViewModel.getLogs().observe(this, new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                logsAdapter.setLogs(logs);
            }
        });*/
        if(selectedRange.equalsIgnoreCase("Custom Date")){
            datePicker.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        selectedRange = String.format("%S/%S/%S",i2,i1+1,i);
                        getLogsBySelectedPeriod(selectedRange);
                        datePicker.setVisibility(View.GONE);
                    }
                });
            }
        }else {
            getLogsBySelectedPeriod(selectedRange);
        }
    }

    public void getLogsBySelectedPeriod(final String selectedRange){
        class GetDetailsByDate extends AsyncTask<String,Void,Void> {

            @Override
            protected Void doInBackground(String... params) {
                Date today = new Date();
                String current_date = String.format("%S/%S/%S",today.getDate(),today.getMonth()+1,today.getYear()+1900);
                String yesterday = String.format("%S/%S/%S",today.getDate()-1,today.getMonth()+1,today.getYear()+1900);
                switch (params[0]){
                    case "All":
                        android.util.Log.i("MAIN_ACTIVITY","ALL");
                        dataSet = logViewModel.getAllLogs();
                        if(dataSet.size() == 0){
                            emptyImageView.setVisibility(View.VISIBLE);
                        }
                        logsAdapter.setLogs(dataSet);
                        break;
                    case "Today":

                        android.util.Log.i("MAIN_ACTIVITY","Today "+current_date);
                        dataSet = logViewModel.getAllLogsByDate(current_date);
                        if(dataSet.size() == 0){
                            emptyImageView.setVisibility(View.VISIBLE);
                        }
                        logsAdapter.setLogs(dataSet);
                        break;
                    case "Yesterday":
                        android.util.Log.i("MAIN_ACTIVITY","Yesterday");
                        dataSet = logViewModel.getAllLogsByDate(yesterday);
                        if(dataSet.size() == 0){
                            emptyImageView.setVisibility(View.VISIBLE);
                        }
                        logsAdapter.setLogs(dataSet);
                        break;
                    case "Custom Date":
                        android.util.Log.i("MAIN_ACTIVITY","Custom Date");
                        if(dataSet.size() == 0){
                            emptyImageView.setVisibility(View.VISIBLE);
                        }
                        logsAdapter.setLogs(logViewModel.getAllLogsByDate(selectedRange));
                        break;
                    default:
                        android.util.Log.i("MAIN_ACTIVITY","Default");
                        dataSet = logViewModel.getAllLogsByDate(selectedRange);
                        if(dataSet.size() == 0){
                            emptyImageView.setVisibility(View.VISIBLE);
                        }
                        logsAdapter.setLogs(dataSet);
                        break;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }
        GetDetailsByDate getDetailsByDate = new GetDetailsByDate();
        getDetailsByDate.execute(selectedRange);
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}