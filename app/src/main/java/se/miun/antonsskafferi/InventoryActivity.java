package se.miun.antonsskafferi;


import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryActivity extends NavigationActivity {

    private ArrayList<Ingredient> inventoryList;
    private InventoryListAdapter adapter;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventoryList = new ArrayList<Ingredient>();
        adapter = new InventoryListAdapter(this, inventoryList);
        ((GridView) findViewById(R.id.inventory_list)).setAdapter(adapter);

        /*for (int i = 0; i < 20; i++) {
            inventoryList.add(new Ingredient("Gino", 3, "st"));
            inventoryList.add(new Ingredient("Känguru", 2.5, "st"));
            inventoryList.add(new Ingredient("Cola", 1, "l"));
        }*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IngredientService ingredientService = retrofit.create(IngredientService.class);
        Call<List<IngredientServiceItem>> ingredientCall = ingredientService.getIngredients();

        ingredientCall.enqueue(new Callback<List<IngredientServiceItem>>() {
            @Override
            public void onResponse(Call<List<IngredientServiceItem>> call, Response<List<IngredientServiceItem>> response) {
                if (response == null || response.body() == null) {
                    inventoryList.clear();
                    adapter.notifyDataSetChanged();
                    return;
                }

                for (IngredientServiceItem item : response.body()) {
                    Ingredient ingredient = new Ingredient(item.getName(), 1, "" + item.getMeasurementId());
                    inventoryList.add(ingredient);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<IngredientServiceItem>> call, Throwable t) {
                inventoryList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        InventoryService inventoryService = retrofit.create(InventoryService.class);

        Call<List<InventoryServiceItem>> call = inventoryService.getInventory();

//        call.enqueue(new Callback<List<InventoryServiceItem>>() {
//            @Override
//            public void onResponse(Call<List<InventoryServiceItem>> call, Response<List<InventoryServiceItem>> response) {
//                if (response == null || response.body() == null) {
//                    inventoryList.clear();
//                    adapter.notifyDataSetChanged();
//                    return;
//                }
//
//                for (InventoryServiceItem item : response.body()) {
//                    Ingredient ingredient = new Ingredient("" + item.getIngredientId(), item.getAmount(), "kg");
//                    inventoryList.add(ingredient);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<InventoryServiceItem>> call, Throwable t) {
//                inventoryList.clear();
//                adapter.notifyDataSetChanged();
//            }
//        });

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


    }
    
    public void removePopupOnClick(View view){
        popupWindow.dismiss();
    }
    public void saveChangeInventory(View v){
        Toast.makeText(InventoryActivity.this,
                "Ändring sparad", Toast.LENGTH_LONG).show();
    }
}
