package com.example.iu.myapplication.module.pandaculture.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.CultureBean;

public class CultureWebActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_web);


        CultureBean.BigImgBean bigImgBean = getIntent().getParcelableExtra("name");
        String url = bigImgBean.getUrl();


        webView = (WebView) findViewById(R.id.culture_web);
        webView.loadUrl(url);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);


//        webview.addJavascriptInterface(this, "test");


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //开始加载时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                dialog = new ProgressDialog(CultureWebActivity.this);
                dialog.setMessage("请稍等......");
                dialog.show();
            }

            //加载结束时
            @Override
            public void onPageFinished(WebView view, String url) {

                dialog.cancel();
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
