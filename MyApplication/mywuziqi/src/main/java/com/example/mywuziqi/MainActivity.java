package com.example.mywuziqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    private wuziqipanel wuziqipanel1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wuziqipanel1= (wuziqipanel)(findViewById(R.id.id_wuqizi));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.action_settings){
            wuziqipanel1.restrat();
            return  true;
        }
        if(id ==R.id.antion_edit){
          wuziqipanel1.Edit();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}