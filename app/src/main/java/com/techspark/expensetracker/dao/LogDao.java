package com.techspark.expensetracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.techspark.expensetracker.entity.Log;

import java.util.List;

@Dao
public interface LogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Log log);

    @Query("SELECT * FROM LOG ORDER BY entry_id desc")
    LiveData<List<Log>> getAll();

    @Query("SELECT SUM(amount) FROM LOG")
    double getTotalAmount();

    @Query("SELECT * FROM LOG")
    List<Log> getAllLogs();

    @Query("SELECT * FROM LOG WHERE date = :date")
    List<Log> getAllbyDate(String date);

    @Query("SELECT SUM(amount) FROM LOG WHERE date = :current_date")
    double getSumByDate(String current_date);
}
