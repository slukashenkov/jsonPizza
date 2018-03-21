package com.slon.JsonPizzaJointTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.apache.log4j.Logger;

/**
 * jsonBased Pizza Joint
 *
 */
public class JsonPizzaJoint {
    final static Logger logger = Logger.getLogger(JsonPizzaJoint.class);

    private Order order;
    private PizzaRecipe recipe;

    public JsonPizzaJoint() {
        this.recipe = new PizzaRecipe();
    }


    public JsonPizzaJoint acceptOrder(String pizzaRecipe){
        logger.debug("Enter joint with the following recipe: \n"+pizzaRecipe+"\n");
        this.recipe = jsonToRecipe(pizzaRecipe);
        this.order = new Order(this.recipe);
        return this;
    }

    public Order serveOrder(){
        return this.order;
    }

    private PizzaRecipe jsonToRecipe(String recipeToConvert) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        // Deserialization
        this.recipe = gson.fromJson(recipeToConvert, PizzaRecipe.class);
        return this.recipe;
    }

}
