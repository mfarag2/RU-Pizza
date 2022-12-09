package com.example.project_5;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class controls the data used in the NY Style activity and the UI functions of the activity
 * @author Mary Farag
 */

public class NYStyleActivity extends AppCompatActivity {

    private StoreOrder currentStoreOrder;
    private Order currentOrder;
    private ImageView picture;
    private RadioGroup sizes;
    private RadioButton small;
    private Button remove;
    private Button add;
    private Button addToOrder;
    private Spinner spinner;
    private ListView toppingsAvailable;
    private ListView toppingsSelected;
    private TextView price;
    private TextView crust;
    private String [] names = {"Build Your Own", "Deluxe", "Meatzza", "BBQ Chicken"};
    private Topping[] toppings = { Topping.SAUSAGE, Topping.BBQCHICKEN, Topping.PROVOLONE,
            Topping.CHEDDAR, Topping.BEEF, Topping.HAM, Topping.PEPPERONI, Topping.GREENPEPPER,
            Topping.ONION, Topping.MUSHROOM, Topping.PINEAPPLE, Topping.BLACKOLIVES, Topping.SPINACH};

    private List<String> pizzaTypes = Arrays.asList(names);
    private List<Topping> availableToppings  = new ArrayList<>(Arrays.asList(toppings));
    private List<Topping> selectedToppings = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<Topping> toppingAvailableAdapter;
    private ArrayAdapter<Topping> toppingSelectedAdapter;

    private PizzaFactory pizzaFactory = new NYStylePizzaFactory();
    private DecimalFormat df = new DecimalFormat("#.##");

    private static Pizza currPizza;
    private static Topping selected;
    private static Topping selectedToRemove;
    private static Size currSize = Size.SMALL;

    /**
     * Executes when the Activity is created.
     *
     * @param savedInstanceState previously held data about this Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_style);
        initializeComponents();
        initializeValues();
        sizeChangeListener();
        typeChangeListener();
        addTopping();
        removeTopping();
        addToOrder();
    }

    /**
     * Initializes the various instance variables to their respective values.
     */
    private void initializeValues(){
        this.currentOrder = MainActivity.currentOrder;
        this.currentStoreOrder = MainActivity.storeOrder;

        price = (TextView) (findViewById(R.id.price2NY));
        crust = (TextView) (findViewById(R.id.crust2NY));
        small = (RadioButton) (findViewById(R.id.radioButton4NY));
        picture = (ImageView) findViewById(R.id.imageView2NY);
        small.setChecked(true);
        currPizza = pizzaFactory.createBuildYourOwn();
        currPizza.setSize(Size.SMALL);

        price.setText(""+df.format(currPizza.price()));
        crust.setText(currPizza.getCrust().toString());
    }

    /**
     * Initializes the Spinner
     *
     */
    private void initializeComponents(){
        spinner = (Spinner) findViewById(R.id.spinner3NY);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pizzaTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        toppingsAvailable = (ListView) findViewById(R.id.listView2NY);
        toppingsAvailable.setSelector(new ColorDrawable(Color.GRAY));
        toppingsAvailable.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        availableToppings  = new ArrayList<>(Arrays.asList(toppings));
        toppingAvailableAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  availableToppings);
        toppingsAvailable.setAdapter(toppingAvailableAdapter);

