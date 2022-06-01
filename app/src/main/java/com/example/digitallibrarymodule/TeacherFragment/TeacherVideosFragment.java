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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherVideosAdapterTwo;
import com.example.digitallibrarymodule.TeacherApi.TeacherApiClient;
import com.example.digitallibrarymodule.TeacherApi.Content;
import com.example.digitallibrarymodule.TeacherApi.TeacherGetLibraryResponse;
import com.example.digitallibrarymodule.TeacherApi.TeacherLoginService;
import com.example.digitallibrarymodule.TeacherModel.TeacherVideoModelTwo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TeacherVideosFragment extends Fragment {

    View view;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Retrofit retrofit;
    TeacherLoginService teacherLoginService;
    ArrayList<TeacherVideoModelTwo> videoModel;
    TeacherVideosAdapterTwo adapter1;
    LinearLayout novideo;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;



    public TeacherVideosFragment(int topicId, int chapterId, int standardId) {
            this.topicId=topicId;
            this.chapterId=chapterId;
            this.standardId=standardId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.teacher_fragment_videos, container, false);

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
        retrofit = TeacherApiClient.getRetrofit();
        teacherLoginService = TeacherApiClient.getApiService();
    }

    public void getLibrary() {
        Call<TeacherGetLibraryResponse> call = teacherLoginService.getLibraryCall_notes(topicId,standardId,chapterId,"video");
        call.enqueue(new Callback<TeacherGetLibraryResponse>() {
            @Override
            public void onResponse(Call<TeacherGetLibraryResponse> call, Response<TeacherGetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                TeacherGetLibraryResponse teacherGetLibraryResponse = response.body();
                ArrayList<Content> contents = teacherGetLibraryResponse.contents;
                int length= contents.size();
                Log.i("videoSize",String.valueOf(length));

                int size= teacherGetLibraryResponse.contents.size();
                Log.i("size", String.valueOf(size));
                if(size==0){
                    novideo.setVisibility(View.VISIBLE);
                }
                else
                {
                    videoModel=new ArrayList<>();
                    for(int i=0;i<=size-1;i++){
                        videoModel.add(new TeacherVideoModelTwo(R.drawable.mapchem,R.drawable.ic_baseline_more_vert_24, teacherGetLibraryResponse.contents.get(i).title,"12:00", teacherGetLibraryResponse.contents.get(i).link, teacherGetLibraryResponse.contents.get(i).file, teacherGetLibraryResponse.contents.get(i).status));

                    }
                    buildR();
                }


            }
            @Override
            public void onFailure(Call<TeacherGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(),"error", Toast.LENGTH_LONG).show();

            }
        });
    }

    public  void buildR(){
        recyclerView=view.findViewById(R.id.video_rvvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter1 = new TeacherVideosAdapterTwo(videoModel,getContext());
        recyclerView.setAdapter(adapter1);}

}