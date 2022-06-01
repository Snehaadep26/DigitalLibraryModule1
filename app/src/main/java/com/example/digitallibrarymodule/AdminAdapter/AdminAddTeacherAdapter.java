package com.example.digitallibrarymodule.AdminAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.AdminModelClass.AdminAddTeacherModel;
import com.example.digitallibrarymodule.R;

import java.util.ArrayList;

public class AdminAddTeacherAdapter extends RecyclerView.Adapter<AdminAddTeacherAdapter.ViewHolder> {
    Context context;
    AdminAddTeacherModel modal;
    private OnItemClickListener onItemClickListener;
    ArrayList<AdminAddTeacherModel> addTeacherSuggets;
    public AdminAddTeacherAdapter(Context context, ArrayList<AdminAddTeacherModel> addTeacherSuggets, int pos) {
        this.context=context;
        this.addTeacherSuggets=addTeacherSuggets;
    }

    @NonNull
    @Override
    public AdminAddTeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggest_item, parent, false);
        ViewHolder cvh = new ViewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAddTeacherAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            modal = addTeacherSuggets.get(position);
        holder.name.setText(String.valueOf(modal.getTeacherName()));
        holder.cancel.setImageResource(modal.getCancel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("s",String.valueOf(position));
            }
        });

    }


    public interface OnItemClickListener {
        void onItemClickListener(int position);
        void onDeleteClick(int position);
        void onNameClick(int i, String teacherName, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return addTeacherSuggets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView cancel;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.suggest_name);
            cancel=itemView.findViewById(R.id.cancel_suggest);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);

                        }

                    }

                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onNameClick(position,modal.getTeacherName(),modal.getId());

                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClickListener(position);

                        }

                    }
                }
            });

        }
    }
}
