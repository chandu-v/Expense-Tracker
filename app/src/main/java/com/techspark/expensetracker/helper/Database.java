package com.techspark.expensetracker.helper;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.techspark.expensetracker.dao.LogDao;
import com.techspark.expensetracker.entity.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Log.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract LogDao logDao();

    private static volatile Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
