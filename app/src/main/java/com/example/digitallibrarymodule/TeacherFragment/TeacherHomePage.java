package com.example.digitallibrarymodule.TeacherFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherCountAdapter;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherLecturerAdapter;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherParentRecyclerViewAdapter;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherQusetionAdapter;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherVideoAdapter;
import com.example.digitallibrarymodule.TeacherApi.TeacherApiClient;
import com.example.digitallibrarymodule.TeacherApi.TeacherHomePageResponse;
import com.example.digitallibrarymodule.TeacherApi.TeacherLoginService;
import com.example.digitallibrarymodule.TeacherModel.TeacherCountModel;
import com.example.digitallibrarymodule.TeacherModel.TeacherLecturerModel;
import com.example.digitallibrarymodule.TeacherModel.TeacherParentModel;
import com.example.digitallibrarymodule.TeacherModel.TeacherQuestionModel;
import com.example.digitallibrarymodule.TeacherModel.TeacherVideoModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TeacherHomePage extends Fragment {
    View view;
    private static final String TAG = "HomePage";
    int lecSize=0;

    ArrayList<TeacherCountModel> cardHori;
    TeacherHomePageResponse homePageGetAllStdResponse,overAllStateResponse;
    RecyclerView recyclerView1;
    Retrofit retrofit;
    TeacherLoginService loginService;
    TeacherCountAdapter cardHoriAdapter;
    LinearLayout foeLecturer,forVideo,forQuestion;
    RecyclerView recyclerView, recyclerViewVideo,recyclerViewQuestion,recyclerViewSubject;
    LinearLayoutManager layoutManager, layoutManagerVideo,layoutManagerQuestion,layoutManagerSubject;
    ArrayList<TeacherLecturerModel> lecturerModels;
    ArrayList<TeacherVideoModel> videoModels;
    ArrayList<TeacherQuestionModel> questionModels;
    TeacherVideoAdapter videoAdapter;
    TeacherLecturerAdapter lecturerAdapter;
    TeacherQusetionAdapter qusetionAdapter;
    ArrayList<TeacherParentModel> parentModelArrayList;
    private RecyclerView.LayoutManager parentLayoutManager;
    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ConstraintLayout cons;


    RecyclerView.LayoutManager layoutManager1;

    //
    int   length=0;



    public TeacherHomePage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.teacher_fragment_home_page, container, false);
        apiInit();
        getSubmitAns();
        getStudHomeP();
        standard();
        foeLecturer=view.findViewById(R.id.lect_linear);
        forVideo=view.findViewById(R.id.video_linear);
        forQuestion=view.findViewById(R.id.question_linear);
        return  view;
    }

    public void apiInit() {

        retrofit = TeacherApiClient.getRetrofit();
        loginService = TeacherApiClient.getApiService();

    }

    public void getSubmitAns() {
        try {


            Call<TeacherHomePageResponse> call = loginService.getHomepageCall();
            call.enqueue(new Callback<TeacherHomePageResponse>() {
                @Override
                public void onResponse(Call<TeacherHomePageResponse> call, Response<TeacherHomePageResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                    }
                    TeacherHomePageResponse homePageGetAllStdResponse = response.body();
//                Log.i("response", retrofit.);
                    cardHori = new ArrayList<>();
                    Log.i(TAG, ": ");
                    cardHori.add(new TeacherCountModel(R.drawable.notes,homePageGetAllStdResponse.count.notesCount,"Lecture",String.valueOf(homePageGetAllStdResponse.count.lastWeekLectureNotesCount+"  From last week")));
                    cardHori.add(new TeacherCountModel(R.drawable.videos,homePageGetAllStdResponse.count.videoCount,"videos",String.valueOf(homePageGetAllStdResponse.count.lastWeekVideoCount+"  From last week")));
                    cardHori.add(new TeacherCountModel(R.drawable.quebank,homePageGetAllStdResponse.count.quesBankCount,"Question",String.valueOf(homePageGetAllStdResponse.count.lastWeekQuestionBankCount+"  From last week")));
                    buildRecyclerView();

                }


                @Override
                public void onFailure(Call<TeacherHomePageResponse> call, Throwable t) {
                    Log.i("TAG", String.valueOf(t.getMessage()));
                    Toast.makeText(getContext(), "Error submit :(", Toast.LENGTH_LONG).show();

                }
            });
        }catch (Exception e){
            Log.i(TAG,"login"+  e.getMessage());
        }
    }
    public void buildRecyclerView() {

        recyclerView1 = view.findViewById(R.id.recyler1);
        recyclerView1.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        cardHoriAdapter = new TeacherCountAdapter(cardHori);
        recyclerView1.setAdapter(cardHoriAdapter);
        recyclerView1.setNestedScrollingEnabled(false);
    }

    public void getStudHomeP()
    {
        Log.i("TAG", "welocome to page");
        Call<TeacherHomePageResponse> call=loginService.getHomepageCall();
        Log.i("TAG", "welocome");
        call.enqueue(new Callback<TeacherHomePageResponse>() {
            @Override
            public void onResponse(Call<TeacherHomePageResponse> call, Response<TeacherHomePageResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                overAllStateResponse=response.body();


                lecSize=overAllStateResponse.lectureNotes.size();
                Log.i("length", String.valueOf(length));
                if(length==0){
                }
                else{

                }
                Log.i("lecturerSizeCards", String.valueOf(lecSize));
                if(lecSize==0){
                    foeLecturer.setVisibility(View.GONE);
                }
                else {
                    lecturerModels=new ArrayList<>();
                    for (int i = 0; i <= overAllStateResponse.lectureNotes.size() - 1; i++) {
                        lecturerModels.add(new TeacherLecturerModel(R.drawable.sbj_chemistry,overAllStateResponse.lectureNotes.get(i).chapter.name,overAllStateResponse.lectureNotes.get(i).topic.name,overAllStateResponse.lectureNotes.get(i).subject.name,overAllStateResponse.lectureNotes.get(i).file));
                    }
                    build();


                }
                if(overAllStateResponse.video.size()==0) {
                    forVideo.setVisibility(View.GONE);
                }else{
                    videoModels=new ArrayList<>();
                    for (int j=0;j<=overAllStateResponse.video.size()-1;j++){

                        videoModels.add(new TeacherVideoModel(R.drawable.videos,overAllStateResponse.video.get(j).chapter.name,overAllStateResponse.video.get(j).topic.name,overAllStateResponse.video.get(j).subject.name,overAllStateResponse.video.get(j).link,overAllStateResponse.video.get(j).title,overAllStateResponse.video.get(j).status,overAllStateResponse.video.get(j).file));
                    }
                    buildVideo();
                }
                if(overAllStateResponse.questionBank.size()==0) {
                    forQuestion.setVisibility(View.GONE);
                }else{
                    questionModels=new ArrayList<>();
                    for (int j=0;j<=overAllStateResponse.questionBank.size()-1;j++){
                        questionModels.add(new TeacherQuestionModel(R.drawable.question_banks,overAllStateResponse.questionBank.get(j).chapter.name,overAllStateResponse.questionBank.get(j).topic.name,overAllStateResponse.questionBank.get(j).subject.name,overAllStateResponse.questionBank.get(j).file));
                    }
                    buildQuestion();
                }

            }
            @Override
            public void onFailure(Call<TeacherHomePageResponse> call, Throwable t) {
                Log.i("msg", String.valueOf(t.getMessage()));

                Toast.makeText(getContext(),String.valueOf( t.getMessage()), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void build(){
        recyclerView = view.findViewById(R.id.for_ln_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        lecturerAdapter = new TeacherLecturerAdapter(lecturerModels,getActivity());
        recyclerView.setAdapter(lecturerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }
    public void buildVideo(){
        recyclerViewVideo=view.findViewById(R.id.for_v_rv);
        recyclerViewVideo.setHasFixedSize(true);
        layoutManagerVideo = new GridLayoutManager(getContext(),2);
        recyclerViewVideo.setLayoutManager(layoutManagerVideo);
        videoAdapter = new TeacherVideoAdapter(videoModels,getContext());
        recyclerViewVideo.setAdapter(videoAdapter);
        recyclerViewVideo.setNestedScrollingEnabled(false);
    }


    public void buildQuestion(){
        recyclerViewQuestion=view.findViewById(R.id.for_q_rv);
        recyclerViewQuestion.setHasFixedSize(true);
        layoutManagerQuestion = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewQuestion.setLayoutManager(layoutManagerQuestion);
        qusetionAdapter= new TeacherQusetionAdapter(questionModels,getContext());
        recyclerViewQuestion.setAdapter(qusetionAdapter);
        recyclerViewQuestion.setNestedScrollingEnabled(false);
    }

    public void standard() {
        Call<TeacherHomePageResponse> call = loginService.getHomepageCall();
        call.enqueue(new Callback<TeacherHomePageResponse>() {
            @Override
            public void onResponse(Call<TeacherHomePageResponse> call, Response<TeacherHomePageResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                homePageGetAllStdResponse = response.body();
                ArrayList<String> standard=new ArrayList();
                for(int i=0;i<=homePageGetAllStdResponse.data.size()-1;i++) {
                    standard.add(homePageGetAllStdResponse.data.get(i).std_std);
                }
                Set values=new HashSet(standard);
                Log.i("tag",values.toString());
                ArrayList<String> uni=new ArrayList<>(values);
                parentModelArrayList = new ArrayList<>();
                for (int i = 0; i <= values.size()-1; i++) {
                    parentModelArrayList.add(new TeacherParentModel(uni.get(i)));
                }
                buildRecyclerView2();

            }

            @Override
            public void onFailure(Call<TeacherHomePageResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error submit :(", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void buildRecyclerView2() {
        parentRecyclerView = view.findViewById(R.id.rv_parent);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(getContext());
        ParentAdapter = new TeacherParentRecyclerViewAdapter(parentModelArrayList, getContext(), homePageGetAllStdResponse, cons);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();
    }

}
