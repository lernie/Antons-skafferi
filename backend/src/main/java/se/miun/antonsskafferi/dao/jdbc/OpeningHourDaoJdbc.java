package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.OpeningHour;
import se.miun.antonsskafferi.dao.OpeningHourDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpeningHourDaoJdbc implements OpeningHourDao {

    @Override
    public boolean update(OpeningHour opParam) {
        boolean status = true;

        try{
            String sqlQuery = "UPDATE OPENINGHOUR " +
                    "SET openingtime = CASE WHEN ? = '' THEN openingtime ELSE ? END, " +
                    "closingtime = CASE WHEN ? = '' THEN closingtime ELSE ? END " +
                    "WHERE id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setShort(1, opParam.getOpeningTime() == -1 ? 0 : opParam.getOpeningTime());
            ps.setShort(2, opParam.getOpeningTime() == -1 ? 0 : opParam.getOpeningTime());
            ps.setShort(3, opParam.getClosingTime() == -1 ? 0 : opParam.getClosingTime());
            ps.setShort(4, opParam.getClosingTime() == -1 ? 0 : opParam.getClosingTime());
            ps.setInt(5, opParam.getId());

            ps.execute();
            ps.close();
            ConnectionSetup.conn.commit();
        }catch (SQLException sqlExcept){
            status = false;
            sqlExcept.printStackTrace();
        }

        return status;
    }

    @Override
    public List<OpeningHour> get() {
        List<OpeningHour> openingHours = new ArrayList();

        try {
            String sqlQuery = "SELECT ID, DAY, OPENINGTIME, CLOSINGTIME FROM OPENINGHOUR";
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery(sqlQuery);
            while (results.next()) {
                OpeningHour tempOpeningHour = new OpeningHour();
                tempOpeningHour.setId(results.getInt(1));
                tempOpeningHour.setDay(results.getString(2));
                tempOpeningHour.setOpeningTime(results.getShort(3));
                tempOpeningHour.setClosingTime(results.getShort(4));
                openingHours.add(tempOpeningHour);
            }
            results.close();
            stmt.close();

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return openingHours;
    }
}
