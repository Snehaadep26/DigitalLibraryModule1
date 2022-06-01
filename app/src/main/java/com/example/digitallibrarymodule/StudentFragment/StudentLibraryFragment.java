package com.example.digitallibrarymodule.StudentFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import com.example.digitallibrarymodule.R;
import com.example.digitallibrarymodule.StudentAdapters.StudentContentAdapter;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.StudentGetLibraryResponse;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;
import com.example.digitallibrarymodule.StudentModels.StudentContentModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StudentLibraryFragment extends Fragment {
    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    StudentLoginService studentLoginService;
    Retrofit retrofit;
    Toolbar toolbar;
    public int notes, video, question;
    RecyclerView recyclerView;
    ImageView back;
    StudentContentAdapter adapter1;
    StudentGetLibraryResponse studentGetLibraryResponse;
    ArrayList<StudentContentModel> studentContentModels;
TextView topic;
    RecyclerView.LayoutManager layoutManager;


    private int chapterId, topicId, standardId,subjectId;
    String subjectName,topicName,chapterName,standardName,sectionName;

    public StudentLibraryFragment(int topicId, int subjectId, int chapterId, String topicName) {
        this.topicId=topicId;
        this.subjectId=subjectId;
        this.chapterId=chapterId;
        this.topicName=topicName;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.student_fragment_library, container, false);
        topic=view.findViewById(R.id.topic_libary_name);
        topic.setText(topicName);

        apiInit();
        back = view.findViewById(R.id.library_activity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        getLibrary();


        return view;
    }

    public void apiInit() {
        retrofit = StudentApiClient.getRetrofit();
        studentLoginService = StudentApiClient.getApiService();
    }


    public void getLibrary() {

        Call<StudentGetLibraryResponse> call = studentLoginService.getLibraryCall(topicId, chapterId);
        call.enqueue(new Callback<StudentGetLibraryResponse>() {
            @Override
            public void onResponse(Call<StudentGetLibraryResponse> call, Response<StudentGetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                studentGetLibraryResponse = response.body();
                studentContentModels = new ArrayList<>();
                notes = Integer.valueOf(studentGetLibraryResponse.totalCount.get(0).notesCount);
                video = Integer.valueOf(studentGetLibraryResponse.totalCount.get(0).videoCount);
                question = Integer.valueOf(studentGetLibraryResponse.totalCount.get(0).quesBankCount);
                Log.i("notes", String.valueOf(notes));
                studentContentModels.add(new StudentContentModel(R.drawable.lecturenotes, String.valueOf(notes), "Lecture notes",String.valueOf(studentGetLibraryResponse.lastWeekLectureNotesCount+" from last week")));
                studentContentModels.add(new StudentContentModel(R.drawable.lecturevideos, String.valueOf(video), "Videos",String.valueOf(studentGetLibraryResponse.lastWeekVideoCount+" from last week")));
                studentContentModels.add(new StudentContentModel(R.drawable.questionbank, String.valueOf(question), "Question Bank",String.valueOf(studentGetLibraryResponse.lastWeekQuestionBankCount+" from last week")));
                build();

                FragmentManager fm = getParentFragmentManager();
                ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
                final ViewPager2 pa =view. findViewById(R.id.pager);
                pa.setAdapter(sa);
                TabLayout tabLayout = view.findViewById(R.id.tabLayout);
                //
                tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("Lecture   notes("+notes+")")));
                tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("videos("+video+")")));
                tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("Question   banks("+question+")")));
                //
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        pa.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
                pa.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        tabLayout.selectTab(tabLayout.getTabAt(position));
                    }
                });


            }
            @Override
            public void onFailure(Call<StudentGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });

    }


    private void build() {
        recyclerView = view.findViewById(R.id.recy1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        adapter1 = new StudentContentAdapter(studentContentModels, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter1);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private class ViewStateAdapter extends FragmentStateAdapter {

        public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Hardcoded in this order, you'll want to use lists and make sure the titles match
            if (position == 0) {
                StudentLibraryLecture fragment = new StudentLibraryLecture(topicId, chapterId);
                return fragment;


            }
            if (position == 1) {
                return new StudentVideosFragment(topicId, chapterId);
            } else {
                return new StudentQuestion(topicId, chapterId);
            }
        }

        @Override
        public int getItemCount() {
            // Hardcoded, use lists
            return 3;
        }
    }

}