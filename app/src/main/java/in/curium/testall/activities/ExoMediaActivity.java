package in.curium.testall.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.OnBufferUpdateListener;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.OnSeekCompletionListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.google.android.exoplayer.MediaFormat;
import in.curium.testall.R;
import in.curium.testall.adapters.VideoUriListAdapter;
import in.curium.testall.video.samples.Samples;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExoMediaActivity extends AppCompatActivity
    implements OnPreparedListener, OnCompletionListener, OnBufferUpdateListener, OnErrorListener,
    OnSeekCompletionListener, VideoUriListAdapter.OnVideoUriClickedListener {
  private final static long VIDEO_MONITOR_INTERVAL = 500L;
  private final String TAG = ExoMediaActivity.class.getCanonicalName();
  private final String NEW_LINE = "\n";
  private EMVideoView mEmVideoView;
  private Handler mHandler;
  private Runnable mVideoMonitor;
  private TextView mTitle;
  private VideoUriListAdapter mVideoUriListAdapter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exo_media);
    initMembers();
    initView();
    setupVideo();
  }

  private void initMembers() {
    debugLog("entering initMembers");
    mHandler = new Handler();
    mVideoMonitor = new Runnable() {
      @Override public void run() {
        if (mEmVideoView != null && mEmVideoView.isPlaying()) {
          // do monitoring stuff
          videoStateLog();
          // reschedule
          mHandler.postDelayed(mVideoMonitor, VIDEO_MONITOR_INTERVAL);
        }
      }
    };
    debugLog("exiting initMembers");
  }

  private void videoStateLog() {
    // collect data
    if (mEmVideoView != null) {
      debugLog(new StringBuilder("buffer percentage = ").append(mEmVideoView.getBufferPercentage())
          .append(NEW_LINE)
          .append("duration = ")
          .append(mEmVideoView.getDuration())
          .append(NEW_LINE)
          .append("current position = ")
          .append(mEmVideoView.getCurrentPosition())
          .toString());
    }
  }

  private void setupVideo() {
    if (mEmVideoView != null) {
      debugLog("emVideoView is not null");
      debugLog("setting on prepared listener");
      mEmVideoView.setOnPreparedListener(this);
      mEmVideoView.setMeasureBasedOnAspectRatioEnabled(true);
      mEmVideoView.setOnCompletionListener(this);
      mEmVideoView.setOnBufferUpdateListener(this);
      mEmVideoView.setOnErrorListener(this);
      mEmVideoView.setOnSeekCompletionListener(this);
      mEmVideoView.setScaleType(ScaleType.NONE);
      mEmVideoView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          debugLog("mEmVideoView clicked");
          videoStateLog();
        }
      });
      //set video uri
      mEmVideoView.setVideoURI(Samples.HLS[7]);
    }
  }

  private void initView() {
    mEmVideoView = (EMVideoView) findViewById(R.id.em_video_id);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.uri_list_id);
    mTitle = (TextView) findViewById(R.id.em_title_tv);
    if (recyclerView != null) {
      recyclerView.setHasFixedSize(true);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      recyclerView.setLayoutManager(linearLayoutManager);
      mVideoUriListAdapter =
          new VideoUriListAdapter(new ArrayList<>(Arrays.asList(Samples.HLS)), this);
      recyclerView.setAdapter(mVideoUriListAdapter);
    }
  }

  private void startVideoMonitoring() {
    debugLog("entering startVideoMonitoring");
    mHandler.post(mVideoMonitor);
    debugLog("exiting startVideoMonitoring");
  }

  @Override protected void onDestroy() {
    mVideoUriListAdapter.setOnVideoUriClickedListener(null);
    super.onDestroy();
  }

  private void logAvailableTracks() {
    if (mEmVideoView.trackSelectionAvailable()) {
      debugLog("-----------");
      Map<Integer, List<MediaFormat>> availableTracks = mEmVideoView.getAvailableTracks();
      debugLog("tracks = {");
      for (Map.Entry<Integer, List<MediaFormat>> entry : availableTracks.entrySet()) {
        if (EMExoPlayer.RENDER_AUDIO == entry.getKey()) {
        }
        boolean shouldLogValues = true;
        switch (entry.getKey()) {
          case EMExoPlayer.RENDER_AUDIO:
            debugLog("key = RENDER_AUDIO");
            break;
          case EMExoPlayer.RENDER_VIDEO:
            debugLog("key = RENDER_VIDEO");
            break;
          case EMExoPlayer.RENDER_CLOSED_CAPTION:
            debugLog("key = RENDER_CLOSED_CAPTION");
            break;
          case EMExoPlayer.RENDER_TIMED_METADATA:
            debugLog("key = RENDER_TIMED_METADATA");
            break;
          default:
            shouldLogValues = false;
            break;
        }
        if (shouldLogValues) {
          debugLog("value = [");
          for (MediaFormat mediaFormat : entry.getValue()) {
            debugLog(new StringBuilder("mediaFormat: ").append("is adaptive? ")
                .append(mediaFormat.adaptive)
                .append(", bit rate: ")
                .append(mediaFormat.bitrate)
                .append(", channel count = ")
                .append(mediaFormat.channelCount)
                .append(", language = ")
                .append(mediaFormat.language)
                .toString());
          }
        }
      }
      debugLog("}");
      debugLog("-----------");
    }
  }

  @Override public void onPrepared() {
    debugLog("entering onPrepared");
    if (mEmVideoView != null && !mEmVideoView.isPlaying()) {
      debugLog("starting playback");
      mEmVideoView.start();
      mEmVideoView.setVolume(0.4f);
      logAvailableTracks();
      debugLog("is track selection available? " + mEmVideoView.trackSelectionAvailable());
      startVideoMonitoring();
    }
    debugLog("exiting onPrepared");
  }

  @Override public void onCompletion() {
    debugLog("entering onCompletion");
    debugLog("exiting onCompletion");
  }

  private final void debugLog(String message) {
    Log.d(TAG, message);
  }

  @Override public void onBufferingUpdate(@IntRange(from = 0, to = 100) int percent) {
    debugLog("entering onBufferingUpdate with percent = " + percent);
    debugLog("exiting onBufferingUpdate");
  }

  @Override public boolean onError() {
    debugLog("entering onError");
    debugLog("exiting onError");
    return false;
  }

  @Override public void onSeekComplete() {
    debugLog("entering onSeekComplete");
    debugLog("exiting onSeekComplete");
  }

  @Override public void onVideoUriClicked(Uri videoUri) {
    debugLog("entering onVideoUriClicked with videoUri = " + videoUri);
    mTitle.setText(videoUri.toString());
    if (!videoUri.equals(mEmVideoView.getVideoUri())) {
      //mEmVideoView.stopPlayback();
      mEmVideoView.reset();
      mEmVideoView.setVideoURI(videoUri);
    }
    debugLog("exiting onVideoUriClicked");
  }
}
