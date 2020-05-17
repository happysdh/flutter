## 创建Flutter模块
```
cd some/path/

flutter create -t module --org com.example my_flutter

```

## Android篇
### 项目建设
1. 设置Java 8
```java
android {
  //...
  compileOptions {
    sourceCompatibility 1.8
    targetCompatibility 1.8
  }
}
```
2. 在settings.gradle文件中加入如下内容

```java

// Include the host app project.
include ':app'                                    // assumed existing content
setBinding(new Binding([gradle: this]))                                // new
evaluate(new File(                                                     // new
  settingsDir.parentFile,                                              // new
  'my_flutter/.android/include_flutter.groovy'                         // new
))
```
建议把my_flutter文件夹与Android项目文件夹平级

3. 在build.gradle中加入

```java
dependencies {
  implementation project(':flutter')
}
```

sync 项目即可
### FlutterActivity方式载入Flutter
1. AndroidManifest.xml

```java
<activity
  android:name="io.flutter.embedding.android.FlutterActivity"
  android:theme="@style/LaunchTheme"
  android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale|layoutDirection|fontScale|screenLayout|density|uiMode"
  android:hardwareAccelerated="true"
  android:windowSoftInputMode="adjustResize"
  />
```

2. Application

```java
public class FlutterApplication extends Application {
    FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        flutterEngine = new FlutterEngine(this);


//  Configure an initial route.
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
```

3. Activity中启动FlutterActivity

```java
startActivity(
//以已缓存my_engine_id引擎启动
FlutterActivity
                              .withCachedEngine("my_engine_id")
                                .build(MainActivity.this)

//每次已新引擎启动
//FlutterActivity
//                                .withNewEngine()
//                                .initialRoute("/my_route")
//                                .build(MainActivity.this)
                );//唤起默认路由页面
```
## iOS篇
### 项目建设
当前APP项目必须iOS 8.0及以上
1. Podfile
如果项目没有Podfile，执行pod init
``` swift
flutter_application_path = '../my_flutter'
load File.join(flutter_application_path, '.ios', 'Flutter', 'podhelper.rb')

target 'MyApp' do
  install_all_flutter_pods(flutter_application_path)
end

```
Run pod install.


### 启动Flutter

1. AppDelegate.swift

``` swift
import UIKit
import Flutter
// Used to connect plugins (only if you have plugins with iOS platform code).
import FlutterPluginRegistrant

@UIApplicationMain
class AppDelegate: FlutterAppDelegate { // More on the FlutterAppDelegate.
  lazy var flutterEngine = FlutterEngine(name: "my flutter engine")

  override func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    // Runs the default Dart entrypoint with a default Flutter route.
    flutterEngine.run();
    // Used to connect plugins (only if you have plugins with iOS platform code).
    GeneratedPluginRegistrant.register(with: self.flutterEngine);
    return super.application(application, didFinishLaunchingWithOptions: launchOptions);
  }
}

```

[更多苹果Flutter](https://flutter.dev/docs/development/add-to-app/ios/add-flutter-screen?tab=initial-route-swift-tab)