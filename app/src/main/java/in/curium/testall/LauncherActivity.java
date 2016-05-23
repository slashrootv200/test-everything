package in.curium.testall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import in.curium.testall.activities.AnimationActivity;
import in.curium.testall.activities.EditTextTestActivity;
import in.curium.testall.activities.ExoMediaActivity;
import in.curium.testall.activities.FragmentLifeCycleActivity;
import in.curium.testall.activities.ParcelableListContainerActivity;
import in.curium.testall.activities.QueriableServiceConnectedActivity;
import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);
    final List<ScreenObject> screenObjectList = getListOfScreens();
    ScreenObjectArrayAdapter listOfScreensAdapter =
        new ScreenObjectArrayAdapter(this, R.layout.list_of_screen_item,
            R.id.list_of_screens_item_tv, screenObjectList);
    ListView lv = (ListView) findViewById(R.id.list_of_screens);
    lv.setAdapter(listOfScreensAdapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(LauncherActivity.this, screenObjectList.get(position).klass));
      }
    });
  }

  private List<ScreenObject> getListOfScreens() {
    List<ScreenObject> list = new ArrayList<>();
    list.add(new ScreenObject(FragmentLifeCycleActivity.class));
    list.add(new ScreenObject(ParcelableListContainerActivity.class));
    list.add(new ScreenObject(QueriableServiceConnectedActivity.class));
    list.add(new ScreenObject(AnimationActivity.class));
    list.add(new ScreenObject(ExoMediaActivity.class));
    list.add(new ScreenObject(EditTextTestActivity.class));
    return list;
  }
}
