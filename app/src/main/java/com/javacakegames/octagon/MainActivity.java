package com.javacakegames.octagon;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Calendar;

public class MainActivity extends Activity {

  private VideoView videoView;
  private AudioManager audioManager;
  private int resumeVol;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Deal with camera cutout
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      getWindow().setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
      );
      getWindow().getAttributes().layoutInDisplayCutoutMode =
        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
    }

    // Hide navigation buttons
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
      );
    }

    audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    // We are ready for content
    setContentView(R.layout.activity_main);

    videoView = findViewById(R.id.videoView);
    videoView.setVideoURI(Uri.parse(
      "android.resource://" + getPackageName() + "/" + R.raw.v720
    ));

    videoView.setOnPreparedListener(mp -> videoView.start());
    videoView.setOnCompletionListener(mp -> finish());

    Calendar calendar = Calendar.getInstance();
    if (
      calendar.get(Calendar.DATE) == 28 &&
      calendar.get(Calendar.MONTH) == Calendar.AUGUST &&
      calendar.get(Calendar.YEAR) <= 2069 // Doubt he'll live past 100
    ) {
      Toast.makeText(this, "Happy birthday, Jack!", Toast.LENGTH_SHORT).show();
    }

  }

  @Override
  protected void onPause() {
    super.onPause();
    // Mute system volume on pause if it was 0 before entering app and
    // user hasn't raised it.
    if (resumeVol == 0 && getSysVol() == 1) {
      setSysVol(0);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    // Raise system media volume to 1 if muted
    resumeVol = getSysVol();
    if (resumeVol == 0) {
      setSysVol(1);
    }
  }

  private int getSysVol() {
    return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
  }

  private void setSysVol(int level) {
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, level, 0);
  }

}
