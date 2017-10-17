package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Measurement;
import se.miun.antonsskafferi.dao.MeasurementDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MeasurementDaoJdbc implements MeasurementDao {

    @Override
    public List<Measurement> getAll() {
        java.util.List<Measurement> measurements = new java.util.ArrayList();

        try
        {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT Id, Name, Prefix FROM Measurement");
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                Measurement tempMeasurement = new Measurement();
                tempMeasurement.setId(results.getInt(1));
                tempMeasurement.setName(results.getString(2));
                tempMeasurement.setPrefix(results.getString(3));

                measurements.add(tempMeasurement);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return measurements;
    }

    @Override
    public boolean insert(Measurement measurement) {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO Measurement(name, prefix) VALUES (?, ?)");
            ps.setString(1, measurement.getName());
            ps.setString(2, measurement.getPrefix());

            ps.execute();
            ConnectionSetup.conn.commit();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public boolean delete(int id) {
        boolean status = true;

        try {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("DELETE FROM Measurement WHERE id=?");
            ps.setInt(1, id);

            ps.execute();
            ConnectionSetup.conn.commit();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            status=false;
        }

        return status;
    }

    @Override
    public boolean update(Measurement measurement) {
        return false;
    }
}
