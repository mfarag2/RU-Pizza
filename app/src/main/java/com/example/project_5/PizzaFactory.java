package com.example.project_5;


/**
 * PizzaFactory interface class describes the methods that need to be implemented by all the pizza classes
 *
 */
public interface PizzaFactory {
    /**
     * Create a deluxe pizza
     * @return deluxe pizza
     */
    Pizza createDeluxe();

    /**
     * Create a meatzza pizza
     * @return meatzza pizza
     */
    Pizza createMeatzza();

    /**
     * Create a BBQ Chicken pizza
     * @return BBQ Chicken pizza
     */
    Pizza createBBQChicken();

    /**
     * Create a build your own pizza
     * @return build your own pizza
     */
    Pizza createBuildYourOwn();
}


