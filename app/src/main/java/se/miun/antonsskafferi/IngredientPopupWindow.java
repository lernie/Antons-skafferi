package se.miun.antonsskafferi;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Kalle on 2017-10-12.
 */

public class IngredientPopupWindow extends PopupWindow{

    private PopupWindow popupWindow;
    private int id;

    IngredientPopupWindow(Activity parent, int id){

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) parent.findViewById(R.id.inventory_layout);

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

        List<UnitService.Unit> units = new ArrayList<UnitService.Unit>();
   /*     units.add("st");
        units.add("l");
        units.add("dl");
        units.add("cl");
        units.add("ml");
        units.add("kg");
        units.add("hg");
        units.add("g");*/


        ArrayAdapter<UnitService.Unit> dataAdapter = new ArrayAdapter<UnitService.Unit>(parent, android.R.layout.simple_spinner_item, id);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) popupView.findViewById(R.id.popup_spinner_unit);
        spinner.setAdapter(dataAdapter);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void remove() {
        popupWindow.dismiss();
    }
}
