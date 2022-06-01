package com.example.digitallibrarymodule.TeacherFragment;

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
import com.example.digitallibrarymodule.TeacherAdapter.TeacherContentAdapter;
import com.example.digitallibrarymodule.TeacherApi.TeacherApiClient;
import com.example.digitallibrarymodule.TeacherApi.TeacherGetLibraryResponse;
import com.example.digitallibrarymodule.TeacherApi.TeacherLoginService;
import com.example.digitallibrarymodule.TeacherModel.TeacherContentModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TeacherLibraryFragment extends Fragment {
    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    TeacherLoginService teacherLoginService;
    Retrofit retrofit;
    Toolbar toolbar;
    public int notes, video, question;
    RecyclerView recyclerView;
    ImageView back;
    TeacherContentAdapter adapter1;
    TeacherGetLibraryResponse teacherGetLibraryResponse;
    ArrayList<TeacherContentModel> teacherContentModels;
TextView topic;
    RecyclerView.LayoutManager layoutManager;


    private int chapterId, topicId, standardId,subjectId;
    String subjectName,topicName,chapterName,standardName,sectionName;

    public TeacherLibraryFragment(int topicId, int standardId, int chapterId, String topicName) {
        this.topicId= topicId;
        this.standardId=standardId;
        this.chapterId= chapterId;
        this.topicName= topicName;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.teacher_fragment_library, container, false);
        topic=view.findViewById(R.id.topic_libary_name);
        topic.setText(topicName);
        Log.i("t1", String.valueOf(topicId));

        Log.i("chap1", String.valueOf(chapterId));
        Log.i("standardddd", String.valueOf(standardId));
        Log.i("topicname", topicName);


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
        retrofit = TeacherApiClient.getRetrofit();
        teacherLoginService = TeacherApiClient.getApiService();
    }


    public void getLibrary() {

        Call<TeacherGetLibraryResponse> call = teacherLoginService.getLibraryCall(topicId,chapterId, standardId);
        call.enqueue(new Callback<TeacherGetLibraryResponse>() {
            @Override
            public void onResponse(Call<TeacherGetLibraryResponse> call, Response<TeacherGetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                teacherGetLibraryResponse = response.body();
                teacherContentModels = new ArrayList<>();
                notes = Integer.parseInt(teacherGetLibraryResponse.totalCount.get(0).notesCount);
                video = Integer.parseInt(teacherGetLibraryResponse.totalCount.get(0).videoCount);
                question = Integer.parseInt(teacherGetLibraryResponse.totalCount.get(0).quesBankCount);
                Log.i("notes", String.valueOf(notes));
                Log.i("videos", String.valueOf(video));
                Log.i("ques", String.valueOf(question));
                teacherContentModels.add(new TeacherContentModel(R.drawable.lecturenotes, String.valueOf(notes), "Lecture notes",String.valueOf(teacherGetLibraryResponse.lastWeekLectureNotesCount+" from last week")));
                teacherContentModels.add(new TeacherContentModel(R.drawable.lecturevideos, String.valueOf(video), "Videos",String.valueOf(teacherGetLibraryResponse.lastWeekVideoCount+" from last week")));
                teacherContentModels.add(new TeacherContentModel(R.drawable.questionbank, String.valueOf(question), "Question Banks",String.valueOf(teacherGetLibraryResponse.lastWeekQuestionBankCount+" from last week")));
                build();

                FragmentManager fm = getParentFragmentManager();
                ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
                final ViewPager2 pa =view. findViewById(R.id.pager);
                pa.setAdapter(sa);
                TabLayout tabLayout = view.findViewById(R.id.tabLayout);
                //
                tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("LECTURE NOTES ("+notes+")")));
                tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("VIDEOS ("+video+")")));
                tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("QUESTION BANKS ("+question+")")));
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
            public void onFailure(Call<TeacherGetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });

    }


    private void build() {
        recyclerView = view.findViewById(R.id.recy1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        adapter1 = new TeacherContentAdapter(teacherContentModels, getContext());
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
                TeacherLibraryLecture fragment = new TeacherLibraryLecture(topicId, chapterId,standardId);
                return fragment;


            }
            if (position == 1) {
                return new TeacherVideosFragment(topicId, chapterId,standardId);
            } else {
                return new TeacherQuestion(topicId, chapterId,standardId);
            }
        }

        @Override
        public int getItemCount() {
            // Hardcoded, use lists
            return 3;
        }
    }

}