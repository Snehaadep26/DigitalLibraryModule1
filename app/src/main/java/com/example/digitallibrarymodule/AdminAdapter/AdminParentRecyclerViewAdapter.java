package com.example.digitallibrarymodule.AdminAdapter;

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


import com.example.digitallibrarymodule.AdminApiLibrary.DashBoardOne;
import com.example.digitallibrarymodule.AdminModelClass.AdminChildModel;
import com.example.digitallibrarymodule.AdminModelClass.AdminParentModel;
import com.example.digitallibrarymodule.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class AdminParentRecyclerViewAdapter extends RecyclerView.Adapter<AdminParentRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<AdminParentModel> adminParentModelArrayList;
    public Context cxt;
    DashBoardOne dashBoardOne;
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


    public AdminParentRecyclerViewAdapter(ArrayList<AdminParentModel> exampleList, Context context, DashBoardOne dashBoardOne, ConstraintLayout cons) {
        this.adminParentModelArrayList = exampleList;
        this.cxt = context;
        this.dashBoardOne = dashBoardOne;
        this.constraintLayout = cons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_parent_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return adminParentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        AdminParentModel currentItem = adminParentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(cxt, 2);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);
        holder.category.setText(currentItem.standardClass());
        ArrayList<AdminChildModel> arrayList = new ArrayList<>();
        int length = dashBoardOne.standardslength;
        ArrayList<String> standard = new ArrayList();
        for (int i = 0; i <= dashBoardOne.standardslength - 1; i++) {
            standard.add(dashBoardOne.data.get(i).std_std);
        }
        Set values = new HashSet(standard);
        Log.i("kishu", values.toString());

        for ( i = 0; i <= values.size() - 1; i++) ;
        {
            for ( j = 0; j <= dashBoardOne.standardslength - 1; j++) {
                if (!adminParentModelArrayList.get(position).standardClass().equals(dashBoardOne.data.get(j).std_std)) {

                } else {
                    arrayList.add(new AdminChildModel(Integer.valueOf(dashBoardOne.data.get(j).getNotesCount()),
                            Integer.valueOf(dashBoardOne.data.get(j).videoCount),
                            Integer.valueOf(dashBoardOne.data.get(j).getQuesBankCount()),
                            dashBoardOne.data.get(j).getStd_id(),
                            String.valueOf(dashBoardOne.data.get(j).getCourseName()),
                           dashBoardOne.data.get(j).getStd_section(),
                            String.valueOf(dashBoardOne.data.get(j).getStd_std())));
//)
                s=String.valueOf(dashBoardOne.data.get(j).getStd_id());
                }
            }

        }

        AdminChildRecyclerViewAdapter adminChildRecyclerViewAdapter = new AdminChildRecyclerViewAdapter(arrayList, holder.childRecyclerView.getContext());
        holder.childRecyclerView.setAdapter(adminChildRecyclerViewAdapter);
        holder.childRecyclerView.setNestedScrollingEnabled(false);

/*
        childRecyclerViewAdapter.setOnItemClickListener(new ChildRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(cxt, "Clicked at" + position, Toast.LENGTH_LONG).show();
                Fragment fragment = new SubjectFragment();
                FragmentManager fragmentManager = ((FragmentActivity) cxt).getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("Position",s);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.your_placeholder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
*/


    }


}