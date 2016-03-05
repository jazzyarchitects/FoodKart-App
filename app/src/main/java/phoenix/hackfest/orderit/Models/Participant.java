package phoenix.hackfest.orderit.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class Participant implements Parcelable {
    private User user;
    private ArrayList<FoodOrder> foodOrders;
    private String orderId;
    private boolean paid;
    private boolean admin = false;

    public Participant() {
    }

    protected Participant(Parcel in) {
        orderId = in.readString();
        paid = in.readByte() != 0;
//        foodOrders=new FoodOrder(in.readString(), in.readDouble(), in.readInt());
        String array = in.readString();
        try {
            JSONArray jsonArray = new JSONArray(array);
            foodOrders = new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                foodOrders.add(FoodOrder.parseJSONString(jsonArray.getString(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        user = User.parseJSONString(in.readString());
        admin = in.readByte() != 0;
    }

    public static final Creator<Participant> CREATOR = new Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel in) {
            return new Participant(in);
        }

        @Override
        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(ArrayList<FoodOrder> foodOrder) {
        this.foodOrders = foodOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderId);
        dest.writeByte((byte) (paid ? 1 : 0));
        JSONArray jsonArray = new JSONArray();
        for (FoodOrder order : foodOrders) {
            jsonArray.put(order.toJSONString());
        }
        dest.writeString(jsonArray.toString());
        dest.writeString(user.toJSONString());
        dest.writeByte((byte)(admin?1:0));
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (FoodOrder order : foodOrders) {
            totalAmount += order.getCost();
        }
        return totalAmount;
    }

    public String toJSONString(){
        Gson gson=new Gson();
        return gson.toJson(this, Participant.class);
    }

    public static Participant parseJSONString(String s){
        Gson gson=new Gson();
        return gson.fromJson(s, Participant.class);
    }
}
