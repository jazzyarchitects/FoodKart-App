package phoenix.hackfest.orderit.Models;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class Participant {
    private User user;
    private FoodOrder foodOrder;
    private String orderId;

    public Participant() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
