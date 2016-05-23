package in.curium.testall.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import in.curium.testall.R;
import in.curium.testall.widgets.ErrorStateEditText;

public class EditTextTestActivity extends AppCompatActivity {

  private Handler handler;
  private EditText e1, e2;
  private ErrorStateEditText e3;

  @Override
  protected void onCreate(
      @Nullable
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edittext_test);
    e1 = (EditText) findViewById(R.id.e1);
    e2 = (EditText) findViewById(R.id.e2);
    e3 = (ErrorStateEditText) findViewById(R.id.e3);
    handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        e1.getBackground()
            .setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black),
                PorterDuff.Mode.SRC_IN);
        e1.setHighlightColor(
            ContextCompat.getColor(getApplicationContext(), android.R.color.holo_orange_dark));
        e3.setError();
      }
    }, 5000L);
  }

  @Override
  protected void onDestroy() {
    handler = null;
    super.onDestroy();
  }
}


