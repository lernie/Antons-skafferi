package se.miun.antonsskafferi;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Kalle on 2017-10-12.
 */

public class IngredientPopupWindow extends PopupWindow{

    private PopupWindow popupWindow;
    private int id;

    IngredientPopupWindow(Activity parent){

        // get a reference to the already created main layout
        final LinearLayout mainLayout = (LinearLayout) parent.findViewById(R.id.inventory_layout);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) parent.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView;

        if(parent.findViewById(R.id.add_ingredient).isPressed()) {
            popupView = inflater.inflate(R.layout.inventory_popup, null);
        } else{
            popupView = inflater.inflate(R.layout.inventory_popup_remove, null);
        }

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        final ArrayList<String> unitsList = new ArrayList<String>();

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parent, android.R.layout.simple_spinner_item, unitsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) popupView.findViewById(R.id.popup_spinner_unit);
        spinner.setAdapter(dataAdapter);

        UnitCache.getInstance().update(new UnitCache.UpdateCallback() {
          @Override
                  public void onSuccess() {
              HashMap<Integer, String> values = UnitCache.getInstance().getUnits();

                 for (String unit : UnitCache.getInstance().getUnits().values()) {
                      unitsList.add(unit);
                  }

                dataAdapter.notifyDataSetChanged();
              popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

          }

            public void onFail() {
                unitsList.clear();
                dataAdapter.notifyDataSetChanged();
            }
        });
    }

    public void remove() {
        popupWindow.dismiss();
    }
}
