package in.curium.testall.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import in.curium.testall.R;

public class ExoMediaActivity extends AppCompatActivity implements OnPreparedListener {
  private final String TAG = ExoMediaActivity.class.getCanonicalName();

  private EMVideoView mEmVideoView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exo_media);
    mEmVideoView = (EMVideoView) findViewById(R.id.em_video_id);
    if (mEmVideoView != null) {
      debugLog("emVideoView is not null");
      debugLog("setting on prepared listener");
      mEmVideoView.setOnPreparedListener(this);
      mEmVideoView.setVideoURI(
          //Uri.parse("http://playertest.longtailvideo.com/adaptive/oceans_aes/oceans_aes.m3u8"));
          Uri.parse(
              "http://dev-media-cdn.mytvstudio.com/medialibrary/videos/Sintel-2010-1080p-29_97FPS-AC3-2273298__mobile.m3u8"));
      //mEmVideoView.start();
      mEmVideoView.setMeasureBasedOnAspectRatioEnabled(true);
    }
  }

  @Override
  public void onPrepared() {
    debugLog("entering onPrepared");
    if (mEmVideoView != null && !mEmVideoView.isPlaying()) {
      debugLog("starting playback");
      mEmVideoView.start();
    }
    debugLog("exiting onPrepared");
  }

  private final void debugLog(String message) {
    Log.d(TAG, message);
  }
}
