package com.techspark.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techspark.expensetracker.adapter.LogsAdapter;
import com.techspark.expensetracker.entity.Log;
import com.techspark.expensetracker.viewmodel.LogViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    LogViewModel logViewModel;
    TextView total_sum_tv;
    double totalSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recycler_view);
        total_sum_tv = findViewById(R.id.total_sum_tv);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddExpense.class);
                startActivity(intent);
            }
        });


        final LogsAdapter logsAdapter = new LogsAdapter(this);
        recyclerView.setAdapter(logsAdapter);


        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);
        logViewModel.getLogs().observe(this, new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                logsAdapter.setLogs(logs);
            }
        });

        class GetTotalAmount extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                totalSum = logViewModel.getTotalSum();
                total_sum_tv.setText("Available Balance: "+totalSum);
                if(totalSum<0){
                    total_sum_tv.setTextColor(getResources().getColor(R.color.red));
                }else{
                    total_sum_tv.setTextColor(getResources().getColor(R.color.green));
                }
                return null;
            }
        }

        GetTotalAmount getTotalAmount = new GetTotalAmount();
        getTotalAmount.execute();


    }
}