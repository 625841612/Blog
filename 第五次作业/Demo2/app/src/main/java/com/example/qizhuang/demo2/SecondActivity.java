package com.example.qizhuang.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



public class SecondActivity extends AppCompatActivity {
    private static final String LIFT_TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secod);
    }
    @Override
    protected void onStart() {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onPause");
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onPostResume");
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onRestart");
        super.onRestart();
    }
    @Override
    protected void onStop() {
        Log.v(MainActivity.LIFT_TAG,"SecondActivity--->onStop");
        super.onStop();
    }
}

