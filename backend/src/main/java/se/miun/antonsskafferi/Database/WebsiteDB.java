package se.miun.antonsskafferi.Database;

        import se.miun.antonsskafferi.Models.OpeningHour;
        import se.miun.antonsskafferi.Models.TodaysLunch;
        import se.miun.antonsskafferi.Models.TodaysLunchString;

        import java.sql.*;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;

public class WebsiteDB {
    private static String tableName = "openinghour";
    private static String todaysLunchName = "todayslunch";
    private static String foodName = "food";
    private static Statement stmt = null;

    @Deprecated
    public static java.util.List<OpeningHour> getAllOpeningHours() {
        java.util.List<OpeningHour> openingHours = new java.util.ArrayList();

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID, DAY, OPENINGTIME, CLOSINGTIME from " + tableName);
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

    public static java.util.List<TodaysLunchString> getAllTodaysLunch(Date startDate, Date endDate){
        java.util.List<TodaysLunch> todaysLunches = new java.util.ArrayList();
        java.util.List<TodaysLunchString> todaysLunchStrings = new java.util.ArrayList();

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            System.out.println("startdate: " + startDate + " enddate: " + endDate);
            //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //java.sql.Date sqlStartDate = format.parse(startDate);
            ResultSet results = stmt.executeQuery("SELECT Food.Name, TodaysLunch.TodaysDate, TodaysLunch.Price, Food.id " +
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
                tempTodaysLunchString.setPrice(results.getInt(3));
                tempTodaysLunchString.setId(results.getInt(4));
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

    public static boolean addTodaysLunch(TodaysLunch todaysLunch)
    {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO TodaysLunch " +
                    "(foodId, todaysDate, Price) VALUES (?, ?, ?)");
            ps.setInt(1, todaysLunch.getFoodId());
            ps.setDate(2, todaysLunch.getTodaysDate());
            ps.setInt(3, todaysLunch.getPrice());

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

    public static boolean deleteTodaysLunch(Date date, int id) {
        boolean status = true;
        try {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("DELETE FROM TodaysLunch WHERE TODAYSDATE = ? AND FOODID = ?");
            ps.setDate(1, date);
            ps.setInt(2, id);

            ps.execute();
            ps.close();
        }catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            status = false;
        }
        return status;
    }
}