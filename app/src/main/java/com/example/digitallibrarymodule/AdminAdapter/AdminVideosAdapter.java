package com.example.digitallibrarymodule.AdminAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import com.example.digitallibrarymodule.AdminApiLibrary.DeleteResponse;
import com.example.digitallibrarymodule.AdminApiLibrary.LoginService;
import com.example.digitallibrarymodule.AdminFragment.AdminEdit;
import com.example.digitallibrarymodule.AdminFragment.AdminPlayerVideo;
import com.example.digitallibrarymodule.AdminModelClass.AdminVideoModel;
import com.example.digitallibrarymodule.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminVideosAdapter extends RecyclerView.Adapter<AdminVideosAdapter.CardViewHolder>
{
    String link="";
    String title,file;
    ArrayList<AdminVideoModel> adminVideoModel;
    Context context;
    BottomSheetDialog bt;
    Retrofit retrofit;
    LoginService loginService;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int standardId;
    int chapterId, topicId, subjectId;

    public AdminVideosAdapter(ArrayList<AdminVideoModel> adminVideoModel, Context context, String subjectName, String topicName,
                              String chapterName, String sectionName, String standardName, int standardId, int subjectId, int chapterId, int topicId) {
        this.adminVideoModel = adminVideoModel;
        this.context = context;
        this.subjectName=subjectName;
        this.topicName=topicName;
        this.chapterName=chapterName;
        this.sectionName=sectionName;
        this.standardName=standardName;
        this.standardId=standardId;
        this.subjectId=subjectId;
        this.chapterId=chapterId;
        this.topicId=topicId;
      
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_card_video,parent,false);
        CardViewHolder cvh = new CardViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        AdminVideoModel currentCards = this.adminVideoModel.get(position);
        holder.imageForCard.setImageResource(currentCards.getImageView());
        holder.infoText.setText(currentCards.getInfoText());
        holder.edit.setImageResource(currentCards.getEdit());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt=new BottomSheetDialog(context, androidx.appcompat.R.style.Base_Theme_AppCompat);
                bt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bt.getWindow().getAttributes().windowAnimations=R.style.ForBottom;
                view= LayoutInflater.from(context).inflate(R.layout.admin_edit,null);
                bt.setContentView(view);
                bt.setCanceledOnTouchOutside(true);
                TextView edit=bt.findViewById(R.id.edit_topic);
                TextView delete=bt.findViewById(R.id.delete_topic);
                delete.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  apiInit();
                                                  bt.dismiss();
                                                  Call<DeleteResponse> call = loginService.deleteLibraryTopic(Integer.valueOf(currentCards.getId()));
                                                  call.enqueue(new Callback<DeleteResponse>() {
                                                      @Override
                                                      public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                                          if (!response.isSuccessful()) {
                                                              Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                                                          }
                                                          DeleteResponse deleteTopicResponse = response.body();
                                                          Toast.makeText(context,deleteTopicResponse.show.message, Toast.LENGTH_LONG).show();

                                                      }
                                                      @Override
                                                      public void onFailure(Call<DeleteResponse> call, Throwable t) {
                                                          Toast.makeText(edit.getContext(), "Error :(", Toast.LENGTH_LONG).show();
                                                      }
                                                  });
                                              }

                                          }

                );

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String titleName=currentCards.getInfoText();
                        String file=currentCards.getFile();
                        String type="video";
                        int id=currentCards.getId();
                        Fragment fragment=new AdminEdit(subjectName,topicName,chapterName,sectionName,titleName,standardName, standardId, file, type, id, subjectId, chapterId, topicId);
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
                        bt.dismiss();
                    }
                });
                bt.show();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("status",currentCards.getPublished() );
                if(!currentCards.getPublished().equals("Draft")) {
                    if(!currentCards.getLink().equals("")){
                        link=currentCards.getLink();
                        title=currentCards.getInfoText();
                        Log.i("link",link );
                        Intent intent = new Intent(context, WebView.class);
                        intent.putExtra("key",link);
                        intent.putExtra("title",title);
                        context.startActivity(intent);
                    }
                    else
                    {
                        file=currentCards.getFile();
                        Fragment fragment = new AdminPlayerVideo(file,title);
                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
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
                }
                else {
                    Toast.makeText(context, "Still Videos is uploading", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return adminVideoModel.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView imageForCard,edit;
        TextView infoText,time;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageForCard=itemView.findViewById(R.id.map_chemistry_imageView);
            infoText=itemView.findViewById(R.id.topic_textview);
            edit=itemView.findViewById(R.id.editvideo);

        }
    }
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }
}
