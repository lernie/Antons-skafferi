package se.miun.antonsskafferi;


import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryActivity extends NavigationActivity {

    private ArrayList<Ingredient> inventoryList;
    private InventoryListAdapter adapter;
    private IngredientPopupWindow ingredientPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        UnitCache.getInstance().update(null);

        inventoryList = new ArrayList<Ingredient>();
        adapter = new InventoryListAdapter(this, inventoryList);
        ((GridView) findViewById(R.id.inventory_list)).setAdapter(adapter);

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
                    Ingredient ingredient = new Ingredient(item.getName(), 1, UnitCache.getInstance().getUnits().get(item.getMeasurementId()));
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
    }

    public void showIngredientPopupWindow(View view) {
        ingredientPopupWindow = new IngredientPopupWindow(this);
    }
    
    public void removePopupOnClick(View view){
        ingredientPopupWindow.remove();
    }
    public void saveChangeInventory(View v){
        Toast.makeText(InventoryActivity.this,
                "Ändring sparad", Toast.LENGTH_LONG).show();
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
/*  keep this one for later:

    public void onBackPressed() {
        if(ingredientPopupWindow != null){
            if(ingredientPopupWindow.isShowing()){
                ingredientPopupWindow.dismiss();
            }
        } else {
            finish();
        }
    } */
}
