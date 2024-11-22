package com.example.mobileproject.recommend;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.ArrayList;
import java.util.List;

public class Menu_Adapter extends RecyclerView.Adapter<Menu_Adapter.ViewHolder> {

    private List<Menu_Init> menuList;

    public Menu_Adapter(List<Menu_Init> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu_Init menu = menuList.get(position);

        holder.nameTextView.setText(menu.getName());
        holder.ingredientsTextView.setText(String.join(", ", menu.getIngredients()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Step_Page.class);
                intent.putExtra("menu_name", menu.getName());
                intent.putStringArrayListExtra("menu_steps", new ArrayList<>(menu.getSteps()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, ingredientsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ingredientsTextView = itemView.findViewById(R.id.ingredientsTextView);
        }
    }
}
