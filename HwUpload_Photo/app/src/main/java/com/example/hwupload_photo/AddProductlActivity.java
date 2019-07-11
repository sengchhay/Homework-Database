package com.example.hwupload_photo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hwupload_photo.Data.ProductDatabase;
import com.example.hwupload_photo.Data.enility.Product;

import java.util.List;

public class AddProductlActivity extends AppCompatActivity {

    EditText ednamepro,edprice,edlocation,eddesc;
    Button btnSave;
    ImageView btnback;
    ProductDatabase productDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_sell);

        ednamepro=findViewById(R.id.edNamePro);
        edprice=findViewById(R.id.edPrice);
        edlocation=findViewById(R.id.edLocation);
        eddesc=findViewById(R.id.edDesc);

        btnback=findViewById(R.id.btnback);
        btnSave=findViewById(R.id.btnSave);

        productDatabase = ProductDatabase.getInstance(this);


        btnSave.setOnClickListener(v -> {
                Product product = new Product();
                product.namepro = ednamepro.getText().toString();
                product.price = Double.parseDouble(edprice.getText().toString());
                product.location = edlocation.getText().toString();
                product.desc = eddesc.getText().toString();
                saveSell(product);
                startActivity(new Intent(AddProductlActivity.this,MainActivity.class));
                Toast.makeText(AddProductlActivity.this, "Save", Toast.LENGTH_SHORT).show();
        });
        btnback.setOnClickListener(v->{
            startActivity( new Intent(this,MainActivity.class));
            finish();
        });
    }

    private long saveSell(Product product) {
        if (product !=null){
            return productDatabase.productDao().save(product);
        }
        return 0l;
    }
  //  private static final String TAG = "Hello" ;
//    private void getSellList(){
//        List<Product> products = productDatabase.productDao().getProducts();
//        Log.e(TAG,"getProducts: "+ products);
//
//    }
}
