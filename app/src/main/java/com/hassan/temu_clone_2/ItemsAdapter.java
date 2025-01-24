package com.hassan.temu_clone_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private final Context context;
    private final List<items> itemsList;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;

    public ItemsAdapter(Context context, List<items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Bind the data to the view holder
        items currentItem = itemsList.get(position);
        holder.imageView.setImageResource(currentItem.getImageResId());
        holder.nameTextView.setText(currentItem.getName());
        holder.priceTextView.setText(currentItem.getPrice());

        // Handle card click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Detail_item.class);
            intent.putExtra("itemImage", currentItem.getImageResId());
            intent.putExtra("itemName", currentItem.getName());
            intent.putExtra("itemPrice", currentItem.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }




    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            nameTextView = itemView.findViewById(R.id.itemName);
            priceTextView = itemView.findViewById(R.id.itemPrice);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
