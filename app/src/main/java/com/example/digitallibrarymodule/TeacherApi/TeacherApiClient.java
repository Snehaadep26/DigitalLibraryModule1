package com.example.digitallibrarymodule.TeacherApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeacherApiClient {

    public static Retrofit retrofit = null;
    public static final String BASE_URL = "https://test.theclassroom.biz/api/";

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static TeacherLoginService getApiService() {
        TeacherLoginService apiService = getRetrofit().create(TeacherLoginService.class);
        return apiService;
    }
}
