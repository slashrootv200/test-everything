package in.curium.testall.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
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
  private float mTextSize;
  private int mTextNormalColor;
  private int mErrorTextColor;
  private boolean mUseErrorColorOnText = false;

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
    setValuesOnViews();
    addView(view);
    dLog("exiting initView");
  }

  protected void setValuesOnViews() {
    initErrorColor();
    mTextInputEditText.setTextSize(mTextSize);
    mTextInputEditText.setTextColor(mTextNormalColor);
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

  protected void updateViewsBasedOnValues() {
    dLog("entering updateViewsBasedOnValues");
    mTextInputEditText.setTextSize(mTextSize);
    mTextInputEditText.setTextColor(mTextNormalColor);
    dLog("exiting updateViewsBasedOnValues");
  }

  protected void getValuesFromAttrs(AttributeSet attrs) {
    dLog("Entering getValuesFromAttrs; attrs = " + attrs);
    TypedArray typedArray = getContext().getTheme()
        .obtainStyledAttributes(attrs, R.styleable.ErrorStateEditText2, 0, 0);
    dLog("getting values from TypedArray");
    try {
      mTextSize = typedArray.getDimension(R.styleable.ErrorStateEditText2_textSize, 12F);
      dLog("mTextSize = " + mTextSize);
      int colorT = getColor(android.R.attr.textColor);
      int colorF = ContextCompat.getColor(getContext(), colorT);
      mTextNormalColor =
          typedArray.getColor(R.styleable.ErrorStateEditText2_textNormalColor, colorF);
      mUseErrorColorOnText =
          typedArray.getBoolean(R.styleable.ErrorStateEditText2_useErrorColorOnText, false);
      dLog("mTextColor = " + mTextNormalColor);
    } finally {
      dLog("recycling TypedArray");
      typedArray.recycle();
    }
    dLog("Exiting getValuesFromAttrs");
  }

  public float getTextSize() {
    return this.mTextSize;
  }

  public void setTextSize(float textSize) {
    this.mTextSize = textSize;
    mTextInputEditText.setTextSize(this.mTextSize);
  }

  public boolean isUseErrorColorOnText() {
    return this.mUseErrorColorOnText;
  }

  public void setUseErrorColorOnText(boolean useErrorColorOnText) {
    this.mUseErrorColorOnText = useErrorColorOnText;
  }

  public void removeError() {
    mTextInputEditText.setTextColor(mTextNormalColor);
    mTextInputLayout.setError(null);
  }

  private int getColor(int colorType) {
    TypedValue typedValue = new TypedValue();
    Resources.Theme theme = getContext().getTheme();
    boolean found = theme.resolveAttribute(colorType, typedValue, true);
    int color = android.R.color.black;
    if (found) color = typedValue.data;
    return color;
  }

  private void initErrorColor() {
    mErrorTextColor = getColor(R.attr.textColorError);
  }

  private void setNormaTextColor(int colorResId) {
    int normalTextColor = ContextCompat.getColor(getContext(), colorResId);
    mTextNormalColor = normalTextColor;
    mTextInputEditText.setTextColor(normalTextColor);
  }

  public CharSequence getError() {
    dLog("entering getError");
    CharSequence cs = mTextInputLayout.getError();
    dLog("exiting getError, returning " + cs);
    return cs;
  }

  public boolean isInErrorState() {
    dLog("entering isInErrorState");
    return !(mTextInputLayout.getError() == null || mTextInputLayout.getError().length() == 0);
  }

  public void setError(CharSequence error) {
    if (error == null || error.length() == 0) {
      if (mUseErrorColorOnText) {
        mTextInputEditText.setTextColor(mErrorTextColor);
      }
      mTextInputLayout.setError(" ");
    } else {
      if (mUseErrorColorOnText) {
        mTextInputEditText.setTextColor(mErrorTextColor);
      }
      mTextInputLayout.setError(error);
    }
  }

  private final void dLog(String message) {
    if (!isInEditMode()) Log.d(TAG, message);
  }
}
