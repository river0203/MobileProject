package com.example.mobileproject;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();
    private String msgList;

    public void setPulsIngredients(String ingredient) {
        this.ingredients.add(ingredient);
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    public String engraftIngredientsList(List<String> ingredientList)
    {
        String temp = String.join(" ", ingredientList);
        return temp;
    }

    public String getMsgList() {
        return msgList;
    }
    public void setMsgList(String tempMsgList) {
        tempMsgList = this.msgList;
    }

    public void printAryList()
    {
        for(String str : ingredients)
        {
            System.out.println(str);
        }
    }
}
