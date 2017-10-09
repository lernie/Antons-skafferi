package se.miun.antonsskafferi;

/**
 * Created by joel on 2017-10-09.
 */

public class OrderServiceItem {
    String created, delivered, modification, ready;
    int foodId, orderStatusId, diningTableOrderId;

    public OrderServiceItem() {   }

    public String getCreated() {
        return created;
    }

    public String getDelivered() {
        return delivered;
    }

    public String getModification() {
        return modification;
    }

    public String getReady() {
        return ready;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public int getDiningTableOrderId() {
        return diningTableOrderId;
    }
}
