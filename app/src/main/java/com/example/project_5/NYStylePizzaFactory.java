package com.example.project_5;

/**
 * NY Pizza Factory creates different pizzas based on the desired style
 * @author Mary Farag
 *
 */
public class NYStylePizzaFactory implements PizzaFactory {
    /**
     * Create a deluxe style pizza and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * Create a Meatzza style pizza and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HANDTOSSED);
    }

    /**
     * Create a BBQChicken style pizza and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * Create a Build Your Own style pizza and sets the crust
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HANDTOSSED);
    }
}