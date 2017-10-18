package se.miun.antonsskafferi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kalle on 2017-10-18.
 */

public class EmployeeScheduleAdapter extends ArrayAdapter<Employee> {

    private final Context context;
    private final ArrayList<Employee> employeeList;

    public EmployeeScheduleAdapter(Context context, ArrayList<Employee> employeeList) {
        super(context, R.layout.employee_schedule_list_item, employeeList);
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView;
        rowView = inflater.inflate(R.layout.inventory_list_item, parent, false);
        TextView shiftTime = (TextView) rowView.findViewById(R.id.shift_time);
        TextView employeeNameId = (TextView) rowView.findViewById(R.id.employee_name_id);

        shiftTime.setText("Hej");
        employeeNameId.setText("testTt");


        return rowView;
    }
}
