package com.example.digitallibrarymodule.TeacherFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherLecturerAdapterTwo;
import com.example.digitallibrarymodule.TeacherApi.TeacherApiClient;
import com.example.digitallibrarymodule.TeacherApi.TeacherGetLibraryResponse;
import com.example.digitallibrarymodule.TeacherApi.TeacherLoginService;
import com.example.digitallibrarymodule.TeacherModel.TeacherLecturerModelTwo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TeacherQuestion extends Fragment {
    ArrayList<TeacherLecturerModelTwo> teacherLecturerModelTwo;
    TeacherLecturerAdapterTwo adapter;
    LinearLayoutManager layoutManager;
    View view;
    LinearLayout linearLayout;
    TeacherLoginService teacherLoginService;
    Retrofit retrofit;
    int topicId,chapterId;
    RecyclerView recyclerView;
    int standardId;


    public TeacherQuestion(int topicId, int chapterId, int standardId) {
        this.topicId=topicId;
        this.chapterId=chapterId;
        this.standardId=standardId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.teacher_fragment_library_lecture, container, false);
        linearLayout=view.findViewById(R.id.no_lecturer_notes_avialbale);
        apiInIt();
        getLibrary();
        return view;
    }
    public void apiInIt()
    {
        retrofit= TeacherApiClient.getRetrofit();
        teacherLoginService = TeacherApiClient.getApiService();
    }
    public void getLibrary(){
        Call<TeacherGetLibraryResponse> call= teacherLoginService.getLibraryCall_notes(topicId,standardId,chapterId,"question-bank");
        call.enqueue(new Callback<TeacherGetLibraryResponse>() {
            @Override
            public void onResponse(Call<TeacherGetLibraryResponse> call, Response<TeacherGetLibraryResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                TeacherGetLibraryResponse teacherGetLibraryResponse =response.body();

                int size= teacherGetLibraryResponse.contents.size();
                Log.i("size", String.valueOf(size));
                if(size==0){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    teacherLecturerModelTwo =new ArrayList<>();
                    for(int i=0;i<=size-1;i++){
                        teacherLecturerModelTwo.add(new TeacherLecturerModelTwo(teacherGetLibraryResponse.contents.get(i).title, teacherGetLibraryResponse.contents.get(i).id, teacherGetLibraryResponse.contents.get(i).file));
                    }
                    buildR();
                }

//                List<ContentGL> contentGL=getLibraryResponse.contents;
//                for(ContentGL chg:
//                        contentGL) {
//                    Toast.makeText(getApplicationContext(), String.valueOf(chg.title), Toast.LENGTH_SHORT).show();
                //}
            }

            @Override
            public void onFailure(Call<TeacherGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error in get library", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void buildR() {
        recyclerView = view.findViewById(R.id.lecturernotervv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeacherLecturerAdapterTwo(teacherLecturerModelTwo, getContext());
        recyclerView.setAdapter(adapter);

    }

}