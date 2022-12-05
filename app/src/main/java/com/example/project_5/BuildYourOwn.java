package com.example.project_5;

import java.util.ArrayList;
import java.util.List;

/**
 * Build Your Own Pizza Class that extends Pizza
 *
 *
 */
public class BuildYourOwn extends Pizza implements Customizable {
    private double price = 0.0;
    private static final double smallPrice = 8.99;
    private static final double mediumPrice = 10.99;
    private static final double largePrice = 12.99;
    private static final double toppingMultiple = 1.59;

    /**
     * Sets the crust of the build your own pizza based on the defined style crust
     *
     * @param crust Crust for the Build Your Own pizza based on the style of the pizza
     */
    public BuildYourOwn(Crust crust) {
        setCrust(crust);
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
        if (getToppings() != null) {
            price += toppingMultiple * getToppings().size();
        }
        return price;
    }

    /**
     * @param obj the obj that needs to be added to the arraylist
     * @return true if the obj was added to the arraylist
     */
    @Override
    public boolean add(Object obj) {
        List<Topping> toppings = getToppings();
        if (toppings == null) {
            toppings = new ArrayList<Topping>();
        }
        toppings.add((Topping) obj);
        setToppings(toppings);
        return true;
    }

    /**
     * @param obj the obj that needs to be removed to the arraylist
     * @return true if the obj was removed to the arraylist
     */
    @Override
    public boolean remove(Object obj) {
        List<Topping> toppings = getToppings();
        if (toppings == null) {
            return true;
        }
        toppings.remove((Topping) obj);
        setToppings(toppings);
        return true;
    }

}
