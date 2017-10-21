package se.miun.antonsskafferi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by joel on 2017-09-28.
 */

public class KitchenOrdersAdapter extends ArrayAdapter<Order> {

    public KitchenOrdersAdapter(@NonNull Context context, ArrayList<Order> list) {
        super(context, R.layout.kitchen_order, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View orderView = (ViewGroup) inflater.inflate(R.layout.kitchen_order, parent, false);

        LinearLayout linLayout = orderView.findViewById(R.id.course_list);

        Order order = getItem(position);

        TextView timeSinceOrdered = (TextView) orderView.findViewById(R.id.came_in_minutes_ago);

        long seconds = ((Calendar.getInstance().get(Calendar.ZONE_OFFSET) +
                            Calendar.getInstance().get(Calendar.DST_OFFSET) +
                            Calendar.getInstance().getTime().getTime() -
                            order.getCreated()))/1000; // milliseconds to seconds conversion

        if (seconds >= 60) {
            timeSinceOrdered.setText(Long.toString(seconds/60) + " min");
        } else {
            timeSinceOrdered.setText(Long.toString(seconds) + " sek");
        }

        TextView tableNumberView = (TextView) orderView.findViewById(R.id.order_title);
        tableNumberView.setText("Bord " + order.getTable());

        for (int i = 0; i < order.getCourseCount(); i++) {

            Order.OrderItem item = order.getItem(i);

            View courseView;

            if (item.isSpecial()) {
                courseView = inflater.inflate(R.layout.kitchen_order_courses_spec, linLayout, false);
                TextView specText = (TextView) courseView.findViewById(R.id.course_spec_text);
                specText.setText(item.getText());
                TextView cookingTime = (TextView) courseView.findViewById(R.id.course_cooking_time);
                cookingTime.setText(Integer.toString(item.getCourse().getCookingTime())+ " min");
            } else {
                courseView = inflater.inflate(R.layout.kitchen_order_courses, linLayout, false);
                TextView courseCount = (TextView) courseView.findViewById(R.id.course_count);
                courseCount.setText("x" + item.getCount());
                TextView cookingTime = (TextView) courseView.findViewById(R.id.course_cooking_time);
                cookingTime.setText(Integer.toString(item.getCourse().getCookingTime())+ " min");
            }

            TextView textView = (TextView) courseView.findViewById(R.id.course_name);

            textView.setText(item.getCourse().getName());
            linLayout.addView(courseView);
        }

        return orderView;
    }
}
