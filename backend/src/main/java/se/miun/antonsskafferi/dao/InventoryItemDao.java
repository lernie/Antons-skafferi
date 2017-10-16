package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.InventoryItem;

public interface InventoryItemDao {
    java.util.List<InventoryItem> getAll();
    boolean insert(InventoryItem item);
    boolean update(InventoryItem item);
}
