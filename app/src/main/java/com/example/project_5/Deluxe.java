package com.example.project_5;


import java.util.ArrayList;
import java.util.List;

/**
 * Deluxe Class that extends Pizza
 * @author Mary Farag
 */
public class Deluxe extends Pizza {
    private double price = 0.0;
    private static final double smallPrice = 14.99;
    private static final double mediumPrice = 16.99;
    private static final double largePrice = 18.99;

    /**
     * Constructor for the Deluxe Pizza that sets toppings and crust
     * @param crust Crust based on the type of pizza
     */
    public Deluxe(Crust crust) {
        List<Topping> toppings = new ArrayList<Topping>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREENPEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        setToppings(toppings);
        setCrust(crust);
    }

    /**
     * returns price of the pizza depending on size
     * @return the price of the pizza based on the size the user inputted
     */
    @Override
    public double price() {
        if(Size.SMALL.equals(getSize())) {
            price = smallPrice;
        } else if(Size.MEDIUM.equals(getSize())) {
            price = mediumPrice;
        } else if(Size.LARGE.equals(getSize())) {
            price = largePrice;
        }

        return price;
    }
}
