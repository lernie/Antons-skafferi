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



public class TableOrdersAdapter extends ArrayAdapter<String>{

    private final Context context;
    //private final ArrayList<String> stringArrayList;
    public TableOrdersAdapter(Context context) {
        super(context, R.layout.table_order_spec);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView;
        if(position==4){
            rowView = inflater.inflate(R.layout.table_order_spec, parent, false);
        }
        else {
            rowView = inflater.inflate(R.layout.table_order, parent, false);
        }
        TextView textView = (TextView) rowView.findViewById(R.id.order);
        int mod=position%3;
        if(mod==0){
            textView.setText("Stekta grodlår");
        }
        else if(mod==1){
            textView.setText("Friterad känguru");
        }
        else
            textView.setText("Gino");



        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.order);


        // 5. retrn rowView
        return rowView;
    }





}

