package com.example.qizhuang.demo2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button myButtton;
    static final String LIFT_TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(MainActivity.LIFT_TAG, "FirstAcvity ---> onCreate");
        Button myButton = (Button)findViewById(R.id.myButton);
        myButton.setText("启动第二个Activity");
        myButton.setOnClickListener(new ButtonOnClickListener());
    }
    class ButtonOnClickListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,SecondActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }
    @Override
    protected void onStart() {
        Log.v(MainActivity.LIFT_TAG,"FirstActivity -> onsTart");
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        Log.v(MainActivity.LIFT_TAG,"FirstActivity -> onDestroy");
        super.onDestroy();
    }
    @Override
    protected void onStop() {
        Log.v(MainActivity.LIFT_TAG,"FirstActivity -> onStop");
        super.onStop();
    }
    @Override
    protected void onPause() {
        Log.v(MainActivity.LIFT_TAG,"FirstActivity -> onPause");
        super.onPause();
    }
    @Override
    protected void onResume() {
        Log.v(MainActivity.LIFT_TAG,"FirstActivity -> onResume");
        super.onResume();
    }
    @Override
    protected void onRestart() {
        Log.v(MainActivity.LIFT_TAG,"FirstActivity -> onRestart");
        super.onRestart();
    }
}
