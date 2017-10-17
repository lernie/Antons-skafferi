package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Booking;

import java.sql.Date;
import java.util.List;

public interface BookingDao{
    List<Booking> get();
    boolean add(Booking bkParam);
    boolean checkIfLess(int count, Date date);
}
