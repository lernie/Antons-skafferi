package se.miun.antonsskafferi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joel on 2017-10-09.
 */

public class OrderServiceItem {
    String created, delivered, modification, ready;
    int foodId, orderStatusId, diningTableId;

    @SerializedName("id")
    int orderId;

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

    public int getDiningTableId() {
        return diningTableId;
    }

    public boolean isSpecial() {
        return modification != null && !"".equals(modification);
    }
}
