package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Models.TodaysLunch;
import se.miun.antonsskafferi.Models.TodaysLunchString;
import se.miun.antonsskafferi.dao.TodaysLunchDao;

import java.sql.*;
import java.util.List;

public class TodaysLunchDaoJdbc  implements TodaysLunchDao {
    @Override
    public List<TodaysLunchString> getAll(Date startDate, Date endDate) {
        java.util.List<TodaysLunch> todaysLunches = new java.util.ArrayList();
        java.util.List<TodaysLunchString> todaysLunchStrings = new java.util.ArrayList();

        try
        {
            Statement stmt = ConnectionSetup.conn.createStatement();
            System.out.println("startdate: " + startDate + " enddate: " + endDate);
            //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //java.sql.Date sqlStartDate = format.parse(startDate);
            ResultSet results = stmt.executeQuery("SELECT Food.Name, TodaysLunch.TodaysDate " +
                    "FROM TodaysLunch " +
                    "INNER JOIN Food ON TodaysLunch.FoodId=Food.Id " +
                    "WHERE TodaysLunch.TodaysDate >= '" + startDate + "' " +
                    "AND TodaysLunch.TodaysDate <= '" + endDate + "'");
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                TodaysLunchString tempTodaysLunchString = new TodaysLunchString();
                tempTodaysLunchString.setFoodName(results.getString(1));
                tempTodaysLunchString.setTodaysDate(results.getString(2));
                todaysLunchStrings.add(tempTodaysLunchString);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return todaysLunchStrings;
    }

    @Override
    public boolean insert(TodaysLunch todaysLunch) {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO TodaysLunch " +
                    "(foodId, todaysDate) VALUES (?, ?)");
            ps.setInt(1, todaysLunch.getFoodId());
            ps.setDate(2, todaysLunch.getTodaysDate());

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
}
