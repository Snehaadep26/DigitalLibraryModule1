package com.example.digitallibrarymodule.TeacherApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TeacherLoginService {

    String token="Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTE1NiwicGhvbmUiOiIrOTE4ODg0ODMxMjg0IiwidXJsIjoidGVzdC50aGVjbGFzc3Jvb20uYml6Iiwib3JnSWQiOiI0Y2IyNTA5ZC03MGY1LTQzNWUtODc5Mi1kMjQ5Mzc3NDNiNTMiLCJicm93c2VyTG9naW5Db2RlIjoiKzkxODg4NDgzMTI4NDExNTZjZDZjZTMxZi0xMmYyLTQ2NjAtYjA2ZS05MmRiMmZiNWIxMjkiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY1Mzk3NTgwNX0.tlQQ4fQDAZJCjauq6_ACQW4LZDcp838H4Jc5UclnPw0";
    String link="orgurl:test.theclassroom.biz";


    @Headers({token,link})
    @GET("admin-library/class")
    Call<TeacherHomePageResponse> getHomepageCall();

    //get standard by id get

    @Headers({token,link})
    @GET("admin-library/standardById")
    Call<TeacherGetTeacherResponse> standardCall(@Query("id")int id);

    //chapter
    @Headers({token,link})
    @GET("admin-library/chapter-list")
    Call<TeacherChapterListResponse> chapterListCall(@Query("subjectId")int subjectId, @Query("standardId")int standardId);


    //topic
    @Headers({token,link})
    @GET("admin-library/topic-list-by-chapterId")
    Call<List<TeacherGetTopicsResponse>> getTopicsCall(@Query("chapterId") int chapterId, @Query("subjectId") int subjectId, @Query("standardId")int standardId);


//library
    @Headers({token,link})
    @GET("admin-library/library-contents")
    Call<TeacherGetLibraryResponse> getLibraryCall(@Query("topicId") int topicId, @Query("chapterId")int chapterId, @Query("standardId")int standardId);

    //getLibray_notes
    @Headers({token,link})
    @GET("admin-library/library-contents")
    Call<TeacherGetLibraryResponse> getLibraryCall_notes(@Query("topicId")int topicId, @Query("standardId")int standardId, @Query("chapterId")int chapterId, @Query("type")String type);

}
