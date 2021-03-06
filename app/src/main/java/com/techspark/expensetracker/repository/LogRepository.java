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

    public List<Log> getAllLogs() {
        return logDao.getAllLogs();
    }

    public List<Log> getAllByDate(String date) {
        return logDao.getAllbyDate(date);
    }

    public double getSumByDate(String current_date) {
        return logDao.getSumByDate(current_date);
    }
}
