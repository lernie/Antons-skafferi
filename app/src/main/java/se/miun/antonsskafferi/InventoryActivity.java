package se.miun.antonsskafferi;


import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.View.OnClickListener;

public class InventoryActivity extends Activity {

    private ArrayList<Ingredient> inventoryList;
    private InventoryListAdapter adapter;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        inventoryList = new ArrayList<Ingredient>();
        adapter = new InventoryListAdapter(this, inventoryList);
        ((ListView) findViewById(R.id.inventory_list)).setAdapter(adapter);

        inventoryList.add(new Ingredient("Gino", 3, "st"));
        inventoryList.add(new Ingredient("Känguru", 2.5, "st"));
        inventoryList.add(new Ingredient("Cola", 1, "l"));

        adapter.notifyDataSetChanged();

    }

    public void showIngredientPopupWindow(View view) {

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.inventory_layout);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.inventory_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        /*popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });*/


    }
    public void removePopupOnClick(View view){
        popupWindow.dismiss();
    }
}
