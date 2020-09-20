package com.techspark.expensetracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.techspark.expensetracker.dao.LogDao;
import com.techspark.expensetracker.entity.Log;
import com.techspark.expensetracker.helper.Database;

import java.util.List;

public class LogRepository {

    private LogDao logDao;
    private LiveData<List<Log>> logs;

    public LogRepository(Application application){
        Database database = Database.getDatabase(application);
        logDao = database.logDao();
        logs = logDao.getAll();
    }

    public LiveData<List<Log>> getAll(){
        return logs;
    }

    public void insert(Log log){
        logDao.insert(log);
    }

    public double getSum(){ return logDao.getTotalAmount(); }
}
