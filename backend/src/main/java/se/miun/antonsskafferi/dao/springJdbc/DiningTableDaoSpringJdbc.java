package se.miun.antonsskafferi.dao.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import se.miun.antonsskafferi.Models.DiningTable;
import se.miun.antonsskafferi.Models.Ingredient;
import se.miun.antonsskafferi.dao.DiningTableDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiningTableDaoSpringJdbc implements DiningTableDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<DiningTable> getAll() {
        List<DiningTable> diningTableList = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT ID, NAME FROM DiningTable");

        for (Map row : rows) {
            DiningTable diningTable = new DiningTable();
            diningTable.setId((Integer)(row.get("ID")));
            diningTable.setName((String)(row.get("NAME")));

            diningTableList.add(diningTable);
        }
        return diningTableList;
    }
}
