package com.example.digitallibrarymodule.StudentFragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.digitallibrarymodule.R;


public class StudentYoutubeFragment extends Fragment {

    String link,title;
    View view;
    ImageView back;


    public StudentYoutubeFragment(String link, String title) {
        this.link=link;
        this.title=title;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.student_fragment_youtube, container, false);


        back=view.findViewById(R.id.back_youtube_pdf);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        TextView textView=view.findViewById(R.id.youtube_title_name);
        textView.setText(title);

        WebView webView = view.findViewById(R.id.web_youtube);
//        webView.setWebViewClient(new WebViewClient());
//
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//
//
//        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new StudentFullScreenClient(getActivity()){
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
            public void onShowCustomView(View view, CustomViewCallback callback)
            {
                showFullScreen(view,callback);
            }

        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);





        return  view;
    }

}