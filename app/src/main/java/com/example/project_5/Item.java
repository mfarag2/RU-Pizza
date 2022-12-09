package com.example.project_5;

import java.io.Serializable;

/**
 * This class defines the structure of an item displayed in the RecyclerView
 * @author Mary Farag
 */
public class Item implements Serializable {
    private String pizzaString;

    /**
     * Parameterized constructor.
     * @param pizzaString the String representation of the Pizza
     */
    public Item(String pizzaString) {
        this.pizzaString = pizzaString;
        //this.image = image;
    }

    /**
     * Getter method that returns the item name of an item.
     * @return the item name of an item.
     */
    public String getPizzaString() {
        return pizzaString;
    }


}
