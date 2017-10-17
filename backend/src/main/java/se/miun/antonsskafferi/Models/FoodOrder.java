package se.miun.antonsskafferi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.ws.rs.DefaultValue;
import java.sql.Timestamp;

public class FoodOrder {
    private int id; //PK
    private String modification;

    private int foodId = -1; //FK

    private int diningTableId = -1; //FK

    private int orderStatusId = -1; //FK
    private Timestamp ready;
    private Timestamp created;
    private Timestamp delivered;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public int getFoodId() { return foodId; }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Timestamp getReady() {
        return ready;
    }

    public void setReady(Timestamp ready) {
        this.ready = ready;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getDelivered() {
        return delivered;
    }

    public void setDelivered(Timestamp delivered) {
        this.delivered = delivered;
    }

    public int getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(int diningTableId) {
        this.diningTableId = diningTableId;
    }

    @Override
    public String toString() {
        return "Id: " + id + " modification: " + modification + " foodId: " + foodId + " diningTableId: " + diningTableId +
                " orderStatusId: " + orderStatusId + " ready: " + ready + " created: " + created + " deliered: " + delivered;
    }

    @JsonIgnore
    public boolean isValid() {
        return foodId != -1 && diningTableId != -1;
    }
}
