package in.curium.testall.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import in.curium.testall.R;
import in.curium.testall.widgets.ErrorStateEditText2;

public class EditTextTestActivity extends AppCompatActivity {
  private final String TAG = EditTextTestActivity.class.getCanonicalName();
  private Handler handler;
  //private EditText e1, e2;
  //private ErrorStateEditText e3;
  //private TextInputLayout tIL1, tIL2;
  //private TextInputEditText tIE1, tIE2;
 private ErrorStateEditText2 e4;

  @Override
  protected void onCreate(
      @Nullable
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edittext_test);
    //e1 = (EditText) findViewById(R.id.e1);
    //e2 = (EditText) findViewById(R.id.e2);
    //e3 = (ErrorStateEditText) findViewById(R.id.e3);
    //tIL1 = (TextInputLayout) findViewById(R.id.p1);
    //tIL2 = (TextInputLayout) findViewById(R.id.p2);
    //tIE1 = (TextInputEditText) findViewById(R.id.c1);
    //tIE2 = (TextInputEditText) findViewById(R.id.c2);
    e4 = (ErrorStateEditText2) findViewById(R.id.custom2);
    handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        //e1.getBackground()
        //    .setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black),
        //        PorterDuff.Mode.SRC_IN);
        //e1.setHighlightColor(
        //    ContextCompat.getColor(getApplicationContext(), android.R.color.holo_orange_dark));
        //e3.setError();
      }
    }, 5000L);
    //debugLog("" + tIL1.isErrorEnabled());
    //debugLog("" + tIL2.isErrorEnabled());
  }

  @Override
  protected void onDestroy() {
    handler = null;
    super.onDestroy();
  }

  private final void debugLog(String message) {
    Log.d(TAG, message);
  }

  public void toggleError(View v) {
    //if (tIL1.isErrorEnabled()) {
    //  tIL1.setErrorEnabled(true);
    //}
    //if (tIL1.getError() == null) {
    //  tIL1.setError(" ");
    //} else {
    //  tIL1.setError(null);
    //}
    //if (tIL2.isErrorEnabled()) {
    //  if (tIL2.getError() == null) {
    //    // there is no error
    //    tIL2.setError("Enter proper surname");
    //  } else {
    //    // there is an error
    //    tIL2.setError(null);
    //  }
    //}
    if (!e4.isInErrorState()) {
      e4.setError("The Error");
    } else {
      e4.removeError();
    }
  }
}


