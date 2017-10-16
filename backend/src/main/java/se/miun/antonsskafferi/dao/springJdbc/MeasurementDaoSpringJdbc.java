package se.miun.antonsskafferi.dao.springJdbc;

import se.miun.antonsskafferi.Models.Measurement;
import se.miun.antonsskafferi.dao.MeasurementDao;

import java.util.List;

public class MeasurementDaoSpringJdbc implements MeasurementDao{
    @Override
    public List<Measurement> getAll() {
        return null;
    }

    @Override
    public boolean insert(Measurement measurement) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(int id) {
        return false;
    }
}
