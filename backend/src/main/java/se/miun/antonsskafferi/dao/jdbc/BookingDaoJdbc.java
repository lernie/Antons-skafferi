package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Booking;
import se.miun.antonsskafferi.dao.BookingDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoJdbc implements BookingDao {
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
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Booking booking) {
        return false;
    }
}
