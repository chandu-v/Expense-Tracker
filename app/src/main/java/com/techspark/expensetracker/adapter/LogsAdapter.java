package com.techspark.expensetracker.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techspark.expensetracker.R;
import com.techspark.expensetracker.entity.Log;

import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {

    private Context context;
    private List<Log> logs;

    public LogsAdapter(Context context) {
        this.context = context;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout = layoutInflater.inflate(R.layout.log_adapter_item_view,null,false);
        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title_tv.setText(logs.get(position).getExpense_name().toUpperCase());
        holder.description_tv.setText(logs.get(position).getDescription());
        holder.amount_tv.setText(logs.get(position).getAmount()+"");
        holder.date_tv.setText(logs.get(position).getDate());
        if(logs.get(position).getAmount()<0){
            holder.amount_tv.setTextColor(context.getResources().getColor(R.color.red));
        }else{
            holder.amount_tv.setTextColor(context.getResources().getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        if(logs == null){
            return 0;
        }else{
            return logs.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_tv,amount_tv,description_tv,date_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title);
            amount_tv = itemView.findViewById(R.id.amount);
            description_tv = itemView.findViewById(R.id.description);
            date_tv = itemView.findViewById(R.id.date);
        }
    }
}
