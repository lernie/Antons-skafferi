package se.miun.antonsskafferi.dao.springJdbc;

import se.miun.antonsskafferi.Models.InventoryItem;
import se.miun.antonsskafferi.dao.InventoryItemDao;

import java.util.List;

public class InventoryItemDaoSpringJdbc implements InventoryItemDao{
    @Override
    public List<InventoryItem> getAll() {
        return null;
    }

    @Override
    public boolean insert(InventoryItem item) {
        return false;
    }

    @Override
    public boolean update(InventoryItem item) {
        return false;
    }
}
