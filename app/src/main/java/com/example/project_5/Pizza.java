package com.example.project_5;


import java.util.List;

/**
 * Pizza is an abstract class that implements the Customizable
 * interface along with the abstract price method and getters/setters for instance variables
 * @author Mary Farag
 */
public abstract class Pizza implements Customizable {
    private List<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * setter method for the toppings
     *
     * @param toppings toppings list
     */
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * getter method to get the list of toppings
     *
     * @return toppings list
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    /**
     * setter method for the type of crust
     *
     * @param crust crust type
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * getter method to get the type of the crust
     *
     * @return crust type
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * setter method for the size
     *
     * @param size size enum
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * getter method to get the size of the pizza
     *
     * @return size enum
     */
    public Size getSize() {
        return size;
    }

    /**
     * Abstract price method implemented by each subclass
     *
     * @return pizza price
     */
    public abstract double price();

    /**
     * Add topping to the list of topping
     *
     * @param obj object to be added
     * @return true if toppings added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        return true;
    }

    /**
     * Remove topping from the list of topping
     *
     * @param obj object to be removed
     * @return true if toppings removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        return true;
    }

}
