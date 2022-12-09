package com.example.project_5;


import java.util.ArrayList;
import java.util.List;

/**
 * Meatzza Class that extends Pizza to create a specialized pizza
 *
 * @author Mary Farag
 */
public class Meatzza extends Pizza {
    private double price = 0.0;
    private static final double smallPrice = 15.99;
    private static final double mediumPrice = 17.99;
    private static final double largePrice = 19.99;

    /**
     * Constructor for the Meatzza Pizza that sets the toppings and crust
     *
     * @param crust Crust for the Meatzza pizza based on the type of pizza
     */
    public Meatzza(Crust crust) {
        setCrust(crust);
        List<Topping> toppings = new ArrayList<Topping>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.HAM);
        toppings.add(Topping.BEEF);
        setToppings(toppings);
    }

    /**
     * returns price of the pizza depending on size
     * @return the price of the pizza based on the size the user inputted
     */
    @Override
    public double price() {
        if (Size.SMALL.equals(getSize())) {
            price = smallPrice;
        } else if (Size.MEDIUM.equals(getSize())) {
            price = mediumPrice;
        } else if (Size.LARGE.equals(getSize())) {
            price = largePrice;
        }
        return price;
    }
}
