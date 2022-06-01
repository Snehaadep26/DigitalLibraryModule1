package com.example.digitallibrarymodule.StudentAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentFragment.StudentChapterFragment;
import com.example.digitallibrarymodule.StudentModels.StudentSubjectModel;
import com.example.digitallibrarymodule.StudentUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectAdapter extends RecyclerView.Adapter<StudentSubjectAdapter.MyViewHolder> implements Filterable {

    List<StudentSubjectModel> studentSubjectModels;
    Context context;
    NavController navController;
    List<StudentSubjectModel> studentSubjectModelsFull;
    private static final String baseUrlForImages = "https://s3.ap-south-1.amazonaws.com/test.files.classroom.digital/";

    private OnItemClickListener onItemClickListener;


    public StudentSubjectAdapter(List<StudentSubjectModel> subject, Context context) {
        this.studentSubjectModels = subject;
        this.context = context;
        this.studentSubjectModelsFull = new ArrayList<>(studentSubjectModels);


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_subjects_layout, parent, false);
        MyViewHolder cvh = new MyViewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentSubjectModel currentCards = studentSubjectModels.get(position);
        StudentUtils.fetchSvg(context,baseUrlForImages+currentCards.getIcon(), holder.imageSubj);

        //.Glide.with(context).load(baseUrlForImages+currentCards.getIcon()).into(holder.imageSubj);
        holder.subjectName.setText(currentCards.getSubjectName());
        holder.chapters.setText(currentCards.getChapters());
        holder.ntsCount.setText(String.valueOf(currentCards.getNtsCount()));
        holder.vdoCount.setText(String.valueOf(currentCards.getVdoCount()));
        holder.quesCount.setText(String.valueOf(currentCards.getQuesCount()));
       // holder.imageSubj.setImageResource(currentCards.getIcon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=currentCards.getSubjectId();
                String name=currentCards.getSubjectName();
                Fragment fragment = new StudentChapterFragment(id,name);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return studentSubjectModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName, chapters, ntsCount, vdoCount, quesCount;
        ImageView imageSubj;

        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_textview);
            chapters = itemView.findViewById(R.id.chapters_tv);
            ntsCount = itemView.findViewById(R.id.notes_count_tv);
            vdoCount = itemView.findViewById(R.id.video_count_tv);
            quesCount = itemView.findViewById(R.id.question_count_tv);
            imageSubj=itemView.findViewById(R.id.imageView6Subj);


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
                List<StudentSubjectModel> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(studentSubjectModelsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (StudentSubjectModel item : studentSubjectModelsFull) {
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
                studentSubjectModels.clear();
                studentSubjectModels.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };


}
