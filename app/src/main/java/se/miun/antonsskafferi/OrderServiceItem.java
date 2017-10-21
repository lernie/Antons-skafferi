package se.miun.antonsskafferi;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by joel on 2017-10-09.
 */

public class OrderServiceItem {
    String modification;
    long created, delivered, ready;
    int foodId, orderStatusId, diningTableId;

    @SerializedName("id")
    int orderId;

    public long getCreated() {
        return created;
    }

    public long getDelivered() {
        return delivered;
    }

    public String getModification() {
        return modification;
    }

    public long getReady() {
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
