package com.example.mobileproject.recommend;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.MainActivity;
import com.example.mobileproject.R;
import com.example.mobileproject.SionActivity;
import com.example.mobileproject.plus.Plus_Main_Page;

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
                // 현재 컨텍스트에서 SionActivity로 이동
                Intent intent = new Intent(v.getContext(), SionActivity.class);

                // 선택된 메뉴 데이터를 전달
                intent.putExtra("menu_name", menu.getName());
                intent.putExtra("menu_ingredients", String.join(", ", menu.getIngredients()));

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
