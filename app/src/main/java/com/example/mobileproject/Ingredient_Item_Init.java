package com.example.mobileproject;

public class Ingredient_Item_Init {
    private String ingredint_name;
    private int quantity;

    public Ingredient_Item_Init(String name, int quantity) {
        this.ingredint_name = name;
        this.quantity = quantity;
    }

    public String getIngredint_name(){
        return ingredint_name;
    }

    public int getQuantity(){
        return quantity;
    }
}
