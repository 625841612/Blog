package com.example.qizhuang.lviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

      String [] weekstring =new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
     ArrayAdapter<String> Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,weekstring);
        this.setListAdaoter(adaoter);
        this.getListView().setOnItemSelectedListener()
        setContentView(R.layout.activity_main);
    }
}
