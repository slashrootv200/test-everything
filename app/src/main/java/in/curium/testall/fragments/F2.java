package in.curium.testall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import in.curium.testall.R;

public class F2 extends BaseF {
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
    t.setText("F2");
    t.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.f, new F3())
            .addToBackStack(null)
            .commit();
        ;
      }
    });
    return view;
  }
}
