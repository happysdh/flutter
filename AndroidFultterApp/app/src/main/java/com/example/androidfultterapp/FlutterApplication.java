package com.example.androidfultterapp;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class FlutterApplication extends Application {
    FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        flutterEngine = new FlutterEngine(this);


//        // Configure an initial route.
//        flutterEngine.getNavigationChannel().setInitialRoute("your/route/here");

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);
    }
}
