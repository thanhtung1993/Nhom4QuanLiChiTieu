package com.example.quanlychitieu.TabPage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quanlychitieu.Database.DatabaseKhoanThu;
import com.example.quanlychitieu.Database.DatabaseLoaiThu;
import com.example.quanlychitieu.Database.DatabaseTaiKhoan;
import com.example.quanlychitieu.Model.ModelKhoanThu;
import com.example.quanlychitieu.Model.ModelLoaiThu;
import com.example.quanlychitieu.Model.ModelTaiKhoan;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.ViewHolder.AdapterSpinner;
import com.example.quanlychitieu.ViewHolder.AdaterSpinnerTenTaiKhoan;
import com.example.quanlychitieu.ViewHolder.KhoanThuApdater;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;




public class KhoanThuFragment extends Fragment {
    TextView txtTongThu;
    RecyclerView recyclerView_KhoangThu;

    FloatingActionButton btnFabKhoanThu;

    RecyclerView.LayoutManager layoutManager;
    Spinner TaikhoanSp,LoaiThuSp;
    EditText edtSoTien,edtMoTa;

    Button btnNgay,BtnHienTai;

    DatabaseLoaiThu databaseLoaiThu;
    DatabaseKhoanThu databaseKhoanThu;
    List<ModelLoaiThu> listdata;
    List<ModelKhoanThu> listModelKhoanThu;
    List<ModelTaiKhoan> listModelTaiKhoan;
    KhoanThuApdater apdater;
    AdapterSpinner adapterSpinner;
    AdaterSpinnerTenTaiKhoan adapterTenTaiKhoan;
    DatabaseTaiKhoan databaseTaiKhoan;
    TextView txtBatloi,txtBatLoiLoai;
    int pos=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_khoan_thu,container,false);

        databaseLoaiThu=new DatabaseLoaiThu(getContext());

        databaseKhoanThu=new DatabaseKhoanThu(getContext());
        txtTongThu=view.findViewById(R.id.txttongthu);
        txtBatloi=view.findViewById(R.id.txtbatloi);

        recyclerView_KhoangThu=view.findViewById(R.id.recyclerview_khoanthu);
        recyclerView_KhoangThu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        databaseTaiKhoan=new DatabaseTaiKhoan(getContext());
        recyclerView_KhoangThu.setLayoutManager(layoutManager);
        btnFabKhoanThu=view.findViewById(R.id.fabkhoanthu);

        listdata=new ArrayList<>();
        listModelTaiKhoan =new ArrayList<>();
        listModelKhoanThu =new ArrayList<>();

        listModelTaiKhoan =databaseTaiKhoan.getTaiKhoan();

        listdata=databaseLoaiThu.layLoaiThu();

        Log.d("size", String.valueOf(listdata.size()));
        Log.d("size", listModelTaiKhoan.size()+"");
        btnFabKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(listModelTaiKhoan.size()<=0 || listdata.size()<=0){
                    txtBatloi.setVisibility(View.VISIBLE);
                }
                else {
                    ShowDialog();
                }

            }
        });
        LoadListKhoanThu();

        return view;
    }

    private void ShowDialog() {

        AlertDialog.Builder aLertDialog=new AlertDialog.Builder(getContext());
        aLertDialog.setTitle("Thêm Khoảng Thu ");
        aLertDialog.setMessage("Vui lòng nhập đủ thông tin!");

        LayoutInflater inflater=this.getLayoutInflater();

        View view_Add=inflater.inflate(R.layout.new_themkhoanthu_layout,null);

        btnNgay=view_Add.findViewById(R.id.btnNgay);
        BtnHienTai=view_Add.findViewById(R.id.btnHienTai);
        TaikhoanSp=view_Add.findViewById(R.id.loaitaikhoanSpinner);
        LoaiThuSp=view_Add.findViewById(R.id.loaithuSpinner);
        edtSoTien=view_Add.findViewById(R.id.edtSoTien);
        edtMoTa=view_Add.findViewById(R.id.edtMota);



        adapterTenTaiKhoan=new AdaterSpinnerTenTaiKhoan(getContext(),R.layout.item_spinner_taikhoan, listModelTaiKhoan);
        TaikhoanSp.setAdapter(adapterTenTaiKhoan);
        adapterTenTaiKhoan.notifyDataSetChanged();

        TaikhoanSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                pos= listModelTaiKhoan.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapterSpinner=new AdapterSpinner(getContext(),R.layout.item_spinner_loaithu,listdata);
        LoaiThuSp.setAdapter(adapterSpinner);
        adapterSpinner.notifyDataSetChanged();

        aLertDialog.setIcon(R.drawable.ic_add_circle_outline_black_24dp);

        getCurrentDate();
        btnNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate();
            }
        });

        aLertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        aLertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aLertDialog.setView(view_Add);
        final AlertDialog dialog=aLertDialog.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean err=true;
                ModelKhoanThu modelKhoanThu =new ModelKhoanThu();
                String Ngay=btnNgay.getText().toString();
                String TaiKhoan=TaikhoanSp.getSelectedItem().toString();
                String LoaiThu=LoaiThuSp.getSelectedItem().toString();
                String SoTien=edtSoTien.getText().toString();
                String Mota=edtMoTa.getText().toString();
                modelKhoanThu.setLoaiThu(LoaiThu);
                modelKhoanThu.setMoTa(Mota);
                modelKhoanThu.setSoTien(SoTien);
                modelKhoanThu.setTaiKhoan(TaiKhoan);
                modelKhoanThu.setNgay(Ngay);

                if(Ngay.equals("Chọn Ngày")){
                    err=false;
                    Toast.makeText(getContext(), "Vui Lòng Chọn Ngày", Toast.LENGTH_SHORT).show();
                }
                else if(SoTien.equals("")){
                    err=false;
                    Toast.makeText(getContext(), "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
                }
                if(err==true){

                    long check=databaseKhoanThu.AddKhoanThu(modelKhoanThu);
                    int add=0;
                    if(check>0)
                    {
                        for (ModelTaiKhoan tk : listModelTaiKhoan)
                        {
                            if(tk.getId()==pos){
                                add=Integer.parseInt(tk.getSoTienTaiKhoan()) + Integer.parseInt(SoTien);
                                tk.setSoTienTaiKhoan(String.valueOf(add));
                                databaseTaiKhoan.UpdateLoaiThu(tk);
                            }
                        }
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        LoadListKhoanThu();
                    }else {
                        Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void LoadListKhoanThu() {
        listModelKhoanThu =databaseKhoanThu.layKhoanThu();

        apdater=new KhoanThuApdater(listModelKhoanThu,getContext());
        recyclerView_KhoangThu.setAdapter(apdater);
        apdater.notifyDataSetChanged();

       /* NumberFormat fmt;
        Locale locale;
        locale = new Locale("vi", "VN");
        fmt = NumberFormat.getCurrencyInstance(locale);
        int total=0;
        for (ModelKhoanThu modelKhoanThu : listModelKhoanThu){
            total+=(Integer.parseInt(modelKhoanThu.getSoTien()));

        }
        txtTongThu.setText(fmt.format(total));*/



    }

    public void ChooseDate(){
        final Calendar calendar=Calendar.getInstance();
        //Date
        int Day=calendar.get(Calendar.DAY_OF_MONTH);
        int Month=calendar.get(Calendar.MONTH);
        int Year=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                btnNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },Year,Month,Day);
        datePickerDialog.show();
    }
    public void getCurrentDate(){
        BtnHienTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                //Time
                int seconds=calendar.get(Calendar.SECOND);
                int minute=calendar.get(Calendar.MINUTE);
                int hours=calendar.get(Calendar.HOUR);
                //Date
                int Month;
                int Day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                int Year=calendar.get(Calendar.YEAR);
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = sdf.format(calendar1.getTime());
                if(month<13){
                    month=month+1;
                }
                String Date=Day+"/"+month+"/"+Year;
                String Time=hours+":"+minute;
                btnNgay.setText(currentDate);

            }
        });
    }
}
