package se.miun.antonsskafferi;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by joel on 2017-09-28.
 */

public class KitchenOrdersAdapter extends ArrayAdapter<String> {

    public KitchenOrdersAdapter(@NonNull Context context) {
        super(context, R.layout.kitchen_order);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = (ViewGroup) inflater.inflate(R.layout.kitchen_order, parent, false);

        LinearLayout linLayout = view.findViewById(R.id.course_list);
        linLayout.addView(inflater.inflate(R.layout.kitchen_order_courses, linLayout, false));
        linLayout.addView(inflater.inflate(R.layout.kitchen_order_courses, linLayout, false));

        return view;
    }
}
