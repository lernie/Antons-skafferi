package se.miun.antonsskafferi.Database;

import se.miun.antonsskafferi.Models.Measurement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MeasurementDB {

    private static String tableName = "measurement";
    private static Statement stmt = null;

    public static java.util.List<Measurement> getAllMeasurements() {
        java.util.List<Measurement> measurements = new java.util.ArrayList();

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
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


    public static boolean insertMeasurement(Measurement measurement)
    {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO " + tableName + "(name, prefix) VALUES (?, ?)");
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

    public static boolean delMeasurement(int id) {
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
}
