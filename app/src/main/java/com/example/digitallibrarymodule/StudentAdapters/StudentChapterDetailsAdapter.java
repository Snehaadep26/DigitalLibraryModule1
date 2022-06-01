package com.example.digitallibrarymodule.StudentAdapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentFragment.StudentTopicFragment;
import com.example.digitallibrarymodule.StudentModels.StudentChapterDetails;

import java.util.ArrayList;


public class StudentChapterDetailsAdapter extends RecyclerView.Adapter<StudentChapterDetailsAdapter.MyViewHolber> {
    ArrayList<StudentChapterDetails> chapterDetials;
    Context context;
    private OnItemClickListener onItemClickListener;
    int subjectId;



    public StudentChapterDetailsAdapter(ArrayList<StudentChapterDetails> chapterDetials, Context context, int id) {
        this.chapterDetials = chapterDetials;
        this.context = context;
        subjectId=id;

    }

    @NonNull
    @Override
    public MyViewHolber onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_subject_mathematics_layout, parent, false);
        MyViewHolber cvh = new MyViewHolber(view, onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolber holder, int position) {
        StudentChapterDetails currentCards = chapterDetials.get(position);
        holder.qCount.setText(String.valueOf(currentCards.getqCount()));
        holder.chapterNum.setText(String.valueOf(currentCards.getChapterNum()));
        holder.vdoCount.setText(String.valueOf(currentCards.getVdoCount()));
        holder.noteCount.setText(String.valueOf(currentCards.getNoteCount()));
        holder.topicNum.setText(String.valueOf(currentCards.getTopicNum()));
        holder.aboutChapter.setText(currentCards.getAboutChapter());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              int chapterId=  currentCards.getChapterId();
              String topic=currentCards.getAboutChapter();
                Fragment fragment = new StudentTopicFragment(subjectId,chapterId,topic);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return chapterDetials.size();
    }


    public class MyViewHolber extends RecyclerView.ViewHolder {
        public TextView chapterNum, topicNum, aboutChapter,
                vdoCount, noteCount, qCount;

        public MyViewHolber(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            chapterNum = itemView.findViewById(R.id.chapters_in_subject);
            topicNum = itemView.findViewById(R.id.topic_number);
            aboutChapter = itemView.findViewById(R.id.about_chapter);
            noteCount = itemView.findViewById(R.id.video_count_tv_chapter);
            vdoCount = itemView.findViewById(R.id.notes_count_tv_chapter);
            qCount = itemView.findViewById(R.id.question_count_tv_chapter);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAbsoluteAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClickListener(position);
//
//                        }
//
//                    }
//                }
//            });


        }
    }
}
