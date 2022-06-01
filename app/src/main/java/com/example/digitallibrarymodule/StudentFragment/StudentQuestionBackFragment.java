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
import com.example.digitallibrarymodule.StudentAdapters.StudentQuestionAdapterTwo;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.Content;
import com.example.digitallibrarymodule.StudentApi.StudentGetLibraryResponse;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;
import com.example.digitallibrarymodule.StudentModels.StudentQuestionModelTwo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StudentQuestionBackFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    StudentQuestionAdapterTwo adapter;
    ArrayList<StudentQuestionModelTwo> questionModels;
    LinearLayoutManager layoutManager;
    //    int chapterId,topicID,standardId;
    StudentLoginService studentLoginService;
    Retrofit retrofit;
    LinearLayout noQuestion;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;
    StudentGetLibraryResponse studentGetLibraryResponse;


    public StudentQuestionBackFragment(int topicId, int chapterId) {
        this.topicId=topicId;
        this.chapterId=chapterId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.student_fragment_question_back, container, false);
        apiInit();

        noQuestion = view.findViewById(R.id.no_question_avialbale);

//
//        chapterId = Integer.valueOf(getArguments().getString("chapterIdQ"));
//        topicID = Integer.valueOf(getArguments().getString("topicIdQ"));
//        standardId = Integer.valueOf(getArguments().getString("standardIdQ"));
//        Log.i("chapter2", String.valueOf(chapterId));
//        Log.i("topic2", String.valueOf(topicId));
//        Log.i("standard2", String.valueOf(standardId));
//        getLibrary();
        return view;

    }

    public void apiInit() {
        retrofit = StudentApiClient.getRetrofit();
        studentLoginService = StudentApiClient.getApiService();
    }


    public void getLibrary() {
        Call<StudentGetLibraryResponse> call = studentLoginService.getLibraryCall_notes(topicId, chapterId, "question-bank");
        call.enqueue(new Callback<StudentGetLibraryResponse>() {
            @Override
            public void onResponse(Call<StudentGetLibraryResponse> call, Response<StudentGetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                studentGetLibraryResponse = response.body();
                ArrayList<Content> contents = studentGetLibraryResponse.contents;
                int length = contents.size();


                Log.i("si", String.valueOf(length));
                if (length == 0) {
                    noQuestion.setVisibility(View.VISIBLE);
                } else {
                    questionModels = new ArrayList<>();
                    for (int i = 0; i <= length - 1; i++) {
                        questionModels.add(new StudentQuestionModelTwo(studentGetLibraryResponse.contents.get(i).title, studentGetLibraryResponse.contents.get(i).file));

                    }
                    buildR();
                }
            }

            @Override
            public void onFailure(Call<StudentGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });
    }


    //    public void forlecturer(){
//        questionModels=new ArrayList<>();
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//
//    }
    public void buildR() {
        recyclerView = view.findViewById(R.id.question_rvvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudentQuestionAdapterTwo(questionModels, getContext());
        recyclerView.setAdapter(adapter);

    }

}