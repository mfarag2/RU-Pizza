package com.example.project_5;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class controls the data used in the Current Order activity and the UI functions of the activity
 * @author Mary Farag
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private Order currentOrder;
    private StoreOrder currentStoreOrder;
    private ListView basketDisplayList;
    private EditText subtotal;
    private EditText tax;
    private EditText total;
    private TextView ordernum;
    private AlertDialog.Builder emptyOrderRemoveDialogBuilder;
    private AlertDialog.Builder noSelectionDialogBuilder;
    private AlertDialog.Builder emptyOrderDialogBuilder;
    private ArrayList<String> toBeRemoved;


    /**
     * Executes when the Activity is created.
     * @param savedInstanceState previously held data about this Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        initializeValues();
        setUpRemoveDialogs();
        setUpSubmitDialogs();
        updateValues();
    }

    /**
     * This method updates the values of the text fields according to the current pizzas
     * (or lack thereof) in the current order
     */
    private void updateValues(){
        if(currentOrder.getItems().isEmpty()){
            subtotal.setText(getResources().getString(R.string.dollarPlaceholder));
            tax.setText(getResources().getString(R.string.dollarPlaceholder));
            total.setText(getResources().getString(R.string.dollarPlaceholder));
        }
        subtotal.setText(formatDouble(currentOrder.getCostBeforeTax()));
        tax.setText(formatDouble(currentOrder.getSalesTax()));
        total.setText(formatDouble(currentOrder.getCostAfterTax()));
    }

    /**
     * Updates the subtotal, tax, and total displays based on the
     * items that are in the user's current order.
     */
    private CharSequence formatDouble(double input){
        if(input < 0){
            input = 0;
        }
        DecimalFormat twoDecimalPlaces = new DecimalFormat(getResources().getString(R.string.dollarPlaceholder));
        return twoDecimalPlaces.format(input);
    }

    /**
     * Handles the control logic for when the user presses the back
     * button on the Android Menu.
     */
    @Override
    public void onBackPressed(){
        MainActivity.currentOrder = this.currentOrder;
        MainActivity.storeOrder = this.currentStoreOrder;
        finish();
    }

    /**
     * Creates an adapter for the list view of menu items.
     * @return ArrayAdapter for list of MenuItem objects.
     */
    private ArrayAdapter<String> listViewAdapter(){
        return new ArrayAdapter<String>(CurrentOrderActivity.this,
                android.R.layout.simple_list_item_single_choice, currentOrder.getPizzaToString()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                view.setLayoutParams(layoutParams);
                return view;
            }
        };
    }

    /**
     * Initializes instance variables, sets display of items in the
     * current order.
     */
    private void initializeValues(){
        subtotal = (EditText)(findViewById(R.id.subtotalAmount));
        tax = (EditText)(findViewById(R.id.taxAmount));
        total = (EditText)(findViewById(R.id.totalAmount));

        toBeRemoved = new ArrayList<>();
        this.currentOrder = MainActivity.currentOrder;
        this.currentStoreOrder = MainActivity.storeOrder;

        Button removeFromBasket = (Button) (findViewById(R.id.removeFromBasket));
        removeFromBasket.setOnClickListener(removeListener());
        Button submitOrder = (Button) (findViewById(R.id.submitOrder));
        submitOrder.setOnClickListener(submitListener());
        Button clearFromBasket= (Button) (findViewById(R.id.clearButton));
        clearFromBasket.setOnClickListener(clearListener());

        basketDisplayList = (ListView)(findViewById(R.id.basketDisplayList));
        basketDisplayList.setAdapter(listViewAdapter());
        basketDisplayList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        basketDisplayList.setOnItemClickListener(selectItemsListener());

        ordernum = (TextView)(findViewById(R.id.textView12));
        ordernum.setText(Integer.toString(currentOrder.getOrderId()));


        updateValues();
    }

    /**
     * Initializes dialog builders to display errors with removing
     * items from the basket.
     */
    public void setUpRemoveDialogs(){
        emptyOrderRemoveDialogBuilder = new AlertDialog.Builder(this);
        emptyOrderRemoveDialogBuilder.setTitle(R.string.RemoveError);
        emptyOrderRemoveDialogBuilder.setMessage(R.string.removeEmptyBasket);
        emptyOrderRemoveDialogBuilder.setIcon(R.drawable.error);
        emptyOrderRemoveDialogBuilder.setPositiveButton(R.string.OKButton, null);

        noSelectionDialogBuilder = new AlertDialog.Builder(this);
        noSelectionDialogBuilder.setTitle(R.string.RemoveError);
        noSelectionDialogBuilder.setMessage(R.string.removeNoSelection);
        noSelectionDialogBuilder.setIcon(R.drawable.error);
        noSelectionDialogBuilder.setPositiveButton(R.string.OKButton, null);
    }

    /**
     * Initializes dialog builders to display errors with submitting
     * an order.
     */
    public void setUpSubmitDialogs(){
        emptyOrderDialogBuilder = new AlertDialog.Builder(this);
        emptyOrderDialogBuilder.setTitle(R.string.submitError);
        emptyOrderDialogBuilder.setMessage(R.string.submitEmptyBasket);
        emptyOrderDialogBuilder.setIcon(R.drawable.error);
        emptyOrderDialogBuilder.setPositiveButton(R.string.OKButton, null);
    }

    /**
     * Creates a listener for the list of menu items which adds and
     * removes items from the view based on selected
     * @return an AdapterView.OnItemClickListener
     */
    private AdapterView.OnItemClickListener selectItemsListener(){
        return (adapterView, view, i, l) -> {
            String itemChosen = (String) adapterView.getItemAtPosition(i);
            if(basketDisplayList.isItemChecked(i)){
                toBeRemoved.add(itemChosen);
            }
            else{
                toBeRemoved.remove(itemChosen);
            }
        };
    }

    /**
     * Creates a listener for the remove pizza button.
     * @return a View.OnClickListener
     */
    private View.OnClickListener removeListener(){
        return view -> {
            if (currentOrder.getItems().isEmpty()) {
                AlertDialog alert = emptyOrderRemoveDialogBuilder.create();
                alert.show();
                return;
            }
            if(toBeRemoved.isEmpty()){
                AlertDialog alert = noSelectionDialogBuilder.create();
                alert.show();
                return;
            }
            for (int i=0; i<toBeRemoved.size(); i++){
                int index= currentOrder.getPizzaToString().indexOf(toBeRemoved.get(i));
                Pizza toRemove= currentOrder.getItems().get(index);
                currentOrder.remove(toRemove);
                currentOrder.getPizzaToString().remove(index);
            }
            basketDisplayList.setAdapter(listViewAdapter());
            Toast successToast = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.removeItemMessage), Toast.LENGTH_SHORT);
            successToast.show();
            toBeRemoved.clear();
            updateValues();
        };
    }

    /**
     * Creates a listener for the Submit Order button.
     * @return a View.OnClickListener
     */
    private View.OnClickListener submitListener(){
        return view -> {
            if(currentOrder.getItems().isEmpty()){
                AlertDialog alert = emptyOrderDialogBuilder.create();
                alert.show();
                return;
            }
            currentStoreOrder.add(currentOrder);
            basketDisplayList.setAdapter(null);
            MainActivity.counter++;
            ordernum.setText(Integer.toString(MainActivity.counter));
            currentOrder = new Order();
            MainActivity.currentOrder=currentOrder;
            MainActivity.storeOrder=currentStoreOrder;
            currentOrder.setOrderID(MainActivity.counter);
            Toast toast = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.submitOrderMessage), Toast.LENGTH_SHORT);
            toast.show();
            updateValues();
        };
    }

    /**
     * Creates a listener for clear order button.
     * @return a View.OnClickListener
     */
    private View.OnClickListener clearListener(){
        return view -> {
            if(currentOrder.getItems().isEmpty()){
                AlertDialog alert = emptyOrderRemoveDialogBuilder.create();
                alert.show();
                return;
            }
            currentOrder.getPizzaToString().clear();
            currentOrder.getItems().clear();
            currentOrder.setCostBeforeTax(0);
            MainActivity.storeOrder=currentStoreOrder;
            MainActivity.currentOrder=currentOrder;
            MainActivity.counter--;
            basketDisplayList.setAdapter(null);
            ordernum.setText(Integer.toString(currentOrder.getOrderId()));
            Toast toast = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.clearItems), Toast.LENGTH_SHORT);
            toast.show();
            updateValues();
        };
    }
}