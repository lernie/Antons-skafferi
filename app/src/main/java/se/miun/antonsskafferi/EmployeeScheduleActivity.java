package se.miun.antonsskafferi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeScheduleActivity extends NavigationActivity {

    private ArrayList<Employee> employeeScheduleList;
    private EmployeeScheduleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_schedule);

        adapter = new EmployeeScheduleAdapter (this, employeeScheduleList);
        ((GridView) findViewById(R.id.schedule_gridview)).setAdapter(adapter);
    }
}
