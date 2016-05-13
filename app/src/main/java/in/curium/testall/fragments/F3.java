package in.curium.testall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import in.curium.testall.R;

public class F3 extends BaseF {
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      @Nullable
      ViewGroup container,
      @Nullable
      Bundle savedInstanceState) {
    Log.d("fragment", "creatingView " + getClass().getName());
    View view = inflater.inflate(R.layout.fragment, container, false);
    TextView t = (TextView) view.findViewById(R.id.t);
    t.setText("F3");
    return view;
  }
}
