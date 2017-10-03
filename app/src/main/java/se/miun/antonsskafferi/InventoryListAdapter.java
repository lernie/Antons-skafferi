package se.miun.antonsskafferi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kalle on 2017-10-02.
 */

public class InventoryListAdapter extends ArrayAdapter<Ingredient>{

    private final Context context;
    private final ArrayList<Ingredient> inventoryList;

    public InventoryListAdapter(Context context, ArrayList<Ingredient> inventoryList) {
        super(context, R.layout.inventory_list_item, inventoryList);
        this.context = context;
        this.inventoryList = inventoryList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Ingredient ingredient = inventoryList.get(position);

        View rowView;
        rowView = inflater.inflate(R.layout.inventory_list_item, parent, false);
        TextView ingredientName = (TextView) rowView.findViewById(R.id.ingredient_name);
        TextView ingredientQuantity = (TextView) rowView.findViewById(R.id.ingredient_quantity);
        TextView ingredientUnit = (TextView) rowView.findViewById(R.id.ingredient_unit);

        ingredientName.setText(ingredient.getName());
        ingredientQuantity.setText(Integer.toString(ingredient.getQuantity()));
        ingredientUnit.setText(ingredient.getUnit());

        return rowView;
    }
}
