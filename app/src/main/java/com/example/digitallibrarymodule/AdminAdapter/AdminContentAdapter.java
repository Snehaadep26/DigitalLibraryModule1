package com.example.digitallibrarymodule.AdminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.AdminModelClass.AdminContentModel;
import com.example.digitallibrarymodule.R;

import java.util.ArrayList;

public class AdminContentAdapter extends RecyclerView.Adapter<AdminContentAdapter.MyViewHolder> {
    ArrayList<AdminContentModel> adminContentModels;
    Context context;

    public AdminContentAdapter(ArrayList<AdminContentModel> adminContentModels, Context context) {
        this.adminContentModels = adminContentModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_content_of_topic_item, parent, false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdminContentModel modal = adminContentModels.get(position);
        holder.count.setText(String.valueOf(modal.getCount()));
        holder.imageView.setImageResource(modal.getImage());
        holder.subject.setText(modal.getDes());

    }

    @Override
    public int getItemCount() {
        return adminContentModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView count,subject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageForCard2);
            count=itemView.findViewById(R.id.zero);
            subject=itemView.findViewById(R.id.subject_text);
        }
    }
}
