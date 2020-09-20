package com.techspark.expensetracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "log")
public class Log {

    @PrimaryKey(autoGenerate = true)
    private int entry_id;
    private String expense_name;
    private double amount;
    private String description;
    private String date;

    public Log() {
    }

    public Log(int entry_id, String expense_name, double amount, String description, String date) {
        this.entry_id = entry_id;
        this.expense_name = expense_name;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
