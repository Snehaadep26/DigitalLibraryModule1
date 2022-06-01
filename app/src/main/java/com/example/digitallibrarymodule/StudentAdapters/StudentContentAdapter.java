package com.example.digitallibrarymodule.StudentAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentModels.StudentContentModel;

import java.util.ArrayList;

public class StudentContentAdapter extends RecyclerView.Adapter<StudentContentAdapter.MyViewHolder> {
    ArrayList<StudentContentModel> studentContentModels;
    Context context;

    public StudentContentAdapter(ArrayList<StudentContentModel> studentContentModels, Context context) {
        this.studentContentModels = studentContentModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_content_of_topic_item, parent, false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentContentModel modal = studentContentModels.get(position);
        holder.count.setText(String.valueOf(modal.getZeroText()));
        holder.imageView.setImageResource(modal.getImageForCard());
        holder.subject.setText(modal.getSubjectText());
        holder.lastWeek.setVisibility(View.VISIBLE);
        if (modal.getLastWeek().charAt(0) == '-'){
            holder.lastWeek.setTextColor(Color.parseColor("#FF414D"));
        }
        holder.lastWeek.setText(modal.getLastWeek());


    }

    @Override
    public int getItemCount() {
        return studentContentModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView count,subject;
        TextView lastWeek;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageForCard);
            count=itemView.findViewById(R.id.zeroText);
            subject=itemView.findViewById(R.id.subjectText);
            lastWeek=itemView.findViewById(R.id.last_count_textv);
        }
    }
}
