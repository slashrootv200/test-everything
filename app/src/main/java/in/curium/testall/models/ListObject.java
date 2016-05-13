package in.curium.testall.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ListObject implements Parcelable {

  public static List<ListObject> getListOfListObject() {
    List<ListObject> listObjects = new ArrayList<>();
    listObjects.add(new ListObject("Pratap", 221));
    listObjects.add(new ListObject("Patil", 4234));
    return listObjects;
  }

  public final String mString;
  public final int mInt;

  public ListObject(String mString, int mInt) {
    this.mString = mString;
    this.mInt = mInt;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mString);
    dest.writeInt(this.mInt);
  }

  protected ListObject(Parcel in) {
    this.mString = in.readString();
    this.mInt = in.readInt();
  }

  public static final Creator<ListObject> CREATOR = new Creator<ListObject>() {
    @Override
    public ListObject createFromParcel(Parcel source) {
      return new ListObject(source);
    }

    @Override
    public ListObject[] newArray(int size) {
      return new ListObject[size];
    }
  };
}
