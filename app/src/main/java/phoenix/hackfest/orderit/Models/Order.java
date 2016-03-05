package phoenix.hackfest.orderit.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class Order implements Parcelable {

    String id="";
    String restaurent="", orderingTime="";
    Double targetAmount=0.0, currentAmount=0.0;
    ArrayList<Participant> participants;

    public Order() {
        super();
    }

    protected Order(Parcel in) {
        id = in.readString();
        restaurent = in.readString();
        orderingTime = in.readString();
        participants = in.createTypedArrayList(Participant.CREATOR);
        currentAmount = in.readDouble();
        targetAmount = in.readDouble();
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(restaurent);
        dest.writeString(orderingTime);
        dest.writeTypedList(participants);
        dest.writeDouble(currentAmount);
        dest.writeDouble(targetAmount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurent() {
        return restaurent;
    }

    public void setRestaurent(String restaurent) {
        this.restaurent = restaurent;
    }

    public String getOrderingTime() {
        return orderingTime;
    }

    public void setOrderingTime(String orderingTime) {
        this.orderingTime = orderingTime;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
}
