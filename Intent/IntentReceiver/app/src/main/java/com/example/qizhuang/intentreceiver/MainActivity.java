package com.example.qizhuang.intentreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;



public class MainActivity extends AppCompatActivity {
    private Button mybutton =null;
    private final  String nobody=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybutton= (Button) findViewById(R.id.myButton);
        mybutton.setOnClickListener(new MyButtonListener());
    }
    class MyButtonListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            //生成一个Intent对象
            Intent intent = new Intent(nobody);
            //在Intent对象当中添加一个键值对
            intent.putExtra("testIntent", "11111111111");
            sendBroadcast(intent);
        }
    }
}
