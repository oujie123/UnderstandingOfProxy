package com.gacrnd.gcs.understandingofproxy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gacrnd.gcs.understandingofproxy.ClickInject.InjectUtils;
import com.gacrnd.gcs.understandingofproxy.ClickInject.OnClick;
import com.gacrnd.gcs.understandingofproxy.ClickInject.OnLongClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.injectClickListener(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Log.i(TAG, "click: 按钮1");
                break;
            case R.id.btn2:
                Log.i(TAG, "click: 按钮2");
                break;
            default:
        }
    }

    @OnLongClick({R.id.btn1, R.id.btn2})
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Log.i(TAG, "longClick: 按钮1");
                break;
            case R.id.btn2:
                Log.i(TAG, "longClick: 按钮2");
                break;
            default:
        }
        return false;
    }
}
