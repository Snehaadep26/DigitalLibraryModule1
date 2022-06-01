package com.example.digitallibrarymodule.StudentAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentFragment.StudentPlayerVideo;
import com.example.digitallibrarymodule.StudentModels.StudentVideoModel;
import com.example.digitallibrarymodule.StudentUtils;
import com.example.digitallibrarymodule.StudentYoutube;

import java.util.ArrayList;

public class StudentVideoAdapter extends RecyclerView.Adapter<StudentVideoAdapter.ViewHolder>
{
    ArrayList<StudentVideoModel> studentVideoModels;
    Context activity;
    String link,title,file;

    private static final String baseUrlForImages = "https://s3.ap-south-1.amazonaws.com/test.files.classroom.digital/";

    public StudentVideoAdapter(ArrayList<StudentVideoModel> studentVideoModels, Context activity) {
        this.studentVideoModels = studentVideoModels;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_video_item,parent,false);
        ViewHolder cvh = new ViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentVideoModel currentCards = this.studentVideoModels.get(position);
       StudentUtils.fetchSvg(activity,baseUrlForImages+currentCards.getIcon(), holder.icon);

        holder.chapterName.setText(currentCards.getChapterName());
        holder.topicName.setText(currentCards.getTopicName());
        holder.subjectName.setText(currentCards.getSubjectName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("status",currentCards.getPublished() );
                if(!currentCards.getPublished().equals("Draft")) {
                    if(!currentCards.getLink().equals("")){
                        link=currentCards.getLink();
                        title=currentCards.getTitle();
                        Log.i("link",link );
                        Intent intent = new Intent(activity, StudentYoutube.class);
                        intent.putExtra("key",link);
                        intent.putExtra("title",title);
                        activity.startActivity(intent);

                    }
                    else
                    {
                        file=currentCards.getFile();
                        Fragment fragment = new StudentPlayerVideo(file,title);
                        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
                else {
                    Toast.makeText(activity, "Still Videos is uploading", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentVideoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView subjectName,chapterName,topicName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.map_chemistry_imageView);
            topicName=itemView.findViewById(R.id.topic_video);
            chapterName=itemView.findViewById(R.id.chapter_video);
            subjectName=itemView.findViewById(R.id.subject_video);
        }
    }
}
