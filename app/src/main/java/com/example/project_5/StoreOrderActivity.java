package com.example.project_5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreOrderActivity extends AppCompatActivity {
    private StoreOrder currentStoreOrder;
    private ArrayList<Order> cancelingThisOrder;
    private AlertDialog.Builder emptyStoreCancelDialogBuilder;
    private AlertDialog.Builder noSelectionDialogBuilder;
    private ArrayList<Item> itemList = new ArrayList<>();
    private RecyclingAdapter adapter;
    private RecyclerView recyclerView;
    /**
     * Manages the view and display if the user presses the back button on the
     * Android Application.
     */
    @Override
    public void onBackPressed() {
        MainActivity.storeOrder = this.currentStoreOrder;
        finish();
    }

    /**
     * Executes when the Activity is created.
     *
     * @param savedInstanceState previously held data about this Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        initializeValues();
        setUpCancelDialogs();
        initializeRecyclerView();
    }

    /**
     * Manages the view and display if the user presses the back button on
     * the Store Activity.
     *
     * @param itemChosen The android.view.MenuItem that was selected
     * @return a true value if the button that the user pressed is the back
     * button. Else, return false.
     */
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem itemChosen) {
        if (itemChosen.getItemId() == android.R.id.home) {
            MainActivity.storeOrder = this.currentStoreOrder;
            finish();
            return true;
        }
        return super.onOptionsItemSelected(itemChosen);
    }

    /**
     * Initializes the RecyclerView
     */
    private void initializeRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclingAdapter( this.currentStoreOrder.getOrders() ); //create the adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Initializes the various instance variables to their respective values.
     */
    private void initializeValues() {
        cancelingThisOrder = new ArrayList<>();
        this.currentStoreOrder = MainActivity.storeOrder;

        Button cancelOrders = (Button) (findViewById(R.id.cancelOrders));
        cancelOrders.setOnClickListener(cancelListener());

        Button cancelAllOrders = (Button) (findViewById(R.id.cancelAllOrders));
        cancelAllOrders.setOnClickListener(cancelListenerAll());
    }

    /**
     * Display respective errors if an order is cancelled in correctly.
     */
    public void setUpCancelDialogs() {
        emptyStoreCancelDialogBuilder = new AlertDialog.Builder(this);
        emptyStoreCancelDialogBuilder.setTitle(R.string.cancelError);
        emptyStoreCancelDialogBuilder.setMessage(R.string.cancelEmptyStore);
        emptyStoreCancelDialogBuilder.setIcon(R.drawable.error);
        emptyStoreCancelDialogBuilder.setPositiveButton(R.string.OKButton, null);

        noSelectionDialogBuilder = new AlertDialog.Builder(this);
        noSelectionDialogBuilder.setTitle(R.string.cancelError);
        noSelectionDialogBuilder.setMessage(R.string.cancelNoSelection);
        noSelectionDialogBuilder.setIcon(R.drawable.error);
        noSelectionDialogBuilder.setPositiveButton(R.string.OKButton, null);
    }


    /**
     * Establishes a listener for when the Cancel Order button is clicked. First
     * checks if the total orders and the selected orders are not empty, and then
     * removed the orders that need to be removed.
     *
     * @return a View.OnClickListener for the cancel button.
     */
    private View.OnClickListener cancelListener() {
        return view -> {
            if (currentStoreOrder.getOrders().isEmpty()) {
                AlertDialog alert = emptyStoreCancelDialogBuilder.create();
                alert.show();
                return;
            }
            if (adapter.getSelectedElem()==null) {
                AlertDialog alert = noSelectionDialogBuilder.create();
                alert.show();
                return;
            }

            currentStoreOrder.getOrders().remove(adapter.getSelectedElem());
            RecyclingAdapter adapter = new RecyclingAdapter( currentStoreOrder.getOrders());
            recyclerView.setAdapter(adapter);
            adapter.resetSelectedPos();
            Toast removeSuccess = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.cancelOrderMessage), Toast.LENGTH_SHORT);
            removeSuccess.show();
            cancelingThisOrder.clear();
        };
    }

    private View.OnClickListener cancelListenerAll() {
        return view -> {
            if (currentStoreOrder.getOrders().isEmpty()) {
                AlertDialog alert = emptyStoreCancelDialogBuilder.create();
                alert.show();
                return;
            }
            currentStoreOrder = new StoreOrder();

            Toast removeAllOrders = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.cancelAllOrderMessage), Toast.LENGTH_SHORT);
            removeAllOrders.show();
            recyclerView.setAdapter(null);
        };
    }
}
