package jazzyarchitects.ism.hackfest.orderit.Models;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class FoodOrder {
    private String itemName = "";
    private int cost=0, qty=0;

    public FoodOrder(String itemName, int cost, int qty) {
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
