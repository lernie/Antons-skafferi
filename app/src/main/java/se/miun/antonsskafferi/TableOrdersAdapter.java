/**
 * Created by Wictor on 2017-09-28.
 */
package se.miun.antonsskafferi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;



public class TableOrdersAdapter extends ArrayAdapter<Order.OrderItem>{

    private final Context context;
    private final ArrayList<Order.OrderItem> orderItems;

    public TableOrdersAdapter(Context context, ArrayList<Order.OrderItem> orderItems) {
        super(context, R.layout.table_order_spec, orderItems);
        this.context = context;
        this.orderItems = orderItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Order.OrderItem item = orderItems.get(position);

        View rowView;
        if(item.isSpecial()){
            rowView = inflater.inflate(R.layout.table_order_spec, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.table_order, parent, false);
            TextView quantity = (TextView) rowView.findViewById(R.id.quantity);
            quantity.setText(Integer.toString(item.getCount()) + "x");
        }

        TextView courseName =  (TextView) rowView.findViewById(R.id.order);

        courseName.setText(item.getCourse().getName());

        return rowView;
    }








}

