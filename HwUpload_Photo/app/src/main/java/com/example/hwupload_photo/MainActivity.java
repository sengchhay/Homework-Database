package com.example.hwupload_photo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.hwupload_photo.Adapter.ProductAdapter;
import com.example.hwupload_photo.Data.ProductDatabase;
import com.example.hwupload_photo.Data.enility.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements ProductAdapter.OnItemClick{

    private RecyclerView rcProduct;
    private List<Product> productList =new ArrayList<>();
    private ProductAdapter productAdapter;
    private Product product;
    ProductDatabase productDatabase;
    FloatingActionButton btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcProduct=findViewById(R.id.rcSell);
        btnAddItem=findViewById(R.id.btnAddItem);
        intUI();
        getProducts();
    }
    private void intUI() {
        productDatabase = ProductDatabase.getInstance(this);
        rcProduct.setLayoutManager( new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList,this,this);
        rcProduct.setAdapter(productAdapter);

        btnAddItem.setOnClickListener(v -> {

            startActivity(new Intent(this, AddProductlActivity.class));
            finish();
        });
    }

    private void getProducts() {
        List<Product> products = productDatabase.productDao().getProducts();
        Log.e("activity",""+ productList);
        productAdapter.addMoreItem(products);
    }

    @Override
    public void onRemoveItem(Product product, int position) {
        productDatabase.productDao().remove(product);
        productAdapter.removeItem(product,position);
        Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEdititem(Product product, int position) {
        Intent intent = new Intent(this, EditProductActivity.class);
        intent.putExtra("product",product);
        startActivity(intent);

    }
}
