package com.slon.JsonPizzaJointTest;

import java.util.BitSet;
import org.apache.log4j.Logger;

/**
 * Created by slon on 19.03.2018.
 */
public class Order {
    final static Logger logger = Logger.getLogger(Order.class);

    private Boolean orderStatus;
    private String reasonForStatus;
    private PizzaRecipe pizzaRecipe;

    public Order(PizzaRecipe pizzaRecipeIn) {
        this.pizzaRecipe = pizzaRecipeIn;
        this.orderStatus=false;
        this.reasonForStatus="Incompatible ingredients \n";
        testIngridients();
    }

    public Boolean isOrderStatus() {
        return orderStatus;
    }
    public String getReasonForStatus() {
        return reasonForStatus;
    }

    private void prepareOrder(){
        testIngridients();
    }
    private void testIngridients(){
        BitSet ingredientsNames = new BitSet();
        BitSet finalResult = new BitSet();
        String usedIngredients="";
        String incompatibleIngredients="";

        for (Ingredients ingridient: Ingredients.values()) {
            int ingr = (ingridient.getIngridient() & (this.pizzaRecipe.getIngredients().intValue()));
            int mask = (ingridient.getMask() & (this.pizzaRecipe.getIngredients().intValue()));
            boolean result01 = (ingr == ingridient.getIngridient());
            boolean result02=  (mask > 0);

                if (result01 && result02) {
                    finalResult.set(ingridient.getIngridient());
                    incompatibleIngredients = incompatibleIngredients + ingridient.getIngridientName() +"\n";
                } else if (result01) {
                    usedIngredients = usedIngredients + ingridient.getIngridientName() + "; " ;
                }
        }

        if (finalResult.isEmpty()){
            this.orderStatus=true;
            this.reasonForStatus="Used ingredients: \n"+usedIngredients;
            logger.debug("All is good with the recipe:"+ this.reasonForStatus +"\n");
        }else{
            this.reasonForStatus = this.reasonForStatus+incompatibleIngredients;
            logger.debug("Something incompatible:"+ this.reasonForStatus +"\n");
        }
    }

    private String checkNames(BitSet ingredientsNames){
        String usedIngredients="";
        for(int i=1; i<ingredientsNames.length(); i++){
            for (Ingredients ingridient: Ingredients.values()) {
                if(i==ingridient.getIngridient()) {
                    usedIngredients = usedIngredients + ingridient.getIngridientName() + "; " ;
                }
            }
        }
        return usedIngredients;
    }
}
