package com.acercow.oneday.note.edit;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;

public class EditNoteActivity extends BaseActivity {
    WebView wvNote;
    EditText etNote;
    private String mTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_edit_note;
    }

    @Override
    public void initView(View view) {
        wvNote = findViewById(R.id.note_web_view);
        etNote = findViewById(R.id.note_edit_text);
        WebSettings webSettings = wvNote.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);

//        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
//        webSettings.setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录
        mTemp = "# 未标题";

        String body = mTemp.replace("\n", "\\n");

        String result = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\"/>\n" +
                "  <title>Marked in the browser</title>\n" +
                "  <script src=\"file:///android_asset/marked.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"content\"></div>\n" +
                "  <script>\n" +
                "   function myFunction(body)\n" +
                "  {\n" +
                "    document.getElementById('content').innerHTML =\n" +
                "      marked(body);\n" +
                "  }\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>";
        wvNote.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
        wvNote.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wvNote.loadUrl("javascript:myFunction(\'" + body + "\')");
            }
        });
        etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTemp = s.toString();
                wvNote.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
                wvNote.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        wvNote.loadUrl("javascript:myFunction(\'" + mTemp.replace("\n", "\\n") + "\')");
                    }
                });
            }
        });
        etNote.setText(mTemp);
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public void onSingleClick(View v) {

    }
}
