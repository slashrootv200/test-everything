package in.curium.testall.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import in.curium.testall.R;
import java.util.ArrayList;

public class VideoUriListAdapter extends RecyclerView.Adapter<VideoUriListAdapter.UriViewHolder> {
  private ArrayList<Uri> videoUris;
  private OnVideoUriClickedListener onVideoUriClickedListener;
  private final String TAG = VideoUriListAdapter.class.getCanonicalName();

  public VideoUriListAdapter(ArrayList<Uri> videoUris,
      OnVideoUriClickedListener onVideoUriClickedListener) {
    this.videoUris = videoUris;
    this.onVideoUriClickedListener = onVideoUriClickedListener;
  }

  public VideoUriListAdapter() {
  }

  public void setVideoUris(ArrayList<Uri> videoUris) {
    this.videoUris = videoUris;
  }

  public void setOnVideoUriClickedListener(OnVideoUriClickedListener onVideoUriClickedListener) {
    this.onVideoUriClickedListener = onVideoUriClickedListener;
  }

  @Override public UriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // create a new view
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_just_a_text_view, parent, false);
    debugLog("view = " + view);
    return new UriViewHolder(view, onVideoUriClickedListener);
  }

  @Override public int getItemCount() {
    if (videoUris == null || videoUris.isEmpty()) return 0;
    return videoUris.size();
  }

  @Override public void onBindViewHolder(UriViewHolder holder, int position) {
    if (videoUris == null || videoUris.isEmpty() || position > videoUris.size()) return;
    holder.bindValues(videoUris.get(position));
  }

  static class UriViewHolder extends RecyclerView.ViewHolder {
    private TextView tv;
    private OnVideoUriClickedListener onVideoUriClickedListener;

    public void unsetListener() {
      tv.setOnClickListener(null);
    }

    public UriViewHolder(View itemView, OnVideoUriClickedListener onVideoUriClickedListener) {
      super(itemView);
      tv = (TextView) itemView;
      this.onVideoUriClickedListener = onVideoUriClickedListener;
      tv.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          UriViewHolder.this.onVideoUriClickedListener.onVideoUriClicked(
              Uri.parse(tv.getText().toString()));
        }
      });
    }

    public void bindValues(Uri videoUri) {
      tv.setText(videoUri.toString());
    }
  }

  private final void debugLog(String message) {
    Log.d(TAG, message);
  }

  public interface OnVideoUriClickedListener {
    void onVideoUriClicked(Uri videoUri);
  }
}
