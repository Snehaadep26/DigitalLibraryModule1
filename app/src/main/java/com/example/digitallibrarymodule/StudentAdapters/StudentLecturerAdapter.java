package com.example.digitallibrarymodule.StudentAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentModels.StudentLecturerModel;
import com.example.digitallibrarymodule.StudentUtils;
import com.example.digitallibrarymodule.StudentYoutube;

import java.util.ArrayList;

public class StudentLecturerAdapter extends RecyclerView.Adapter<StudentLecturerAdapter.ViewHolder>

{
    ArrayList<StudentLecturerModel> studentLecturerModels;
    Context activity;
    private static final String baseUrlForImages = "https://s3.ap-south-1.amazonaws.com/test.files.classroom.digital/";

    public StudentLecturerAdapter(ArrayList<StudentLecturerModel> studentLecturerModels, FragmentActivity activity) {
        this.studentLecturerModels = studentLecturerModels;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_lecturer_item,parent,false);
        ViewHolder cvh = new ViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentLecturerModel currentCards = this.studentLecturerModels.get(position);
        StudentUtils.fetchSvg(activity,baseUrlForImages+currentCards.getIcon(), holder.icon);

        holder.chapterName.setText(currentCards.getChapterName());
        holder.topicName.setText(currentCards.getTopicName());
        holder.subjectName.setText(currentCards.getSubjectName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file=  currentCards.getFile();
                Log.i("file", file);
                String name=currentCards.getTopicName();
                String link= "https://docs.google.com/viewer?url="+"https://test-digital-library.s3.ap-south-1.amazonaws.com/"+ currentCards.getFile();
                String title=currentCards.getTopicName();
                Intent intent = new Intent(activity, StudentYoutube.class);
                intent.putExtra("key",link);
                intent.putExtra("title",title);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentLecturerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView subjectName,chapterName,topicName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.imageView_icon);
            topicName=itemView.findViewById(R.id.topic_name_lec);
            chapterName=itemView.findViewById(R.id.chapter_lec);
            subjectName=itemView.findViewById(R.id.subject_lec);
        }
    }
}
