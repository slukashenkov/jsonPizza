package com.slon.JsonPizzaJointTest;

/**
 * Created by slon on 19.03.2018.
 */
public enum Ingredients {

    ingridient01(1, 0, "ingridient01"),
    ingridient02(2, 12, "ingridient02"),
    ingridient03(4, 2, "ingridient03"),
    ingridient04(8, 2, "ingridient04"),
    ingridient05(16, 0, "ingridient05"),
    ingridient06(32, 0, "ingridient06");


    private int mask;
    private int ingridient;
    private String ingridientName;

    Ingredients(int ingridient, int mask, String ingridientName){
        this.ingridient =ingridient;
        this.mask=mask;
        this.ingridientName=ingridientName;
    }
    public int getMask(){
        return this.mask;
    };
    public int getIngridient(){
        return this.ingridient;
    };

    public String getIngridientName() {
        return ingridientName;
    }
}


