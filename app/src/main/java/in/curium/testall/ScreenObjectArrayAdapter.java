package in.curium.testall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ScreenObjectArrayAdapter extends ArrayAdapter<ScreenObject> {

  private LayoutInflater mLayoutInflater;

  public ScreenObjectArrayAdapter(Context context, int resource, int textViewResourceId,
      List<ScreenObject> objects) {
    super(context, resource, textViewResourceId, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ScreenObject screenObject = getItem(position);
    ViewHolder holder;
    if (convertView == null) {
      mLayoutInflater =
          (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = mLayoutInflater.inflate(R.layout.list_of_screen_item, null);
      holder = new ViewHolder();
      holder.tv = (TextView) convertView.findViewById(R.id.list_of_screens_item_tv);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.tv.setText(screenObject.label);
    return convertView;
  }

  static class ViewHolder {
    TextView tv;
  }
}
