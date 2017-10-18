package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.TodaysLunch;
import se.miun.antonsskafferi.Models.TodaysLunchString;

import java.sql.Date;
import java.util.List;

public interface TodaysLunchDao {
    List<TodaysLunchString> getAll(Date startDate, Date endDate);
    boolean insert(TodaysLunch todaysLunch);
    boolean delete(Date date, int id);
}
