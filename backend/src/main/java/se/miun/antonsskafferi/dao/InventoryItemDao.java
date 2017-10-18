package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.InventoryItem;

import java.util.List;

public interface InventoryItemDao {
    List<InventoryItem> getAll();
    boolean insert(InventoryItem inventoryItem);
    boolean update(InventoryItem inventoryItem);
}
