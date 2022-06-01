package com.example.digitallibrarymodule.StudentFragment;

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
import com.example.digitallibrarymodule.StudentAdapters.StudentLecturerAdapterTwo;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.StudentGetLibraryResponse;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;
import com.example.digitallibrarymodule.StudentModels.StudentLecturerModelTwo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StudentLibraryLecture extends Fragment {

    ArrayList<StudentLecturerModelTwo> studentLecturerModelTwo;
    StudentLecturerAdapterTwo adapter;
    LinearLayoutManager layoutManager;
    View view;
    LinearLayout linearLayout;
    StudentLoginService studentLoginService;
    Retrofit retrofit;
    int topicId,chapterId;
    RecyclerView recyclerView;
    public StudentLibraryLecture(int topicId, int chapterId) {
        // Required empty public constructor
        this.topicId=topicId;
        this.chapterId=chapterId;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.student_fragment_library_lecture, container, false);
        linearLayout=view.findViewById(R.id.no_lecturer_notes_avialbale);
        apiInIt();
        getLibrary();
        return view;
    }
    public void apiInIt()
    {
        retrofit= StudentApiClient.getRetrofit();
        studentLoginService = StudentApiClient.getApiService();
    }
    public void getLibrary(){
        Call<StudentGetLibraryResponse> call= studentLoginService.getLibraryCall_notes(topicId,chapterId,"lecture-notes");
        call.enqueue(new Callback<StudentGetLibraryResponse>() {
            @Override
            public void onResponse(Call<StudentGetLibraryResponse> call, Response<StudentGetLibraryResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
               StudentGetLibraryResponse studentGetLibraryResponse =response.body();

                int size= studentGetLibraryResponse.contents.size();
                Log.i("size", String.valueOf(size));
                if(size==0){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    studentLecturerModelTwo =new ArrayList<>();
                    for(int i=0;i<=size-1;i++){
                        studentLecturerModelTwo.add(new StudentLecturerModelTwo(studentGetLibraryResponse.contents.get(i).title, studentGetLibraryResponse.contents.get(i).id, studentGetLibraryResponse.contents.get(i).file));
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
            public void onFailure(Call<StudentGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error in get library", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void buildR() {
        recyclerView = view.findViewById(R.id.lecturernotervv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudentLecturerAdapterTwo(studentLecturerModelTwo, getContext());
        recyclerView.setAdapter(adapter);

    }

}