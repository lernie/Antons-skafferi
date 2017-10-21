package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Measurement;
import se.miun.antonsskafferi.Models.Unit;
import se.miun.antonsskafferi.dao.MeasurementDao;
import se.miun.antonsskafferi.dao.UnitDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UnitDaoJdbc implements UnitDao {

    @Override
    public List<Unit> getAll() {
        java.util.List<Unit> units = new java.util.ArrayList();

        try
        {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT Id, Name, Prefix FROM Unit");
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                Unit tempUnit = new Unit();
                tempUnit.setId(results.getInt(1));
                tempUnit.setName(results.getString(2));
                tempUnit.setPrefix(results.getString(3));

                units.add(tempUnit);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return units;
    }

    @Override
    public boolean insert(Unit unit) {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO Unit(name, prefix) VALUES (?, ?)");
            ps.setString(1, unit.getName());
            ps.setString(2, unit.getPrefix());

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
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("DELETE FROM Unit WHERE id=?");
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
    public boolean update(Unit unit) {
        boolean status = true;
        try {
            String sqlQuery = "UPDATE Unit " +
                    "SET name = CASE WHEN ? = '' THEN name ELSE ? END, " +
                    "prefix = CASE WHEN ? = '' THEN prefix ELSE ? END " +
                    "WHERE id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
            ps.setString(1, unit.getName() == null ? "" : unit.getName());
            ps.setString(2, unit.getName() == null ? "" : unit.getName());
            ps.setString(3, unit.getPrefix() == null ? "" : unit.getPrefix());
            ps.setString(4, unit.getPrefix() == null ? "" : unit.getPrefix());
            ps.setInt(5, unit.getId());

            ps.execute();
            ps.close();
            ConnectionSetup.conn.commit();

        } catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            status = false;
        }

        return status;
    }
}
