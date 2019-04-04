package com.example.quanlychitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.quanlychitieu.Model.ModelKhoanChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class DatabaseKhoanChi {


    SQLiteDatabase database;
    String TB="TAB";
    public DatabaseKhoanChi(Context context)
    {
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.Open();
    }

    public static String Today="SELECT * FROM " + CreateDatabase.TB_KHOANCHI+" WHERE "+CreateDatabase.TB_KHOANCHI_NGAY+" ='"+chooseDate()+"'";
    //public static String Todaytest="SELECT * FROM " + CreateDatabase.TB_KHOANCHI+" WHERE "+CreateDatabase.TB_KHOANCHI_NGAY+" ='"+chooseDate()+"'"+7;

    public static String ThisWeek = "SELECT * FROM "+CreateDatabase.TB_KHOANCHI+" WHERE strftime('%W',"+CreateDatabase.TB_KHOANCHI_NGAY+") = strftime('%W',date('now')) " +
            "AND  strftime('%m',"+CreateDatabase.TB_KHOANCHI_NGAY+") = strftime('%m',date('now'))"+
            "AND strftime('%Y',"+CreateDatabase.TB_KHOANCHI_NGAY+") = strftime('%Y',date('now'))";

    public static String ThisYear="SELECT * FROM "+CreateDatabase.TB_KHOANCHI+" WHERE strftime('%Y',"+CreateDatabase.TB_KHOANCHI_NGAY+") = strftime('%Y',date('now'))";

    public static String ThisMonth="SELECT * FROM "+CreateDatabase.TB_KHOANCHI+" WHERE strftime('%Y',"+CreateDatabase.TB_KHOANCHI_NGAY+") = strftime('%Y',date('now')) " +
            "AND  strftime('%m',"+CreateDatabase.TB_KHOANCHI_NGAY+") = strftime('%m',date('now'))";

    public long ThemKhoanChi(ModelKhoanChi modelKhoanChi)
    {
        ContentValues contentValues=new ContentValues();

        contentValues.put(CreateDatabase.TB_KHOANCHI_NGAY, modelKhoanChi.getNgayChi());
        contentValues.put(CreateDatabase.TB_KHOANCHI_TAIKHOAN, modelKhoanChi.getTaiKhoanChi());
        contentValues.put(CreateDatabase.TB_KHOANCHI_SOTIEN, modelKhoanChi.getSoTienChi());
        contentValues.put(CreateDatabase.TB_KHOANCHI_MOTA, modelKhoanChi.getMoTaChi());
        contentValues.put(CreateDatabase.TB_KHOANCHI_LOAICHI, modelKhoanChi.getLoaiChi());

        long check=database.insert(CreateDatabase.TB_KHOANCHI,null,contentValues);
        Log.d(TB, String.valueOf(check));
        return check;
    }
    public List<ModelKhoanChi> layKhoanChi(){

        List<ModelKhoanChi> modelKhoanChiList=new ArrayList<>();

        String TruyVan=" SELECT * FROM " + CreateDatabase.TB_KHOANCHI;


        Cursor cursor=database.rawQuery(TruyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ModelKhoanChi modelKhoanChi =new ModelKhoanChi();
            modelKhoanChi.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_ID)));
            modelKhoanChi.setNgayChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_NGAY)));
            modelKhoanChi.setLoaiChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_LOAICHI)));
            modelKhoanChi.setMoTaChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_MOTA)));
            modelKhoanChi.setSoTienChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_SOTIEN)));
            modelKhoanChi.setTaiKhoanChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_TAIKHOAN)));
            modelKhoanChiList.add(modelKhoanChi);
            cursor.moveToNext();
        }


        return modelKhoanChiList;
    }

    public List<ModelKhoanChi> layKhoanChiTheoNgayThangNam(String truyvan){

        List<ModelKhoanChi> list=new ArrayList<>();
        Cursor cursor=database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ModelKhoanChi modelKhoanChi =new ModelKhoanChi();
            modelKhoanChi.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_ID)));
            modelKhoanChi.setNgayChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_NGAY)));
            modelKhoanChi.setLoaiChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_LOAICHI)));
            modelKhoanChi.setMoTaChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_MOTA)));
            modelKhoanChi.setSoTienChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_SOTIEN)));
            modelKhoanChi.setTaiKhoanChi(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_KHOANCHI_TAIKHOAN)));
            list.add(modelKhoanChi);
            cursor.moveToNext();
        }


        return list;
    }

    public static String chooseDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// bắt buộc phải định dạng theo năm tháng ngày
        String currentDate = sdf.format(calendar.getTime());
        return currentDate;
    }


}
