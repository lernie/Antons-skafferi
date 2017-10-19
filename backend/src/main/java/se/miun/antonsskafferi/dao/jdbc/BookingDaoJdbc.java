package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ApplicationException;
import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Booking;
import se.miun.antonsskafferi.dao.BookingDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoJdbc implements BookingDao {
    public String SQLError = "";

    @Override
    public List<Booking> get() {
        List<Booking> bookings = new ArrayList();

        try{
            String sqlQuery = "SELECT ID, LASTNAME, NUMBEROFGUESTS, BOOKINGDATE, BOOKINGTIME, PHONENUMBER FROM BOOKING";
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery(sqlQuery);

            while(results.next()){
                Booking tempBooking = new Booking();
                tempBooking.setId(results.getInt(1));
                tempBooking.setLastName(results.getString(2));
                tempBooking.setNumberOfGuests(results.getInt(3));
                tempBooking.setBookingDate(results.getDate(4));
                tempBooking.setBookingTime(results.getTime(5));
                tempBooking.setPhoneNumber(results.getString(6));
                bookings.add(tempBooking);
            }
            results.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean insert(Booking bkParam) {
        boolean status = true;
        try {
            String sqlQuery = "INSERT INTO BOOKING(LASTNAME, NUMBEROFGUESTS, BOOKINGDATE, BOOKINGTIME, PHONENUMBER) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setString(1, bkParam.getLastName());
            ps.setInt(2, bkParam.getNumberOfGuests());
            ps.setDate(3, bkParam.getBookingDate());
            ps.setTime(4, bkParam.getBookingTime());
            ps.setString(5, bkParam.getPhoneNumber());

            ps.execute();
            ps.close();
            ConnectionSetup.conn.commit();
        }catch(SQLException sqlExcept){
            status = false;
            sqlExcept.printStackTrace();

        }
        return status;
    }

    @Override
    public boolean checkIfLess(int count, Date date) {
        boolean status = true;

        try {
            String sqlQuery = "SELECT COUNT(*) " +
                    "FROM Booking " +
                    "WHERE BookingDate = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (!(rs.getInt(1) < count)) {
                status = false;
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public boolean update(Booking bkParam) throws ApplicationException {
        boolean status = true;

        try{
            String sqlQuery = "UPDATE BOOKING " +
                    "SET lastname = CASE WHEN ? = '' THEN lastname ELSE ? END, " +
                    "numberofguests = CASE WHEN ? = '' THEN numberofguests ELSE ? END, " +
                    "bookingdate = CASE WHEN ? = DATE('1970-01-01') THEN bookingdate ELSE ? END, " +
                    "bookingtime = CASE WHEN ? = TIME('01.00.00') THEN bookingtime ELSE ? END, " +
                    "phonenumber = CASE WHEN ? = '' THEN phonenumber ELSE ? END " +
                    "WHERE ID = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setString(1, bkParam.getLastName() == null ? "" : bkParam.getLastName());
            ps.setString(2, bkParam.getLastName() == null ? "" : bkParam.getLastName());
            ps.setInt(3, bkParam.getNumberOfGuests());
            ps.setInt(4, bkParam.getNumberOfGuests());
            ps.setDate(5, bkParam.getBookingDate() == null ? new Date(0) : bkParam.getBookingDate());
            ps.setDate(6, bkParam.getBookingDate() == null ? new Date(0) : bkParam.getBookingDate());
            ps.setTime(7, bkParam.getBookingTime() == null ? new Time(0) : bkParam.getBookingTime());
            ps.setTime(8, bkParam.getBookingTime() == null ? new Time(0) : bkParam.getBookingTime());
            ps.setString(9, bkParam.getPhoneNumber() == null ? "" : bkParam.getPhoneNumber());
            ps.setString(10, bkParam.getPhoneNumber() == null ? "" : bkParam.getPhoneNumber());
            ps.setInt(11, bkParam.getId());
            ps.execute();
            ps.close();
            ConnectionSetup.conn.commit();

        }catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            throw new ApplicationException(sqlExcept.getMessage());
        }
        return status;
    }

    @Override
    public boolean delete(int id) {
        boolean status = true;

        try{
            String sqlQuery = "DELETE FROM BOOKING WHERE Id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            ps.execute();
            ConnectionSetup.conn.commit();
        }catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public boolean exists(int id) {
        boolean status = true;
        try{
            String sqlQuery = "SELECT COUNT(1) FROM BOOKING WHERE ID = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            ConnectionSetup.conn.commit();

            result.next();
            if(result.getInt(1) == 0){
                status = false;
            }
        }catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            status = false;
        }
        return status;
    }
}