        toppingsSelected = (ListView) findViewById(R.id.listViewNY);
        toppingsSelected.setSelector(new ColorDrawable(Color.GRAY));
        toppingsSelected.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        selectedToppings = new ArrayList<>();
        toppingSelectedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  selectedToppings);
        toppingsSelected.setAdapter(toppingSelectedAdapter);
    }

    /**
     * This method is used to add a topping from the toppingsAvailable list view to the toppingsSelected list view,
     * only allowing a maximum of 7 toppings to be added
     */
    private void addTopping() {
        add = (Button) findViewById(R.id.button13NY);
        toppingsAvailable.setOnItemClickListener((adapter, v, position, id) -> selected = (Topping) adapter.getItemAtPosition(position));
        add.setOnClickListener(v -> {
            if (selected == null) {
                Toast toast = Toast.makeText(NYStyleActivity.this,
                        getResources().getString(R.string.noToppingsSelectedToAdd), Toast.LENGTH_LONG);
                toast.show();
            } else if (selectedToppings.size() == 7) {
                Toast toast = Toast.makeText(NYStyleActivity.this,
                        getResources().getString(R.string.maxToppingsReached),  Toast.LENGTH_LONG);
                toast.show();
            } else {
                availableToppings.remove(selected);
                toppingAvailableAdapter.notifyDataSetChanged();
                selectedToppings.add(selected);
                toppingSelectedAdapter.notifyDataSetChanged();
                currPizza.add(selected);
                price.setText(""+df.format(currPizza.price()));
                selected = null;
            }
        });
    }

    /**
     * This method is used to remove a topping from the toppingsSelected list view and add it back to the toppingsAvailable list view
     */
    private void removeTopping() {
        remove = (Button) findViewById(R.id.button12NY);
        toppingsSelected.setOnItemClickListener((adapter, v, position, id) -> selectedToRemove = (Topping) adapter.getItemAtPosition(position));
        remove.setOnClickListener(v -> {
            if (selectedToRemove == null) {
                Toast toast = Toast.makeText(NYStyleActivity.this,
                        getResources().getString(R.string.noToppingsSelectedToRemove), Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                selectedToppings.remove(selectedToRemove);
                toppingSelectedAdapter.notifyDataSetChanged();
                availableToppings.add(selectedToRemove);
                toppingAvailableAdapter.notifyDataSetChanged();
                currPizza.remove(selectedToRemove);
                price.setText(""+df.format(currPizza.price()));
                selectedToRemove= null;
            }
        });
    }

    /**
     * Establishes a listener for when the pizza size is selected. The price displayed is changed
     * accordingly
     */
    private void sizeChangeListener(){
        sizes = (RadioGroup) findViewById(R.id.radioGroupNY);
        sizes.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                currSize = Size.valueOf(checkedRadioButton.getText().toString().toUpperCase());
                currPizza.setSize(currSize);
                price.setText(""+df.format(currPizza.price()));
            }
        });
    }

    /**
     * Establishes a listener for when the pizza type is selected. The image displayed, the list
     * views, the add and remove toppings buttons, price, and the crust are changed accordingly
     */
    private void typeChangeListener(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedVal = parentView.getSelectedItem().toString();
                add.setEnabled(false);
                remove.setEnabled(false);
                if (selectedVal.equals("Deluxe")){
                    picture.setImageResource(R.drawable.nydeluxe);
                    currPizza = pizzaFactory.createDeluxe();
                }
                else if (selectedVal.equals("Meatzza")){
                    picture.setImageResource(R.drawable.nymeatzza);
                    currPizza = pizzaFactory.createMeatzza();
                }
                else if (selectedVal.equals("BBQ Chicken")){
                    picture.setImageResource(R.drawable.nybbq);
                    currPizza = pizzaFactory.createBBQChicken();
                }
                else {
                    add.setEnabled(true);
                    remove.setEnabled(true);
                    picture.setImageResource(R.drawable.nybyo);
                    currPizza = pizzaFactory.createBuildYourOwn();
                }
                selectedToppings.clear();
                if (!(selectedVal.equals("Build Your Own"))){selectedToppings.addAll(currPizza.getToppings()); }
                toppingSelectedAdapter.notifyDataSetChanged();
                currPizza.setSize(currSize);
                price.setText(""+df.format(currPizza.price()));
                crust.setText(currPizza.getCrust().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    /**
     * This method adds the current pizza to the current order
     */
    private void addToOrder(){
        addToOrder = (Button) findViewById(R.id.button14NY);
        addToOrder.setOnClickListener(v -> {
            currentOrder.add(currPizza);
            currentOrder.addToStringArray(currPizza, "NY Style");
            Toast toast = Toast.makeText(NYStyleActivity.this,
                    getResources().getString(R.string.pizzaAdded), Toast.LENGTH_LONG);
            toast.show();
            initializeValues();
            initializeComponents();
        });
    }
}