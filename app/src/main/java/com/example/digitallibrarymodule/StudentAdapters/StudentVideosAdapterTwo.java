package com.example.digitallibrarymodule.StudentAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentFragment.StudentPlayerVideo;
import com.example.digitallibrarymodule.StudentModels.StudentVideoModelTwo;
import com.example.digitallibrarymodule.StudentYoutube;

import java.util.ArrayList;

public class StudentVideosAdapterTwo extends RecyclerView.Adapter<StudentVideosAdapterTwo.CardViewHolder>
{
    String link="";
    String title,file;
    ArrayList<StudentVideoModelTwo> videoModel;
    Context context;

    public StudentVideosAdapterTwo(ArrayList<StudentVideoModelTwo> videoModel, Context context) {
        this.videoModel = videoModel;
        this.context = context;

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card_video,parent,false);
        CardViewHolder cvh = new CardViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        StudentVideoModelTwo currentCards = this.videoModel.get(position);
        holder.imageForCard.setImageResource(currentCards.getImageView());
        holder.infoText.setText(currentCards.getInfoText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("status",currentCards.getPublished() );
                if(!currentCards.getPublished().equals("Draft")) {
                    if(!currentCards.getLink().equals("")){
                        link=currentCards.getLink();
                        title=currentCards.getInfoText();
                        Log.i("link",link );
                        Intent intent = new Intent(context, StudentYoutube.class);
                        intent.putExtra("key",link);
                        intent.putExtra("title",title);
                        context.startActivity(intent);
//                        Fragment fragment = new YoutubeFragment(link,title);
//                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();
                    }
                    else
                    {
                        file=currentCards.getFile();
                        Fragment fragment = new StudentPlayerVideo(file,title);
                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
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
        return videoModel.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView imageForCard,edit;
        TextView infoText,time;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageForCard=itemView.findViewById(R.id.map_chemistry_imageView);
            infoText=itemView.findViewById(R.id.topic_textview);


        }
    }
}

