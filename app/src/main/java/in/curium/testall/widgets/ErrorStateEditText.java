package in.curium.testall.widgets;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class ErrorStateEditText extends AppCompatEditText {
  public ErrorStateEditText(Context context) {
    super(context);
  }

  public ErrorStateEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ErrorStateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setError() {
    getBackground().setColorFilter(ContextCompat.getColor(getContext(), android.R.color.black),
        PorterDuff.Mode.SRC_IN);
  }

  public void setNormal() {
    getBackground().setColorFilter(
        ContextCompat.getColor(getContext(), android.R.color.holo_red_dark),
        PorterDuff.Mode.SRC_IN);
  }
}
