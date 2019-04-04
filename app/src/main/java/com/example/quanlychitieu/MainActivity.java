package com.example.quanlychitieu;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.quanlychitieu.Database.DatabaseKhoanChi;
import com.example.quanlychitieu.Fragment.ChiFragment;
import com.example.quanlychitieu.Fragment.GioiThieuFragment;
import com.example.quanlychitieu.Fragment.NapTienVaoTaiKhoanFragment;
import com.example.quanlychitieu.Fragment.ThonKeFragment;
import com.example.quanlychitieu.Fragment.ThuFragment;
import com.example.quanlychitieu.Model.ModelKhoanChi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    TextView txtFulllname;
    TextView txtGetTiltle;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        txtGetTiltle=findViewById(R.id.txttoolbartil);
        drawerLayout =  findViewById(R.id.Drawerlayout);
        navigationView =  findViewById(R.id.navigationview);
        toolbar =  findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);//ba lằng;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.mo, R.string.dong) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setItemIconTintList(null);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction tranHienThiThongke = fragmentManager.beginTransaction();//
        ThonKeFragment hienThiThonke = new ThonKeFragment();
        tranHienThiThongke.replace(R.id.content, hienThiThonke);
        tranHienThiThongke.commit();
        txtGetTiltle.setText("Thống Kê");
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_ThongKe:

                FragmentTransaction tranHienThiThongke = fragmentManager.beginTransaction();//
                ThonKeFragment hienThiThonke = new ThonKeFragment();
                tranHienThiThongke.replace(R.id.content, hienThiThonke);
                tranHienThiThongke.commit();
                txtGetTiltle.setText("Thống Kê");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_KhoanThu:
                FragmentTransaction tranHienThiThu = fragmentManager.beginTransaction();//
                ThuFragment hienThithu = new ThuFragment();
                tranHienThiThu.replace(R.id.content, hienThithu);
                tranHienThiThu.commit();
              // txtGetTiltle.setText("Khoản Thu");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.nav_KhoanChi:
                FragmentTransaction tranHienThiChi = fragmentManager.beginTransaction();//
                ChiFragment hienThichi = new ChiFragment();
                tranHienThiChi.replace(R.id.content, hienThichi);
                tranHienThiChi.commit();
                txtGetTiltle.setText("Khoản Chi");
                item.setChecked(true);
                drawerLayout.closeDrawers();



                break;

            case R.id.nav_TaiKhoan:
                FragmentTransaction tranHienTaiKhoan = fragmentManager.beginTransaction();//

                NapTienVaoTaiKhoanFragment hienThiTaiKhoan = new NapTienVaoTaiKhoanFragment();
                tranHienTaiKhoan.replace(R.id.content, hienThiTaiKhoan);
                tranHienTaiKhoan.commit();
                txtGetTiltle.setText("Quản Lí Tài Khoản");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.nav_gioithieu:
                FragmentTransaction tranHienGioiThieu = fragmentManager.beginTransaction();//

                GioiThieuFragment hienThiGioiThieu = new GioiThieuFragment();
                tranHienGioiThieu.replace(R.id.content, hienThiGioiThieu);
                tranHienGioiThieu.commit();
                txtGetTiltle.setText("Giới Thiệu");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.nav_thoat:
                finish();
                System.exit(0);
                break;

        }
        return false;
    }

}
