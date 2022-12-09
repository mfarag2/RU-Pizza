package com.example.project_5;

/**
 * Chicago Pizza Factory creates different pizzas based on the desired style
 * @author Mary Farag
 */
public class ChicagoStylePizzaFactory implements PizzaFactory {
    /**
     * Create a deluxe style pizza  and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEPDISH);
    }

    /**
     * Create a Meatzza style pizza and and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Create a BBQChicken style pizza and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Create a Build Your Own style pizza  and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}

