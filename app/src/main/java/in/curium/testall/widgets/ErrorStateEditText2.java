package in.curium.testall.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import in.curium.testall.R;

public class ErrorStateEditText2 extends FrameLayout {
  private final String TAG = ErrorStateEditText2.class.getCanonicalName();
  private TextInputLayout mTextInputLayout;
  private TextInputEditText mTextInputEditText;
  private int mErrorStateEditText2Style;
  private float mTextSize;
  private int mTextColor;
  private int mErrorTextColor;

  public ErrorStateEditText2(Context context, AttributeSet attrs) {
    super(context, attrs);
    dLog("Constructor 1");
    initView(attrs);
  }

  public ErrorStateEditText2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    dLog("Constructor 2");
    initView(attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ErrorStateEditText2(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    dLog("Constructor 3");
    initView(attrs);
  }

  protected void initView(AttributeSet attrs) {
    dLog("entering initView");
    getValuesFromAttrs(attrs);
    View view = inflateLayout();
    getReferencesToChildViews(view);
    addView(view);
    dLog("exiting initView");
  }

  protected void getReferencesToChildViews(View view) {
    dLog("entering getReferencesToChildViews");
    mTextInputLayout = (TextInputLayout) view.findViewById(R.id.error_state_text_input_layout_id);
    mTextInputEditText = (TextInputEditText) view.findViewById(R.id.error_state_input_edit_text_id);
    dLog("exiting getReferencesToChildViews");
  }

  protected View inflateLayout() {
    dLog("Entering - inflating R.layout.error_state_edit_text_layout");
    View view = inflate(getContext(), R.layout.error_state_edit_text_layout, null);
    dLog("exiting - inflating R.layout.error_state_edit_text_layout");
    return view;
  }

  protected void getValuesFromAttrs(AttributeSet attrs) {
    dLog("Entering getValuesFromAttrs; attrs = " + attrs);
    TypedArray typedArray = getContext().getTheme()
        .obtainStyledAttributes(attrs, R.styleable.ErrorStateEditText2, 0, 0);
    dLog("getting values from TypedArray");
    try {
      dLog("mErrorStateEditText2Style = " + mErrorStateEditText2Style);
      mTextSize = typedArray.getDimension(R.styleable.ErrorStateEditText2_textSize, 12F);
      dLog("mTextSize = " + mTextSize);
      mTextColor = typedArray.getColor(R.styleable.ErrorStateEditText2_textColor,
          ContextCompat.getColor(getContext(), android.R.color.black));
      dLog("mTextColor = " + mTextColor);
      mErrorTextColor = typedArray.getColor(R.styleable.ErrorStateEditText2_errorTextColor,
          ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
      dLog("mErrorTextColor = " + mErrorTextColor);
    } finally {
      dLog("recycling TypedArray");
      typedArray.recycle();
    }
    dLog("Exiting getValuesFromAttrs");
  }

  public void setError(CharSequence error) {
    dLog("entering setError " + error);
    if (TextUtils.isEmpty(error)) {
      // no error
      mTextInputEditText.setTextColor(mTextColor);
    } else {
      // error
      TypedValue typedValue = new TypedValue();
      Resources.Theme theme = getContext().getTheme();
      theme.resolveAttribute(R.attr.textColorError, typedValue, true);
      int color = typedValue.data;
      mTextInputEditText.setTextColor(color);
    }
    mTextInputLayout.setError(error);
    dLog("exiting setError");
  }

  public CharSequence getError() {
    dLog("entering getError");
    CharSequence cs = mTextInputLayout.getError();
    dLog("exiting getError, returning " + cs);
    return cs;
  }

  private final void dLog(String message) {
    if (!isInEditMode()) Log.d(TAG, message);
  }
}
