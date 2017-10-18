package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.OrderStatus;

import java.util.List;

public interface OrderStatusDao {
    List<OrderStatus> getAll();
}
