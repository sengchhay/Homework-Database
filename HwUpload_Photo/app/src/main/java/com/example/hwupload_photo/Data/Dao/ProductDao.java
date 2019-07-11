package com.example.hwupload_photo.Data.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hwupload_photo.Data.enility.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    Long save(Product product);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] save(List<Product> products);

    @Update
    void update(Product product);
    @Delete
    void remove(Product product);

    @Query("SELECT * FROM Product order by id asc")
    List<Product> getProducts();
}
