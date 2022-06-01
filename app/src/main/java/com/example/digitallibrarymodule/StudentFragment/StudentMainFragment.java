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
import com.example.digitallibrarymodule.StudentAdapters.StudentLecturerAdapter;
import com.example.digitallibrarymodule.StudentAdapters.StudentQusetionAdapter;
import com.example.digitallibrarymodule.StudentAdapters.StudentSubjectAdapter;
import com.example.digitallibrarymodule.StudentAdapters.StudentVideoAdapter;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;
import com.example.digitallibrarymodule.StudentApi.StudentOverAllStateResponse;
import com.example.digitallibrarymodule.StudentApi.TotalCount;
import com.example.digitallibrarymodule.StudentModels.StudentLecturerModel;
import com.example.digitallibrarymodule.StudentModels.StudentQuestionModel;
import com.example.digitallibrarymodule.StudentModels.StudentSubjectModel;
import com.example.digitallibrarymodule.StudentModels.StudentVideoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StudentMainFragment extends Fragment {

    View view;
    Retrofit retrofit;
    int length=0;
    StudentLoginService studentLoginService;
    private static final String TAG = "MainFragment";
    StudentVideoAdapter studentVideoAdapter;
    StudentSubjectAdapter studentSubjectAdapter;
    RecyclerView recyclerView, recyclerViewVideo,recyclerViewQuestion,recyclerViewSubject;
    LinearLayoutManager layoutManager, layoutManagerVideo,layoutManagerQuestion,layoutManagerSubject;
    ArrayList<StudentLecturerModel> studentLecturerModels;
    StudentOverAllStateResponse studentOverAllStateResponse;
    LinearLayout foeLecturer,forVideo,forQuestion;
    StudentLecturerAdapter studentLecturerAdapter;
    StudentQusetionAdapter studentQusetionAdapter;
    ArrayList<StudentVideoModel> studentVideoModels;
    ArrayList<StudentQuestionModel> studentQuestionModels;
    ArrayList<StudentSubjectModel> studentSubjectModels;



    public StudentMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.student_fragment_main, container, false);
        foeLecturer=view.findViewById(R.id.lect_linear);
        forVideo=view.findViewById(R.id.video_linear);
        forQuestion=view.findViewById(R.id.question_linear);
        apiInit();
        getStudHomeP();
        getSubject();

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshHomePage);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        apiInit();
                        getStudHomeP();
                        getSubject();
                       
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
    public void getStudHomeP()
    {
        Call<StudentOverAllStateResponse> call= studentLoginService.overallCall();
        call.enqueue(new Callback<StudentOverAllStateResponse>() {
            @Override
            public void onResponse(Call<StudentOverAllStateResponse> call, Response<StudentOverAllStateResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                studentOverAllStateResponse =response.body();
                List<TotalCount> totalCount = studentOverAllStateResponse.totalCount;

                int lecSize= studentOverAllStateResponse.lectureNotes.size();
                length= studentOverAllStateResponse.subjects.size();
                Log.i("length", String.valueOf(length));
                if(length==0){
                }
                else{
                    getSubject();
                }
                Log.i("size", String.valueOf(lecSize));
                if(lecSize==0){
                    foeLecturer.setVisibility(View.GONE);
                }
                else {
                    studentLecturerModels =new ArrayList<>();
                    for (int i = 0; i <= studentOverAllStateResponse.lectureNotes.size() - 1; i++) {
                        studentLecturerModels.add(new StudentLecturerModel(studentOverAllStateResponse.lectureNotes.get(i).subject.icon, studentOverAllStateResponse.lectureNotes.get(i).topic.name, studentOverAllStateResponse.lectureNotes.get(i).chapter.name, studentOverAllStateResponse.lectureNotes.get(i).subject.name, studentOverAllStateResponse.lectureNotes.get(i).file));
                    }
                    build();


                }
                if(studentOverAllStateResponse.video.size()==0) {
                 forVideo.setVisibility(View.GONE);
                }else{
                    studentVideoModels =new ArrayList<>();
                    for (int j = 0; j<= studentOverAllStateResponse.video.size()-1; j++){
                        studentVideoModels.add(new StudentVideoModel(studentOverAllStateResponse.video.get(j).subject.icon, studentOverAllStateResponse.video.get(j).topic.name, studentOverAllStateResponse.video.get(j).chapter.name, studentOverAllStateResponse.video.get(j).subject.name, studentOverAllStateResponse.video.get(j).link, studentOverAllStateResponse.video.get(j).title, studentOverAllStateResponse.video.get(j).status, studentOverAllStateResponse.video.get(j).file));
                    }
                    buildVideo();
                }
                if(studentOverAllStateResponse.questionBank.size()==0) {
                    forQuestion.setVisibility(View.GONE);
                }else{
                    studentQuestionModels =new ArrayList<>();
                    for (int k = 0; k<= studentOverAllStateResponse.questionBank.size()-1; k++){
                        studentQuestionModels.add(new StudentQuestionModel(studentOverAllStateResponse.questionBank.get(k).subject.icon, studentOverAllStateResponse.questionBank.get(k).topic.name, studentOverAllStateResponse.questionBank.get(k).chapter.name, studentOverAllStateResponse.questionBank.get(k).subject.name, studentOverAllStateResponse.questionBank.get(k).file));
                    }
                    buildQuestion();
                }

            }
            @Override
            public void onFailure(Call<StudentOverAllStateResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error fail in home page", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getSubject()
    {
        Call<StudentOverAllStateResponse> call= studentLoginService.overallCall();
        call.enqueue(new Callback<StudentOverAllStateResponse>() {
            @Override
            public void onResponse(Call<StudentOverAllStateResponse> call, Response<StudentOverAllStateResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
               StudentOverAllStateResponse response1=response.body();
                studentSubjectModels =new ArrayList<>();

                int length=0;
                      length=  response1.subjects.size();
                Log.i("hh", String.valueOf(response1.subjects.get(0).chapterCount));
                for(int i=0;i<=length-1;i++){
                    studentSubjectModels.add(new StudentSubjectModel(response1.subjects.get(i).subjects_name,String.valueOf(response1.subjects.get(i).chapterCount),Integer.valueOf(response1.subjects.get(i).notesCount),
                           Integer.valueOf( response1.subjects.get(i).videoCount),Integer.valueOf(response1.subjects.get(i).quesBankCount),response1.subjects.get(i).subjects_id,(response1.subjects.get(i).getIcon())));
                }
                buildSubject();


            }
            @Override
            public void onFailure(Call<StudentOverAllStateResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(getContext(), "Error fail in home page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void build(){
            recyclerView = view.findViewById(R.id.for_ln_rv);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            studentLecturerAdapter = new StudentLecturerAdapter(studentLecturerModels,getActivity());
            recyclerView.setAdapter(studentLecturerAdapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    public void buildVideo(){
        recyclerViewVideo=view.findViewById(R.id.for_v_rv);
        recyclerViewVideo.setHasFixedSize(true);
        layoutManagerVideo = new GridLayoutManager(getContext(),2);
        recyclerViewVideo.setLayoutManager(layoutManagerVideo);
        studentVideoAdapter = new StudentVideoAdapter(studentVideoModels,getContext());
        recyclerViewVideo.setAdapter(studentVideoAdapter);
        recyclerViewVideo.setNestedScrollingEnabled(false);
    }


    public void buildQuestion(){
        recyclerViewQuestion=view.findViewById(R.id.for_q_rv);
        recyclerViewQuestion.setHasFixedSize(true);
        layoutManagerQuestion = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewQuestion.setLayoutManager(layoutManagerQuestion);
         studentQusetionAdapter = new StudentQusetionAdapter(studentQuestionModels,getContext());
        recyclerViewQuestion.setAdapter(studentQusetionAdapter);
        recyclerViewQuestion.setNestedScrollingEnabled(false);
    }
    public void buildSubject() {
        recyclerViewSubject = view.findViewById(R.id.subject_rv);
        recyclerViewSubject.setHasFixedSize(true);
        layoutManagerSubject = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerViewSubject.setLayoutManager(layoutManagerSubject);
        studentSubjectAdapter = new StudentSubjectAdapter(studentSubjectModels, getContext());
        recyclerViewSubject.setAdapter(studentSubjectAdapter);
        recyclerViewSubject.setNestedScrollingEnabled(false);
    }
}


