package com.example.inventorymanagmentapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {



    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productNameView;
        private final TextView productIDView;
        private final TextView productPriceView;
        private final TextView productInvView;



        private ProductViewHolder(View itemView) {
            super(itemView);
     productIDView = itemView.findViewById(R.id.productID);
         productInvView = itemView.findViewById(R.id.productInventory);
        productNameView= itemView.findViewById(R.id.productName);
            productPriceView = itemView.findViewById(R.id.productPrice);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final ProductEntity current = productEntities.get(position);
                    Intent intent = new Intent(context, ProductAddActivity.class);
                    intent.putExtra("productName", current.getProdName());
                    intent.putExtra("productID",current.getProductID());
                    intent.putExtra("productInventory" ,current.getProdInv());
                    intent.putExtra("productPrice",current.getProdPrice());
                    intent.putExtra("productMax", current.getProdMax());
                    intent.putExtra("productMin",current.getProdMin());


                    intent.putExtra("position", position);
                    intent.putExtra("productInventoryID",current.getProductIDInventory());
                   // intent.putExtra("termID", current.getTermIdFk());
                    context.startActivity(intent);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<ProductEntity> productEntities;








    ProductAdapter(Context context) {

        this.context = context;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.product_table_view_list, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (productEntities != null) {
            ProductEntity current = productEntities.get(position);
            holder.productNameView.setText(current.getProdName());
            holder.productIDView.setText(current.getProductID());
            holder.productPriceView.setText(current.getProdPrice());
            holder.productInvView.setText(current.getProdInv());
        }

    }

    public void setProductEntities(List<ProductEntity> words) {
       productEntities = words;
        notifyDataSetChanged();

    }

    public ProductEntity getProductAt (int position) {
        return productEntities.get(position);
    }


    @Override
    public int getItemCount() {
        if (productEntities != null)
            return productEntities.size();
        else return 0;
    }







}