package in.curium.testall.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.devbrackets.android.exomedia.ui.widget.VideoControls;
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
      mEmVideoView.setControls(new VideoControls(getApplicationContext()) {
        @Override
        public void setPosition(
            @IntRange(from = 0L)
            long position) {

        }

        @Override
        public void setDuration(
            @IntRange(from = 0L)
            long duration) {

        }

        @Override
        public void updateProgress(
            @IntRange(from = 0L)
            long position,
            @IntRange(from = 0L)
            long duration,
            @IntRange(from = 0L, to = 100L)
            int bufferPercent) {

        }

        @Override
        protected int getLayoutResource() {
          return 0;
        }

        @Override
        protected void animateVisibility(boolean toVisible) {

        }
      });
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
