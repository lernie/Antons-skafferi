package se.miun.antonsskafferi.Database;

        import se.miun.antonsskafferi.Models.OpeningHour;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class WebsiteDB {
    private static String tableName = "openinghours";
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
}