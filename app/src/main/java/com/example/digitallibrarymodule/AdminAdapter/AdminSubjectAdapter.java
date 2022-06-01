package com.example.digitallibrarymodule.AdminAdapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.AdminFragment.AdminChapterFragment;
import com.example.digitallibrarymodule.AdminModelClass.AdminSubjectModel;
import com.example.digitallibrarymodule.R;

import java.util.ArrayList;
import java.util.List;

public class AdminSubjectAdapter extends RecyclerView.Adapter<AdminSubjectAdapter.MyViewHolder> implements Filterable {

    List<AdminSubjectModel> adminSubjectModels;
    Context context;
    List<AdminSubjectModel> adminSubjectModelsFull;
    int positions;

    private OnItemClickListener onItemClickListener;
    String section,standard;




    public AdminSubjectAdapter(List<AdminSubjectModel> subject, Context context, int position, String section, String standard) {
        this.adminSubjectModels = subject;
        this.context = context;
        this.adminSubjectModelsFull = new ArrayList<>(adminSubjectModels);
        this.positions=position;
        this.section=section;
        this.standard=standard;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_layout, parent, false);
        MyViewHolder cvh = new MyViewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdminSubjectModel currentCards = adminSubjectModels.get(position);

        holder.subjectName.setText(currentCards.getSubjectName());
        holder.chapters.setText(currentCards.getChapters());
        holder.ntsCount.setText(String.valueOf(currentCards.getNtsCount()));

        holder.vdoCount.setText(String.valueOf(currentCards.getVdoCount()));
        holder.quesCount.setText(String.valueOf(currentCards.getQuesCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectId=String.valueOf(currentCards.getSubjectId());
                String subjectName=currentCards.getSubjectName();
                String standardId=String.valueOf(positions);
                Fragment fragment = new AdminChapterFragment(subjectId,subjectName,standardId,section,standard);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        );
                fragmentTransaction.replace(R.id.your_placeholder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return adminSubjectModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName, chapters, ntsCount, vdoCount, quesCount;

        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_textview);
            chapters = itemView.findViewById(R.id.chapters_tv);
            ntsCount = itemView.findViewById(R.id.notes_count_tv);
            vdoCount = itemView.findViewById(R.id.video_count_tv);
            quesCount = itemView.findViewById(R.id.question_count_tv);
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
        onItemClickListener=  listener;
    }
    @Override
    public Filter getFilter() {

return filter;

       }
       private Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<AdminSubjectModel> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(adminSubjectModelsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (AdminSubjectModel item : adminSubjectModelsFull) {
                        if (item.getSubjectName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }


           @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                adminSubjectModels.clear();
                adminSubjectModels.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };


}
