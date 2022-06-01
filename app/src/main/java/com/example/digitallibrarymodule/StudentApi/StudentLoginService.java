package com.example.digitallibrarymodule.StudentApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StudentLoginService {

    String token="Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTI0NiwicGhvbmUiOiIrOTE5MzkxODE2NTY1IiwidXJsIjoidGVzdC50aGVjbGFzc3Jvb20uYml6Iiwib3JnSWQiOiI0Y2IyNTA5ZC03MGY1LTQzNWUtODc5Mi1kMjQ5Mzc3NDNiNTMiLCJicm93c2VyTG9naW5Db2RlIjoiKzkxOTM5MTgxNjU2NTEyNDZhZmYzMzJkYy1hZTExLTQ5OWUtYTc4Mi04NThkMjY4NWI2OTgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY1NDAwMzMyMX0.QHDQb08JvZ2MED4s6sUs5vEJqTYjRpelrLuCB1JdmRo";
    String link="orgurl:test.theclassroom.biz";
    //SIgn in student
    @Headers({token,link})
    @POST("login/virtual-signin")
    Call<SignInStudentResponse> signInApiCall(@Body  SignInStudentRequest signInStudentRequest);

//home page student
    @Headers({token,link})
    @GET("admin-library/standardById")
     Call<StudentOverAllStateResponse> overallCall();

    //GETCHAPTERLIST
    @Headers({token,link})
    @GET(" admin-library/chapter-list")
    Call<StudentGetChapterResponse> getChapterCall(@Query("subjectId") int subjectId);

    //get topic
    @Headers({token,link})
    @GET(" admin-library/topic-list-by-chapterId")
    Call<List<StudentGetTopicsResponse>> getTopicsCall(@Query("chapterId") int chapterId, @Query("subjectId") int subjectId);



    //library

    @Headers({token,link})
    @GET(" admin-library/library-contents")
    Call<StudentGetLibraryResponse> getLibraryCall(@Query("topicId") int topicId, @Query("chapterId") int chapterId);

    //getLibray_notes
    @Headers({token,link})
    @GET("admin-library/library-contents")
    Call<StudentGetLibraryResponse> getLibraryCall_notes(@Query("topicId")int topicId, @Query("chapterId")int chapterId, @Query("type")String type);


}
