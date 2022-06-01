package com.example.digitallibrarymodule.TeacherAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherApi.TeacherHomePageResponse;
import com.example.digitallibrarymodule.TeacherModel.TeacherChildModel;
import com.example.digitallibrarymodule.TeacherModel.TeacherParentModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class TeacherParentRecyclerViewAdapter extends RecyclerView.Adapter<TeacherParentRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<TeacherParentModel> teacherParentModelArrayList;
    public Context cxt;
    TeacherHomePageResponse dashBoardOne;
    ConstraintLayout constraintLayout;
    String s;


   int i,j;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public RecyclerView childRecyclerView;


        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.standard_tv);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);

        }
    }


    public TeacherParentRecyclerViewAdapter(ArrayList<TeacherParentModel> exampleList, Context context, TeacherHomePageResponse dashBoardOne, ConstraintLayout cons) {
        this.teacherParentModelArrayList = exampleList;
        this.cxt = context;
        this.dashBoardOne = dashBoardOne;
        this.constraintLayout = cons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_parent_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return teacherParentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        TeacherParentModel currentItem = teacherParentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(cxt, 2);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);
        holder.category.setText(currentItem.standardClass());
        ArrayList<TeacherChildModel> arrayList = new ArrayList<>();
        int length = dashBoardOne.data.size();
        ArrayList<String> standard = new ArrayList();
        for (int i = 0; i <= dashBoardOne.data.size() - 1; i++) {
            standard.add(dashBoardOne.data.get(i).std_std);
        }
        Set values = new HashSet(standard);
        Log.i("kishu", values.toString());

        for ( i = 0; i <= values.size() - 1; i++) ;
        {
            for ( j = 0; j <= dashBoardOne.data.size() - 1; j++) {
                if (!teacherParentModelArrayList.get(position).standardClass().equals(dashBoardOne.data.get(j).std_std)) {

                } else {
                    arrayList.add(new TeacherChildModel(Integer.valueOf(dashBoardOne.data.get(j).notesCount),
                            Integer.valueOf(dashBoardOne.data.get(j).videoCount),
                            Integer.valueOf(dashBoardOne.data.get(j).quesBankCount),
                            dashBoardOne.data.get(j).std_id,
                            String.valueOf(dashBoardOne.data.get(j).courseName),
                           dashBoardOne.data.get(j).std_section,
                            String.valueOf(dashBoardOne.data.get(j).std_std)));
//)
                s=String.valueOf(dashBoardOne.data.get(j).std_id);
                }
            }

        }

        TeacherChildRecyclerViewAdapter teacherChildRecyclerViewAdapter = new TeacherChildRecyclerViewAdapter(arrayList, holder.childRecyclerView.getContext());
        holder.childRecyclerView.setAdapter(teacherChildRecyclerViewAdapter);
        holder.childRecyclerView.setNestedScrollingEnabled(false);




    }


}