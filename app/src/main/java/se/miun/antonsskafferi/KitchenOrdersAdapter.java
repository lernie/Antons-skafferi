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

        View view = (ViewGroup) inflater.inflate(R.layout.kitchen_order, parent, false);

        LinearLayout linLayout = view.findViewById(R.id.course_list);

        Order order = getItem(position);

        TextView tableNumberView = (TextView) view.findViewById(R.id.order_title);
        tableNumberView.setText("Bord " + order.getTable());

        for (int i = 0; i < order.getCourseCount(); i++) {

            Order.OrderItem item = order.getItem(i);

            View courseView;

            if (item.isSpecial()) {
                courseView = inflater.inflate(R.layout.kitchen_order_courses_spec, linLayout, false);
                TextView specText = (TextView) courseView.findViewById(R.id.course_spec_text);

                specText.setText(item.getText());
            } else {
                courseView = inflater.inflate(R.layout.kitchen_order_courses, linLayout, false);
            }

            TextView textView = (TextView) courseView.findViewById(R.id.course_name);

            textView.setText(item.getCourse());
            linLayout.addView(courseView);
        }

        return view;
    }
}
