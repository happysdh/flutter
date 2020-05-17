package com.example.androidfultterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(

                        FlutterActivity
                                .withCachedEngine("my_engine_id")
                                .build(MainActivity.this)

//                        FlutterActivity
//                                .withNewEngine()
//                                .initialRoute("/my_route")
//                                .build(MainActivity.this)
                );//唤起默认路由页面
            }
        });
    }

}
