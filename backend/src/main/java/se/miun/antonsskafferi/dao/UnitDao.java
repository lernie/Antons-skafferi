package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Unit;

public interface UnitDao {
    java.util.List<Unit> getAll();
    boolean insert(Unit unit);
    boolean delete(int id);
    boolean update(Unit unit);
}
