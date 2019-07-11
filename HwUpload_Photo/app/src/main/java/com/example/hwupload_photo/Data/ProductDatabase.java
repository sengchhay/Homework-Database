package com.example.hwupload_photo.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hwupload_photo.Data.Dao.ProductDao;
import com.example.hwupload_photo.Data.enility.Product;

@Database(version = 1,entities = {Product.class})
public abstract class ProductDatabase extends RoomDatabase {
    static final String DB_NAME="product_db";

    public abstract ProductDao productDao();
    public static ProductDatabase getInstance(Context context){

        return Room.databaseBuilder(context, ProductDatabase.class,DB_NAME).allowMainThreadQueries().addMigrations().build();
    }

}
