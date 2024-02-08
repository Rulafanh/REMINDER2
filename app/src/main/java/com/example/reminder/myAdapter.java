package com.example.reminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    private ArrayList<Model> dataholder;

    public myAdapter(ArrayList<Model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_reminder_file, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        // Bind data to the item layout
        Model currentModel = dataholder.get(position);
        holder.title.setText(currentModel.getTitle());
        holder.date.setText(currentModel.getDate());
        holder.time.setText(currentModel.getTime());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    // View holder class to hold the views
    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, time;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            date = itemView.findViewById(R.id.txtDate);
            time = itemView.findViewById(R.id.txtTime);
        }
    }
}

