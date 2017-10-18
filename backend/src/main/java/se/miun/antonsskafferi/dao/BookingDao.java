package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Database.ApplicationException;
import se.miun.antonsskafferi.Models.Booking;

import java.awt.print.Book;
import java.sql.Date;
import java.util.List;

public interface BookingDao{
    List<Booking> get();
    boolean insert(Booking bkParam);
    boolean checkIfLess(int count, Date date);
    boolean update(Booking bkParam) throws ApplicationException;
    boolean delete(int id);
    boolean exists(int id) throws ApplicationException;
}
