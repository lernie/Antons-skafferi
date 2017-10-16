package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Wictor on 2017-10-16.
 */

public class OrderConfirmPopup extends PopupWindow {

    private PopupWindow popupWindow;

    OrderConfirmPopup(Activity parent) {

        // get a reference to the already created main layout
        RelativeLayout mainLayout = (RelativeLayout) parent.findViewById(R.id.order_layout);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) parent.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView;

        popupView = inflater.inflate(R.layout.order_clear_confirm_popup, null);


        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
    }


    public void remove() {
        popupWindow.dismiss();

    }
}
