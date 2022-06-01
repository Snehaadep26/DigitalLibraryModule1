package com.example.digitallibrarymodule.StudentFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentAdapters.StudentTopicModelAdapter;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.StudentGetTopicsResponse;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;
import com.example.digitallibrarymodule.StudentModels.StudentTopicModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StudentTopicFragment extends Fragment {
View view;
int subjectId,chapterId;
String topic;
TextView topicName;
Retrofit retrofit;
StudentLoginService studentLoginService;
ImageView back;
ArrayList<StudentTopicModel> topicModels;
StudentTopicModelAdapter adapter;
RecyclerView recyclerView;
LinearLayout linearLayout;
LinearLayoutManager linearLayoutManager;



    public StudentTopicFragment(int subjectId, int chapterId, String topic) {
        // Required empty public constructor
        this.subjectId=subjectId;
        this.chapterId=chapterId;
        this.topic=topic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.student_fragment_topic, container, false);
        back=view.findViewById(R.id.back_no_topic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        topicName=view.findViewById(R.id.topic_chapter_name);
        topicName.setText(topic);
        Log.i("s", String.valueOf(subjectId));
        Log.i("c", String.valueOf(chapterId));

        apiInit();
        getTopics();
        return view;
    }
    public void apiInit() {

        retrofit = StudentApiClient.getRetrofit();
        studentLoginService = StudentApiClient.getApiService();

    }

    public void getTopics()
    {
        Call<List<StudentGetTopicsResponse>> call= studentLoginService.getTopicsCall(chapterId,subjectId);
        call.enqueue(new Callback<List<StudentGetTopicsResponse>>() {
            @Override
            public void onResponse(Call<List<StudentGetTopicsResponse>> call, Response<List<StudentGetTopicsResponse>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
            List <StudentGetTopicsResponse> studentGetTopicsResponse =  response.body();
                        topicModels=new ArrayList<>();
                       int topicSize= studentGetTopicsResponse.size();
                Log.i("topicSize", String.valueOf(topicSize));
                if (topicSize==0){
                    linearLayout = view.findViewById(R.id.no_topic2_avialbale_);
                    linearLayout.setVisibility(View.VISIBLE);

                }
                else {

                    for (int i = 0; i <= topicSize - 1; i++) {


                        topicModels.add(new StudentTopicModel(Integer.valueOf(studentGetTopicsResponse.get(i).getVideoCount()), Integer.valueOf(studentGetTopicsResponse.get(i).getQuesBankCount()), Integer.valueOf(studentGetTopicsResponse.get(i).notesCount), String.valueOf(studentGetTopicsResponse.get(i).topicName), String.valueOf(studentGetTopicsResponse.get(i).getTopicId())));


                    }
                    buildRecyclerView();
                }
        }

            @Override
            public void onFailure(Call<List<StudentGetTopicsResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error in get topic", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void buildRecyclerView(){
        recyclerView=view.findViewById(R.id.idRVCourses);
        adapter = new StudentTopicModelAdapter(topicModels,getContext(),subjectId,chapterId);
        LinearLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}