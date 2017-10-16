package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Models.OpeningHour;
import se.miun.antonsskafferi.dao.OpeningHourDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OpeningHourDaoJdbc implements OpeningHourDao{
    @Override
    public List<OpeningHour> getAll() {
        java.util.List<OpeningHour> openingHours = new java.util.ArrayList();

        try
        {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from openinghour");
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                OpeningHour tempOpeningHour = new OpeningHour();
                tempOpeningHour.setId(results.getInt(1));
                tempOpeningHour.setDay(results.getString(2));
                tempOpeningHour.setOpeningTime(results.getShort(3));
                tempOpeningHour.setClosingTime(results.getShort(4));

                openingHours.add(tempOpeningHour);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return openingHours;
    }
}
