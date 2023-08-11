package com.example.my_flutter_app;

import android.content.Intent;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

  private static final String CHANNEL = "com.zensar.myFlutterApp/surveyMonkey";
  private MethodChannel.Result result;
  private static final int REQUESTCODE = 120;

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
            .setMethodCallHandler(
                    (call, result) -> {
                      startSurveyMonkeyActivity((String) call.arguments, result);
                    }
            );
  }

  private void startSurveyMonkeyActivity(String sdkData, MethodChannel.Result result) {
    Intent intent = new Intent(this, SMActivity.class);
    intent.putExtra("hash", sdkData);
    this.result = result;
    startActivityForResult(intent, REQUESTCODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUESTCODE && resultCode == RESULT_OK && data != null) {
      String resultString = data.getStringExtra("isSuccess");
      result.success(resultString);
    }
  }
}
