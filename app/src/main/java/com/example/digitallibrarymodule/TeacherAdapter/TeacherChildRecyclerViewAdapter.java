package com.example.digitallibrarymodule.TeacherAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherFragment.TeacherSubjectFragment;
import com.example.digitallibrarymodule.TeacherModel.TeacherChildModel;


import java.util.ArrayList;


public class TeacherChildRecyclerViewAdapter extends RecyclerView.Adapter<TeacherChildRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<TeacherChildModel> teacherChildModelArrayList;
    Context cxt;
    private  OnItemClickListener onItemClickListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView notesCount,videoCount,quesCount,course,section;



        public MyViewHolder(View itemView,OnItemClickListener listener) {
            super(itemView);
            notesCount = itemView.findViewById(R.id.notes_count);
            videoCount = itemView.findViewById(R.id.video_count);
            quesCount = itemView.findViewById(R.id.question_count);
            course = itemView.findViewById(R.id.course);
            section = itemView.findViewById(R.id.section);
           // NavController navController = NavHostFragment.findNavController(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAbsoluteAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClickListener(position);

                        }

                    }
                }
            });


        }
    }
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public TeacherChildRecyclerViewAdapter(ArrayList<TeacherChildModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.teacherChildModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_child_recyclerview_items, parent, false);
        return new MyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TeacherChildModel currentItem = teacherChildModelArrayList.get(position);

        holder.course.setText(teacherChildModelArrayList.get(position).getCourse());
        holder.section.setText(String.valueOf(teacherChildModelArrayList.get(position).getSection()));
        holder.notesCount.setText(String.valueOf(teacherChildModelArrayList.get(position).getNotesCount()));
        holder.videoCount.setText(String.valueOf(teacherChildModelArrayList.get(position).getVideoCount()));
        holder.quesCount.setText(String.valueOf(teacherChildModelArrayList.get(position).getQuesCount()));



        //creating new activity

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int standardId= teacherChildModelArrayList.get(position).getStandardId();
                String section= teacherChildModelArrayList.get(position).getSection();
                String standardName= teacherChildModelArrayList.get(position).getStandard();

                Fragment fragment = new TeacherSubjectFragment(standardId,section,standardName);
                FragmentManager fragmentManager = ((FragmentActivity) cxt).getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("Position",String.valueOf(teacherChildModelArrayList.get(position).getStandardId()));
                args.putString("section", teacherChildModelArrayList.get(position).getSection());
                args.putString("standardName",String.valueOf(teacherChildModelArrayList.get(position).getStandard()));
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        );

                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return teacherChildModelArrayList.size();
    }
}