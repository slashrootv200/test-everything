package in.curium.testall.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ParcelableListContainer<T extends Parcelable> implements Parcelable {

  public static final Creator<ParcelableListContainer> CREATOR =
      new Creator<ParcelableListContainer>() {
        @Override
        public ParcelableListContainer createFromParcel(Parcel source) {
          return new ParcelableListContainer(source);
        }

        @Override
        public ParcelableListContainer[] newArray(int size) {
          return new ParcelableListContainer[size];
        }
      };
  private List<T> listOfT;
  private Class<T> clazz;

  public ParcelableListContainer(Class<T> clazz) {
    this.clazz = clazz;
  }

  private Creator<T> creator;

  protected ParcelableListContainer(Parcel in) {
    listOfT = new ArrayList<>();
    String className = in.readString();
    try {
      clazz = (Class<T>) Class.forName(className);
      in.readList(listOfT, clazz.getClassLoader());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public List<T> getListOfT() {
    return listOfT;
  }

  public void setListOfT(List<T> listOfT) {
    this.listOfT = listOfT;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(clazz.getCanonicalName());
    dest.writeTypedList(listOfT);
  }
}