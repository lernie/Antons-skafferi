package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Measurement;

public interface MeasurementDao {
    java.util.List<Measurement> getAll();
    boolean insert(Measurement measurement);
    boolean delete(int id);
    boolean update(Measurement measurement);
}
