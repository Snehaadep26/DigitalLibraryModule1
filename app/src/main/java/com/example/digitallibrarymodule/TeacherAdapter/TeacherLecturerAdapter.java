package com.example.digitallibrarymodule.TeacherAdapter;

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
import com.example.digitallibrarymodule.TeacherWebView;
import com.example.digitallibrarymodule.TeacherModel.TeacherLecturerModel;

import java.util.ArrayList;


public class TeacherLecturerAdapter extends RecyclerView.Adapter<TeacherLecturerAdapter.ViewHolder>

{
    ArrayList<TeacherLecturerModel> teacherLecturerModels;
    Context activity;
    public TeacherLecturerAdapter(ArrayList<TeacherLecturerModel> teacherLecturerModels, FragmentActivity activity) {
        this.teacherLecturerModels = teacherLecturerModels;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_lecturer_item,parent,false);
        ViewHolder cvh = new ViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeacherLecturerModel currentCards = this.teacherLecturerModels.get(position);
        holder.icon.setImageResource(currentCards.getIcon());
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
                Intent intent = new Intent(activity, TeacherWebView.class);
                intent.putExtra("key",link);
                intent.putExtra("title",title);
                activity.startActivity(intent);
//                Fragment fragment = new PdfReader(file,name);
//                FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.your_placeholder, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return teacherLecturerModels.size();
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
