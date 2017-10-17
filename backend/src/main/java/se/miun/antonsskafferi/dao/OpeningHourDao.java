package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.OpeningHour;

import java.util.List;

public interface OpeningHourDao {
    boolean update (OpeningHour opParam);
    List<OpeningHour> get();


}
