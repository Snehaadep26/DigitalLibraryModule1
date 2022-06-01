package com.example.digitallibrarymodule;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digitallibrarymodule.StudentApi.SignInStudentRequest;
import com.example.digitallibrarymodule.StudentApi.SignInStudentResponse;
import com.example.digitallibrarymodule.StudentApi.StudentApiClient;
import com.example.digitallibrarymodule.StudentApi.StudentLoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
  public  Button teacher,admin,student;
  SignInStudentRequest signInStudentRequest;
  SignInStudentResponse signInStudentResponse;
  StudentLoginService studentLoginService;
  Retrofit retrofit;
  EditText email,accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_login);
        admin=findViewById(R.id.adminButton);
        teacher=findViewById(R.id.teacherButton);
        student=findViewById(R.id.studentButton);
        email=findViewById(R.id.studLoginText);
        accessToken=findViewById(R.id.accessToken);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(), TeacherMainActivity.class);
                startActivity(intent1);
            }
        });
        signInStudentRequest=new SignInStudentRequest("231296saisiva@gmail.com","1234",true);
        email.setText(signInStudentRequest.email);
        student.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(),StudentMainActivity.class);
                startActivity(intent2);
                    //apiInIt();


                    //signInApiStudent();
                }
            });
        }


    public void apiInIt()
    {

        retrofit = StudentApiClient.getRetrofit();
        studentLoginService=StudentApiClient.getApiService();
    }


    public void signInApiStudent()
    {
        Call<SignInStudentResponse> call=studentLoginService.signInApiCall(signInStudentRequest);
        call.enqueue(new Callback<SignInStudentResponse>() {
            @Override
            public void onResponse(Call<SignInStudentResponse> call, Response<SignInStudentResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                signInStudentResponse=response.body();
                Toast.makeText(getApplicationContext(), signInStudentResponse.data.accessToken, Toast.LENGTH_SHORT).show();
                accessToken.setText(signInStudentResponse.data.accessToken);
                accessToken.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),StudentMainActivity.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<SignInStudentResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error sing in Api", Toast.LENGTH_SHORT).show();
            }
        });

    }

}