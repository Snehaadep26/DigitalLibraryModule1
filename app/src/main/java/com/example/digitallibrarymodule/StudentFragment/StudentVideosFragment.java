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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentAdapters.StudentVideosAdapterTwo;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.Content;
import com.example.digitallibrarymodule.StudentApi.StudentGetLibraryResponse;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;
import com.example.digitallibrarymodule.StudentModels.StudentVideoModelTwo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StudentVideosFragment extends Fragment {

    View view;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Retrofit retrofit;
    StudentLoginService studentLoginService;
    ArrayList<StudentVideoModelTwo> videoModel;
    StudentVideosAdapterTwo adapter1;
    LinearLayout novideo;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;

    public StudentVideosFragment(int topicId, int chapterId) {
        this.topicId= topicId;
        this.chapterId= chapterId;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.student_fragment_videos, container, false);

        novideo=view.findViewById(R.id.no_video_avialbale);
        apiInit();
        getLibrary();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutvideos);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        apiInit();
                        getLibrary();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        return view;

    }

    public void apiInit() {
        retrofit = StudentApiClient.getRetrofit();
        studentLoginService = StudentApiClient.getApiService();
    }

    public void getLibrary() {
        Call<StudentGetLibraryResponse> call = studentLoginService.getLibraryCall_notes(topicId,chapterId,"video");
        call.enqueue(new Callback<StudentGetLibraryResponse>() {
            @Override
            public void onResponse(Call<StudentGetLibraryResponse> call, Response<StudentGetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                StudentGetLibraryResponse studentGetLibraryResponse = response.body();
                ArrayList<Content> contents = studentGetLibraryResponse.contents;
                int length= contents.size();
                Log.i("videoSize",String.valueOf(length));

                int size= studentGetLibraryResponse.contents.size();
                Log.i("size", String.valueOf(size));
                if(size==0){
                    novideo.setVisibility(View.VISIBLE);
                }
                else
                {
                    videoModel=new ArrayList<>();
                    for(int i=0;i<=size-1;i++){
                        videoModel.add(new StudentVideoModelTwo(R.drawable.twosizevideo,R.drawable.ic_baseline_more_vert_24, studentGetLibraryResponse.contents.get(i).title,"12:00", studentGetLibraryResponse.contents.get(i).link, studentGetLibraryResponse.contents.get(i).file, studentGetLibraryResponse.contents.get(i).status));

                    }
                    buildR();
                }


            }
            @Override
            public void onFailure(Call<StudentGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(),"error", Toast.LENGTH_LONG).show();

            }
        });
    }

    public  void buildR(){
        recyclerView=view.findViewById(R.id.video_rvvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter1 = new StudentVideosAdapterTwo(videoModel,getContext());
        recyclerView.setAdapter(adapter1);}

}