package com.example.digitallibrarymodule.TeacherFragment;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.TeacherAdapter.TeacherChapterDetailsAdapter;
import com.example.digitallibrarymodule.TeacherApi.TeacherApiClient;
import com.example.digitallibrarymodule.TeacherApi.TeacherChapterListResponse;
import com.example.digitallibrarymodule.TeacherApi.TeacherLoginService;
import com.example.digitallibrarymodule.TeacherModel.TeacherChapterDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TeacherChapterFragment extends Fragment {
    View view;
    TeacherLoginService teacherLoginService;
    Retrofit retrofit;
    RecyclerView recyclerView;
    TeacherChapterDetailsAdapter teacherChapterDetailsAdapter;
    ArrayList<TeacherChapterDetails> chapterdetails;
    RecyclerView.LayoutManager layoutManager;
    int subjectId;
    LinearLayout linearLayout;
    String standardName2,section2;
    Button addTeacher,later;




    //accessing imageview for adding te teacher
    ImageView setting;
    //back
    ImageView back;
    String standardid;

    String section, standard,standardId,subjectName;

    public TeacherChapterFragment(String section, String standard, String standardId, int subjectId, String subjectName) {
        // Required empty public constructor
        this.section=section;
        this.standard=standard;
        this.standardId=standardId;
        this.subjectId=subjectId;
        this.subjectName=subjectName;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.teacher_fragment_chapter, container, false);
        TransitionInflater inflate = TransitionInflater.from(requireContext());
        setExitTransition(inflate.inflateTransition(R.transition.fade));
        Log.i("standardId", standardId);
        Log.i("subjectId", String.valueOf(subjectId));
       //back
        back=view.findViewById(R.id.back_chapter_detials);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


// Load and use views afterwards
        TextView tv1 =view.findViewById(R.id.subject_name);
        tv1.setText(subjectName);

        apiInit();
        createCard();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutChapter);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        createCard();
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

    public void createCard() {
        Log.i("subjectId", String.valueOf(subjectId));
        Log.i("standardID", String.valueOf(standardId));
        chapterdetails = new ArrayList<>();
        Call<TeacherChapterListResponse> call = teacherLoginService.chapterListCall(subjectId, Integer.valueOf(standardId));

        call.enqueue(new Callback<TeacherChapterListResponse>() {
            @Override
            public void onResponse(Call<TeacherChapterListResponse> call, Response<TeacherChapterListResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                TeacherChapterListResponse teacherChapterListResponse = response.body();
                int length = teacherChapterListResponse.chapters.size();
                Log.i("TAG", String.valueOf(teacherChapterListResponse.getChapterCount()));
                if(length==0){
                    linearLayout=view.findViewById(R.id.no_chapter_avialbale_);
                    linearLayout.setVisibility(View.VISIBLE);

                }
                else {
                    for (int i = 0; i <= teacherChapterListResponse.chapters.size()-1; i++) {
                        chapterdetails.add(new TeacherChapterDetails(String.valueOf(teacherChapterListResponse.chapters.get(i).getChapterNo()),
                                String.valueOf(teacherChapterListResponse.chapters.get(i).getTopicCount()),
                                String.valueOf(teacherChapterListResponse.chapters.get(i).getChapterName()),
                                Integer.valueOf(teacherChapterListResponse.chapters.get(i).getNotesCount()),
                                Integer.valueOf(teacherChapterListResponse.chapters.get(i).getVideoCount()),
                                Integer.valueOf(teacherChapterListResponse.chapters.get(i).getQuesBankCount()),
                                Integer.valueOf(teacherChapterListResponse.chapters.get(i).getChapterId())));
                    }
                }
                buildRecyclerView();
            }
            @Override
            public void onFailure(Call<TeacherChapterListResponse> call, Throwable t) {

            }
        });
    }
    public void buildRecyclerView() {
        recyclerView = view.findViewById(R.id.subject_rvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        teacherChapterDetailsAdapter = new TeacherChapterDetailsAdapter(chapterdetails, getContext(),subjectId,standardId,section2,standardName2,subjectName);
        recyclerView.setAdapter(teacherChapterDetailsAdapter);
    }


}