package com.example.digitallibrarymodule.TeacherFragment;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherTopicModelAdapter;
import com.example.digitallibrarymodule.TeacherApi.TeacherApiClient;
import com.example.digitallibrarymodule.TeacherApi.TeacherGetTopicsResponse;
import com.example.digitallibrarymodule.TeacherApi.TeacherLoginService;
import com.example.digitallibrarymodule.TeacherModel.TeacherTopicModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TeacherTopicFragment extends Fragment {
    View view;
    int subjectId, chapterId;
    String topic;
    TextView topicName;
    Retrofit retrofit;
    TeacherLoginService teacherLoginService;
    ImageView back;
    ArrayList<TeacherTopicModel> teacherTopicModels;
    TeacherTopicModelAdapter adapter;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;
    String standardId;


    public TeacherTopicFragment(int subjectId, int chapterId, String standardid, String topic) {
        // Required empty public constructor
        this.subjectId = subjectId;
        this.chapterId = chapterId;
        standardId = standardid;
        this.topic = topic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.teacher_fragment_topic, container, false);
        back = view.findViewById(R.id.back_no_topic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        topicName = view.findViewById(R.id.topic_chapter_name);
        topicName.setText(topic);
        Log.i("s", String.valueOf(subjectId));
        Log.i("c", String.valueOf(chapterId));

        apiInit();
        getTopics();

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayouttopic);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getTopics();
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

    public void getTopics() {
        Log.i("c1", String.valueOf(chapterId));
        Log.i("sub", String.valueOf(subjectId));
        Log.i("st", String.valueOf(standardId));
        Call<List<TeacherGetTopicsResponse>> call = teacherLoginService.getTopicsCall(chapterId, subjectId, Integer.valueOf(standardId));
        call.enqueue(new Callback<List<TeacherGetTopicsResponse>>() {
            @Override
            public void onResponse(Call<List<TeacherGetTopicsResponse>> call, Response<List<TeacherGetTopicsResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<TeacherGetTopicsResponse> teacherGetTopicsResponse = response.body();
                teacherTopicModels = new ArrayList<>();
                int topicSize = teacherGetTopicsResponse.size();
                Log.i("topicSize", String.valueOf(topicSize));
                Log.i("count", teacherGetTopicsResponse.get(0).getQuesBankCount());
                if (topicSize == 0) {
                    linearLayout = view.findViewById(R.id.no_topic2_avialbale_);
                    linearLayout.setVisibility(View.VISIBLE);

                } else {

                    for (int i = 0; i <= topicSize - 1; i++) {
                        teacherTopicModels.add(new TeacherTopicModel(Integer.valueOf(teacherGetTopicsResponse.get(i).notesCount),
                                Integer.valueOf(teacherGetTopicsResponse.get(i).videoCount),
                                Integer.valueOf(teacherGetTopicsResponse.get(i).quesBankCount),
                                String.valueOf(teacherGetTopicsResponse.get(i).topicName),
                                String.valueOf(teacherGetTopicsResponse.get(i).topicId)));


//                        topicModels.add(new TopicModel(Integer.valueOf(getTopicsResponse.get(i).quesBankCount),
//                                Integer.valueOf(getTopicsResponse.get(i).videoCount),
//                                Integer.valueOf(getTopicsResponse.get(i).notesCount),
//                                String.valueOf(getTopicsResponse.get(i).topicName),
//                                String.valueOf(getTopicsResponse.get(i).getTopicId())));


                    }
                    buildRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<TeacherGetTopicsResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error in get topic", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void buildRecyclerView() {
        recyclerView = view.findViewById(R.id.idRVCourses);
        adapter = new TeacherTopicModelAdapter(teacherTopicModels, getContext(), subjectId, chapterId,standardId);
        LinearLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}