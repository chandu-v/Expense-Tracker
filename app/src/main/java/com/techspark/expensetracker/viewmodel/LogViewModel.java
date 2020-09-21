package com.techspark.expensetracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.techspark.expensetracker.entity.Log;
import com.techspark.expensetracker.repository.LogRepository;

import java.util.List;

public class LogViewModel extends AndroidViewModel {

    private LogRepository logRepository;
    private LiveData<List<Log>> logs;

    public LogViewModel(@NonNull Application application) {
        super(application);
        logRepository = new LogRepository(application);
        logs = logRepository.getAll();
    }

    public LiveData<List<Log>> getLogs() {
        return logs;
    }

    public void insert(Log log){
        logRepository.insert(log);
    }

    public double getTotalSum(){ return logRepository.getSum(); }

    public List<Log> getAllLogs() {
        return logRepository.getAllLogs();
    }


    public List<Log> getAllLogsByDate(String date) {
        return logRepository.getAllByDate(date);
    }
}
