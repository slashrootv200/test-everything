package in.curium.testall.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import in.curium.testall.R;
import in.curium.testall.models.ListObject;
import in.curium.testall.models.ParcelableListContainer;

public class ParcelableListContainerActivity extends AppCompatActivity {

  private Bundle mBundle = new Bundle();
  public static final String KEY = "KEY";
  public static final String EXTRA_KEY = "EXTRA_KEY";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_parcelable_list_container);
    TextView title = (TextView) findViewById(R.id.parcel_title);
    title.setText("Sender");
    Button btn = (Button) findViewById(R.id.parcel_btn);
    btn.setText("Send");
  }

  public void parcelMe(View v) {
    ParcelableListContainer<ListObject> ok = new ParcelableListContainer<>(ListObject.class);
    ok.setListOfT(ListObject.getListOfListObject());
    mBundle.putParcelable(KEY, ok);
    Intent intent = new Intent(this, ParcelableListContainerReaderActivity.class);
    intent.putExtra(EXTRA_KEY, mBundle);
    intent.putExtra(KEY, ok);
    startActivity(intent);
  }
}
