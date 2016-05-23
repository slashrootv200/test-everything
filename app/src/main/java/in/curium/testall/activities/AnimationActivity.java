package in.curium.testall.activities;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import in.curium.testall.R;

public class AnimationActivity extends AppCompatActivity {

  private TextView mNumberTv;

  @Override
  protected void onCreate(
      @Nullable
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation);
    mNumberTv = (TextView) findViewById(R.id.number_tv);
    mNumberTv.setText(""+ 0);
    startCountAnimation();
  }

  private void startCountAnimation() {
    ValueAnimator animator = new ValueAnimator();
    animator.setObjectValues(0, 600);
    animator.setDuration(5000);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      public void onAnimationUpdate(ValueAnimator animation) {
        mNumberTv.setText("" + (int) animation.getAnimatedValue());
      }
    });
    animator.start();
  }
}
