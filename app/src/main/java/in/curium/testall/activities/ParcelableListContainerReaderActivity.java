package in.curium.testall.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import in.curium.testall.R;
import in.curium.testall.models.ListObject;
import in.curium.testall.models.ParcelableListContainer;
import java.util.List;

public class ParcelableListContainerReaderActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_parcelable_list_container);
    TextView title = (TextView) findViewById(R.id.parcel_title);
    title.setText("Reader");
    Button btn = (Button) findViewById(R.id.parcel_btn);
    btn.setText("Read");
  }

  public void parcelMe(View v) {
    Intent intent = getIntent();
    ParcelableListContainer<ListObject> ok2 = null;
    ParcelableListContainer<ListObject> ok1 =
        intent.getParcelableExtra(ParcelableListContainerActivity.KEY);
    Bundle bundle = intent.getBundleExtra(ParcelableListContainerActivity.EXTRA_KEY);
    if (bundle != null) {
      ok2 = bundle.getParcelable(ParcelableListContainerActivity.KEY);
    }
    printList(ok1);
    printList(ok2);
  }

  private void printList(ParcelableListContainer<ListObject> ok) {
    if (ok == null) {
      log("ok == null");
      return;
    }
    List<ListObject> list = ok.getListOfT();
    if (list == null) {
      log("list == null");
      return;
    }
    for (ListObject listObject : list) {
      log(listObject.mString + " " + listObject.mInt);
    }
  }

  private final void log(String message) {
    Log.d("parcellist", message);
  }
}
