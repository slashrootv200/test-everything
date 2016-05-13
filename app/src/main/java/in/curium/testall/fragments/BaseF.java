package in.curium.testall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class BaseF extends Fragment {
  protected FragmentManager getSupportFragmentManager() {
    if (getActivity() != null) {
      return getActivity().getSupportFragmentManager();
    }
    return null;
  }

  @Override
  public void onCreate(
      @Nullable
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("fragment", "creating " + getClass().getName());
  }

  @Override
  public void onDestroy() {
    Log.d("fragment", "destroying " + getClass().getName());
    super.onDestroy();
  }

  @Override
  public void onDestroyView() {
    Log.d("fragment", "destroyingView " + getClass().getName());
    super.onDestroyView();
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

  @Override
  public void onSaveInstanceState(Bundle outState) {
    Log.d("fragment", "onSaveInstanceState " + getClass().getName()
    + ", outState = " + outState);
    super.onSaveInstanceState(outState);
  }
}
