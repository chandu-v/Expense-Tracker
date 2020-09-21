package com.techspark.expensetracker.adapter;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techspark.expensetracker.MainActivity;
import com.techspark.expensetracker.R;
import com.techspark.expensetracker.viewmodel.LogViewModel;

import java.util.List;

public class FilterByDateAdapter extends RecyclerView.Adapter<FilterByDateAdapter.ViewHolder> {

    Context context;
    List<String> dataset;
    LogViewModel logViewModel;

    public FilterByDateAdapter(Context context, List<String> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.filter_by_date,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(dataset.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("selectedRange",dataset.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.date);
        }
    }
}
