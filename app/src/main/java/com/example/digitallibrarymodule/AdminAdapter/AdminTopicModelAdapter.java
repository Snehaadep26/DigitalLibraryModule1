package com.example.digitallibrarymodule.AdminAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibrarymodule.AdminApiLibrary.ApiClient;
import com.example.digitallibrarymodule.AdminApiLibrary.DeleteTopicRequest;
import com.example.digitallibrarymodule.AdminApiLibrary.DeleteTopicResponse;
import com.example.digitallibrarymodule.AdminApiLibrary.LoginService;
import com.example.digitallibrarymodule.AdminFragment.AdminEditTopic;
import com.example.digitallibrarymodule.AdminFragment.AdminLibraryFragment;
import com.example.digitallibrarymodule.AdminModelClass.AdminTopicModel;
import com.example.digitallibrarymodule.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminTopicModelAdapter extends RecyclerView.Adapter<AdminTopicModelAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<AdminTopicModel> courseModalArrayList;
    private Context context;
    private OnItemClickListener onItemClickListener;
    int standardId,chapterId;
            String subjectName;
    View sheetView;
     BottomSheetDialog bt,btnew;
     int subjectId;
     LoginService loginService;
     Retrofit retrofit;
    BottomSheetDialog mBottomSheetDialog;
    String standardName,section,chapterName;

    // creating a constructor for our variables.
    public AdminTopicModelAdapter(ArrayList<AdminTopicModel> courseModalArrayList, Context context, int standardid, int chapterid, String standardName, String section, String subjectName, String chapterName, int subjectId) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
        this.standardId=standardid;
        this.chapterId=chapterid;
        this.standardName=standardName;
        this.section=section;
        this.subjectName=subjectName;
        this.chapterName=chapterName;
        this.subjectId=subjectId;
    }


    public void filterList(ArrayList<AdminTopicModel> filterllist) {

        courseModalArrayList = filterllist;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item, parent, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        AdminTopicModel modal = courseModalArrayList.get(position);
        holder.aboutTopic.setText(modal.getCourseDescription());
        holder.topicNoteCount.setText(String.valueOf(modal.getNcount()));
        holder.topicVideoCount.setText(String.valueOf(modal.getVcount()));
        holder.topicQuestionCount.setText(String.valueOf(modal.getQcount()));
        holder.edit.setImageResource(modal.getImage());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt=new BottomSheetDialog(context, androidx.appcompat.R.style.Base_Theme_AppCompat);
                View view1= LayoutInflater.from(context).inflate(R.layout.admin_edit,null);
                bt.setContentView(view1);
                bt.setCancelable(true);
                bt.setCanceledOnTouchOutside(true);
                bt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView edit=bt.findViewById(R.id.edit_topic);
                TextView delete=bt.findViewById(R.id.delete_topic);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bt.dismiss();
                        btnew=new BottomSheetDialog(context, androidx.appcompat.R.style.Base_Theme_AppCompat);
                        View view1= LayoutInflater.from(context).inflate(R.layout.admin_delete,null);
                        btnew.setContentView(view1);
                        btnew.setCancelable(true);
                        btnew.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        Button cancel=btnew.findViewById(R.id._cancel_button);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                btnew.dismiss();
                            }
                        });
                        Button confirmButton=btnew.findViewById(R.id.confirm_topic_button);
                        confirmButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                apiInit();
                                DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(Integer.valueOf(modal.getTopicId()),1);
                                Call<DeleteTopicResponse> call = loginService.deleteCall(deleteTopicRequest);
                                call.enqueue(new Callback<DeleteTopicResponse>() {
                                    @Override
                                    public void onResponse(Call<DeleteTopicResponse> call, Response<DeleteTopicResponse> response) {
                                        if (!response.isSuccessful()) {
                                            Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                                        }
                                        DeleteTopicResponse deleteTopicResponse = response.body();
                                        Toast.makeText(context,"Topic deleted successfully!", Toast.LENGTH_LONG).show();
                                        btnew.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<DeleteTopicResponse> call, Throwable t) {
                                        Toast.makeText(edit.getContext(), "Error :(", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                        btnew.show();
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment=new AdminEditTopic();
                        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                                .setCustomAnimations(
                                        R.anim.slide_in,  // enter
                                        R.anim.fade_out,  // exit
                                        R.anim.fade_in,   // popEnter
                                        R.anim.slide_out  // popExit
                                );
                       Bundle arg = new Bundle();
                       arg.putString("topicId",String.valueOf(modal.getTopicId()));
                        arg.putString("topicName",String.valueOf(modal.getCourseDescription()));
                        fragment.setArguments(arg);
                        fragmentTransaction.replace(R.id.your_placeholder, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        bt.dismiss();
                    }
                });
                bt.show();


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int topicId=Integer.valueOf(modal.getTopicId());
                String topicName=String.valueOf(modal.getCourseDescription());
                Fragment fragment = new AdminLibraryFragment(topicId,chapterId,standardId,topicName,chapterName,standardName,section,subjectName,subjectId);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                Log.i("t",String.valueOf(chapterId));
                Log.i("a",String.valueOf(standardId));
                Log.i("s", modal.getTopicId());
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
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView aboutTopic, topicNoteCount, topicVideoCount, topicQuestionCount;
        private ImageView edit;

        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            // initializing our views with their ids.
            edit = itemView.findViewById(R.id.edit);
            aboutTopic = itemView.findViewById(R.id.about_chapter);
            topicNoteCount = itemView.findViewById(R.id.notes_c);
            topicVideoCount = itemView.findViewById(R.id.video_c);
            topicQuestionCount = itemView.findViewById(R.id.question_c);
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
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }
}

