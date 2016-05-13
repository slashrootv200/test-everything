package in.curium.testall;

import android.app.Activity;

public class ScreenObject<T extends Activity> {
  public final Class<T> klass;
  public final String label;

  public ScreenObject(Class<T> klass) {
    this.klass = klass;
    this.label = klass.getSimpleName();
  }
}
