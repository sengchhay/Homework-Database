package com.example.hwupload_photo;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hwupload_photo.Data.ProductDatabase;
import com.example.hwupload_photo.Data.enility.Product;

public class EditProductActivity extends AppCompatActivity {


    EditText edtnamepro, edtprice, edtlocation, edtdesc;
    Button btnSave;
    Product product;
    ImageView btnbackEdit;
    ProductDatabase productDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtnamepro = findViewById(R.id.edtNamePro);
        edtprice = findViewById(R.id.edtPrice);
        edtlocation = findViewById(R.id.edtLocation);
        edtdesc = findViewById(R.id.edtDesc);
        btnSave = findViewById(R.id.btnSave1);
        btnbackEdit= findViewById(R.id.btnbackEdit);

        productDatabase = ProductDatabase.getInstance(this);

        Intent intent = getIntent();

        if (intent != null) {
            product = intent.getParcelableExtra("product");
            if (product != null) {
                edtnamepro.setText(product.namepro);
                edtlocation.setText(product.location);
                edtprice.setText("" + product.price);
                edtdesc.setText(product.desc);
            }
        }

        btnSave.setOnClickListener(v -> {
            if (product == null)
                product = new Product();

            product.namepro = edtnamepro.getText().toString();
            product.location = edtlocation.getText().toString();
            product.desc = edtdesc.getText().toString();
            product.price = Double.parseDouble(edtprice.getText().toString());

            productDatabase.productDao().update(product);
            Log.e("Debug", "" + productDatabase.productDao().getProducts().toString());
            Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditProductActivity.this,MainActivity.class));
            finish();
        });
        btnbackEdit.setOnClickListener(v->{
            startActivity( new Intent(this,MainActivity.class));
            finish();
        });
    }
}
