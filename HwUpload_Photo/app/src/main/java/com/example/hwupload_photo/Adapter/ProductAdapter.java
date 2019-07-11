package com.example.hwupload_photo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.hwupload_photo.Data.Dao.ProductDao;
import com.example.hwupload_photo.Data.enility.Product;
import com.example.hwupload_photo.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private AppCompatActivity context;

    public ProductAdapter(List<Product> productList, AppCompatActivity Context, OnItemClick listener) {
        this.productList = productList;
        this.context = Context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.custom_layout, viewGroup,false);

        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Product product = productList.get(position);

        viewHolder.imageView.setImageResource(R.drawable.laptop);
        viewHolder.tvNamepro.setText(product.namepro);
        viewHolder.tvLocation.setText(product.location);
        viewHolder.tvDesc.setText(product.desc);
        viewHolder.tvPrice.setText(""+ product.price);

        viewHolder.btnMore.setOnClickListener(v->{

            PopupMenu menu =new PopupMenu(context,v);
            menu.inflate(R.menu.option_menu);
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.btnRemove:
                        listener.onRemoveItem(product,viewHolder.getAdapterPosition());
                        return true;
                    case  R.id.btnEdit:
                        listener.onEdititem(product,viewHolder.getAdapterPosition());
                        return  true;
                }
                return  false;
            });
            menu.show();
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvNamepro,tvDesc,tvPrice,tvLocation;
        ImageView btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            tvNamepro=itemView.findViewById(R.id.tvNamePro);
            tvDesc=itemView.findViewById(R.id.tvDesc);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            btnMore=itemView.findViewById(R.id.btnMore);
        }
    }
    public void addMoreItem(List<Product> products) {

        int previousSize=getItemCount();
        this.productList.addAll(products);
        Log.e("Product size",""+this.productList.size());
        notifyItemRangeInserted( previousSize -1,this.productList.size());
    }

    public  void removeItem(Product product, int position){
        this.productList.remove(product);
        notifyItemRemoved(position);
    }
    OnItemClick listener;
    public  interface OnItemClick{

        void onRemoveItem(Product product, int position);
        void onEdititem(Product product, int position);
    }
}
