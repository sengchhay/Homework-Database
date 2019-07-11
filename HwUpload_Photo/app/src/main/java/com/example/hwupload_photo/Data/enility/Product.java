package com.example.hwupload_photo.Data.enility;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


@Entity(tableName = "product")
public class Product implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id  ;

    public String namepro;
    public String location;
    public  String desc;
    public  double price;

    @ColumnInfo(name = "user_id")
    public int userId;
    @Ignore
    public Bitmap bitmap;



    public Product(Parcel in){
        id=in.readInt();
        namepro=in.readString();
        location=in.readString();
        desc=in.readString();
        price=in.readDouble();
        userId=in.readInt();
        bitmap=in.readParcelable(Bitmap.class.getClassLoader());
    }


    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Product() {

    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(namepro);
        dest.writeString(location);
        dest.writeString(desc);
        dest.writeDouble(price);
        dest.writeInt(userId);
        dest.writeParcelable(bitmap, flags);
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", namepro='" + namepro + '\'' +
                ", location='" + location + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                ", bitmap=" + bitmap +
                '}';
    }
}
