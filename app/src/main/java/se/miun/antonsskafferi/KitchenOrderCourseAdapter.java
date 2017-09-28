package se.miun.antonsskafferi;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by joel on 2017-09-28.
 */

public class KitchenOrderCourseAdapter extends ArrayAdapter {
    public KitchenOrderCourseAdapter(@NonNull Context context) {
        super(context, R.layout.kitchen_order_courses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.kitchen_order_courses, parent, false);

        return view;
    }
}
