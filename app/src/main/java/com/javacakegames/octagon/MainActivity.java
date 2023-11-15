package com.javacakegames.octagon;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class MainActivity extends Activity {

  private VideoView videoView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Deal with camera cutout
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
      getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
    }

    // Hide navigation buttons
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
      );
    }

    // Raise system media volume to 1 if muted
    AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    if (audio.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
      audio.setStreamVolume(AudioManager.STREAM_MUSIC, 1, 0);
    }

    // We are ready for content
    setContentView(R.layout.activity_main);

    videoView = findViewById(R.id.videoView);
    videoView.setVideoURI(Uri.parse(
      "android.resource://" + getPackageName() + "/" + R.raw.v720
    ));

    videoView.setOnPreparedListener(mp -> videoView.start());
    videoView.setOnCompletionListener(mp -> finish());

  }

}
