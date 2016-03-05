package phoenix.hackfest.orderit.Models;

import com.google.gson.Gson;

import phoenix.hackfest.orderit.CustomViews.RupeeEditText;
import phoenix.hackfest.orderit.CustomViews.RupeeTextView;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class FoodOrder {
    private String itemName = "";
    private double cost=0;
    private  int qty=0;

    public FoodOrder(String itemName, double cost, int qty) {
        this.itemName = itemName;
        this.cost = cost;
        this.qty = qty;
    }

    public FoodOrder() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return this.itemName+ " "+this.qty+" "+this.getCost();
    }

    public String toJSONString(){
        Gson gson=new Gson();
        return gson.toJson(this, FoodOrder.class);
    }

    public static FoodOrder parseJSONString(String string){
        Gson gson=new Gson();
        return gson.fromJson(string, FoodOrder.class);
    }

    public String getListing(){
        return this.getItemName()+"      "+this.getQty()+"       "+ RupeeTextView.RUPEE+" "+this.getCost()+" /-";
    }
}
