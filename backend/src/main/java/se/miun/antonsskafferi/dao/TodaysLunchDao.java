package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.TodaysLunch;
import se.miun.antonsskafferi.Models.TodaysLunchString;

import java.sql.Date;

public interface TodaysLunchDao {
    java.util.List<TodaysLunchString> getAll(Date startDate, Date endDate);
    boolean insert(TodaysLunch todaysLunch);
}
