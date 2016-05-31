package in.curium.testall.video.ui;

import android.content.Context;
import com.devbrackets.android.exomedia.ui.widget.VideoControlsMobile;

public class MyVideoControls extends VideoControlsMobile {
  public MyVideoControls(Context context) {
    super(context);
  }

  @Override protected int getLayoutResource() {
    return com.devbrackets.android.exomedia.R.layout.exomedia_default_controls_mobile;
  }
}
