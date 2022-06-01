package com.example.digitallibrarymodule;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.digitallibrarymodule.TeacherModel.TeacherFullScreenClient;


public class TeacherWebView extends AppCompatActivity {
    String link,title;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.teacher_activity_youtube);


        back=findViewById(R.id.back_youtube_pdf);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            link= extras.getString("key");
            title=extras.getString("title");
            //The key argument here must match that used in the other activity
        }
        webView();

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.videos);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        TextView textView=findViewById(R.id.youtube_title_name);
        textView.setText(title);


    }
    public  void webView(){
        android.webkit.WebView webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new TeacherFullScreenClient(TeacherWebView.this){
            @Override
            public void onHideCustomView()
            {
                hideFullScreen(webView);
            }
            @Override
            public Bitmap getDefaultVideoPoster()
            {
                return videoBitmap();
            }
            @Override
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback)
            {
                showFullScreen(view,callback);
            }

        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }

}