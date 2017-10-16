package se.miun.antonsskafferi.dao.springJdbc;

import se.miun.antonsskafferi.Models.TodaysLunch;
import se.miun.antonsskafferi.Models.TodaysLunchString;
import se.miun.antonsskafferi.dao.TodaysLunchDao;

import java.sql.Date;
import java.util.List;

public class TodaysLunchDaoSpringJdbc implements TodaysLunchDao{
    @Override
    public List<TodaysLunchString> getAll(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public boolean insert(TodaysLunch todaysLunch) {
        return false;
    }
}
