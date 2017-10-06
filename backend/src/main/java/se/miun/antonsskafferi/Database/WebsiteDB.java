package se.miun.antonsskafferi.Database;

        import se.miun.antonsskafferi.Models.OpeningHour;
        import se.miun.antonsskafferi.Models.TodaysLunch;
        import se.miun.antonsskafferi.Models.TodaysLunchString;

        import java.text.DateFormat;
        import java.sql.Date;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.text.SimpleDateFormat;

public class WebsiteDB {
    private static String tableName = "openinghours";
    private static String todaysLunchName = "todayslunch";
    private static String foodName = "food";
    private static Statement stmt = null;

    public static java.util.List<OpeningHour> getAllOpeningHours() {
        java.util.List<OpeningHour> openingHours = new java.util.ArrayList();

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
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
}