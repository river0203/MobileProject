package com.example.mobileproject.plus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.List;


public class Ingredient_Adapter extends RecyclerView.Adapter<Ingredient_Adapter.ViewHolder> {
    private Context context;
    private List<Ingredient_Item_Init> ingredientItemList;




    public Ingredient_Adapter(Context context, List<Ingredient_Item_Init> ingredientItemList) {
        this.context = context;
        this.ingredientItemList = ingredientItemList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient_Item_Init currentItem = ingredientItemList.get(position);
        holder.ingredientName.setText(currentItem.getIngredint_name());
        holder.ingredientQuantity.setText(String.valueOf(currentItem.getQuantity()));
    }


    @Override
    public int getItemCount() {
        return ingredientItemList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView ingredientQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientQuantity = itemView.findViewById(R.id.ingredient_quantity);
        }
    }
}
