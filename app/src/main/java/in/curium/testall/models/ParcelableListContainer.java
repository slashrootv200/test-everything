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

  public ParcelableListContainer() {
  }

  protected ParcelableListContainer(Parcel in) {
    this.listOfT = new ArrayList<>();
    in.readTypedList(this.listOfT, null);
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
    dest.writeTypedList(this.listOfT);
  }
}