package jazzyarchitects.ism.hackfest.orderit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class Order implements Parcelable {




    protected Order(Parcel in) {
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
