package com.example.inventorymanagmentapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.PartViewHolder> {

    class PartViewHolder extends RecyclerView.ViewHolder {
        private final TextView partNameView;
        private final TextView partIDView;
        private final TextView partPriceView;
        private final TextView partInvView;



        private PartViewHolder(View itemView) {
            super(itemView);
            partNameView = itemView.findViewById(R.id.partName);
            partIDView = itemView.findViewById(R.id.partID);
            partPriceView  = itemView.findViewById(R.id.partPrice);
            partInvView = itemView.findViewById(R.id.partInventory);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final PartEntity current = partEntities.get(position);
                    Intent intent = new Intent(context, PartAddActivity.class);
                    intent.putExtra("partCompanyName", current.getCompanyName());
                    intent.putExtra("partName", current.getPartName());
                    intent.putExtra("partID", current.getPartID());
                    intent.putExtra("partInventory", current.getPartInventory());
                    intent.putExtra("partPrice", current.getPartPrice());
                    intent.putExtra("partMax", current.getPartMax());
                    intent.putExtra("partMin", current.getPartMin());
                    intent.putExtra("position", position);
                    intent.putExtra("partInventoryID", current.getPartInventoryID());
                    context.startActivity(intent);
                }
            });
        }

    }


    private final LayoutInflater mInflater;
    private final Context context;
    private List<PartEntity> partEntities;


   public PartAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public PartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.part_table_item_list, parent, false);

        return new PartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PartViewHolder holder, int position) {
        if (partEntities != null) {
            PartEntity current = partEntities.get(position);
            holder.partNameView.setText(current.getPartName());
            holder.partIDView.setText(current.getPartID());
            holder.partPriceView.setText(current.getPartPrice());
            holder.partInvView.setText(current.getPartInventory());
        }


    }

    public void setPartEntities(List<PartEntity> words) {
        partEntities = words;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (partEntities != null)
            return partEntities.size();
        else return 0;
    }

    public PartEntity getPartAt(int position) {
        return partEntities.get(position);
    }
}