package com.example.quanlychitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.quanlychitieu.Model.ModelTaiKhoan;

import java.util.ArrayList;
import java.util.List;



public class DatabaseTaiKhoan {

    SQLiteDatabase database;
    String TB="TAB";
    public DatabaseTaiKhoan(Context context)
    {
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.Open();
    }

    public long ThemTaiKhoan(ModelTaiKhoan modelTaiKhoan){

        ContentValues contentValues=new ContentValues();

        contentValues.put(CreateDatabase.TB_TAIKHOAN_NAME, modelTaiKhoan.getTenTaiKhoan());

        contentValues.put(CreateDatabase.TB_TAIKHOAN_SOTIEN, modelTaiKhoan.getSoTienTaiKhoan());


        long check=database.insert(CreateDatabase.TB_TAIKHOAN,null,contentValues);

        return check;
    }

    public List<ModelTaiKhoan> getTaiKhoan(){

        List<ModelTaiKhoan> list=new ArrayList<>();

        String TruyVan=" SELECT * FROM " + CreateDatabase.TB_TAIKHOAN;

        Cursor cursor=database.rawQuery(TruyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ModelTaiKhoan modelTaiKhoan =new ModelTaiKhoan();
            modelTaiKhoan.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TAIKHOAN_ID)));
            modelTaiKhoan.setTenTaiKhoan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TAIKHOAN_NAME)));
            modelTaiKhoan.setSoTienTaiKhoan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TAIKHOAN_SOTIEN)));
            list.add(modelTaiKhoan);
            cursor.moveToNext();
        }
        Log.d("checkNao", list.toString());
        return list;
    }

    public boolean UpdateLoaiThu(ModelTaiKhoan modelTaiKhoan){

        ContentValues contentValues=new ContentValues();

        contentValues.put(CreateDatabase.TB_TAIKHOAN_SOTIEN, modelTaiKhoan.getSoTienTaiKhoan());

        long check=database.update(CreateDatabase.TB_TAIKHOAN,contentValues,CreateDatabase.TB_LOAITHU_ID + " = " + modelTaiKhoan.getId(),null);

        if(check !=0){
            return true;
        }else {
            return false;
        }

    }
}
