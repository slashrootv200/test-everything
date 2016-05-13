package in.curium.testall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import in.curium.testall.fragments.F1;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void click(View v) {
    getSupportFragmentManager().beginTransaction().add(R.id.f, new F1()).commit();
  }

  @Override
  public void onResume() {
    super.onResume();
    Log.d("fragment", "resuming " + getClass().getName());
  }

  @Override
  public void onPause() {
    super.onPause();
    Log.d("fragment", "pausing " + getClass().getName());
  }
}
