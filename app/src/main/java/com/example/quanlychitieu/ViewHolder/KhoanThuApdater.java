package com.example.quanlychitieu.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.quanlychitieu.ItemOnClickListener;
import com.example.quanlychitieu.KhoanThuChiTietActivity;
import com.example.quanlychitieu.Model.ModelKhoanThu;
import com.example.quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;


class KhoanThuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_KhoangThu,txt_SoTien,txtNgay;
    public List<ModelKhoanThu> thuList=new ArrayList<>();
    public Context context;
    private ItemOnClickListener itemOnClickListener;

    public KhoanThuViewHolder(View itemView,Context context,List<ModelKhoanThu> list) {
        super(itemView);
        this.thuList=list;
        this.context=context;
        txt_KhoangThu=itemView.findViewById(R.id.txtLoaiThu);
        txt_SoTien=itemView.findViewById(R.id.txtSoTien);
        txtNgay=itemView.findViewById(R.id.txtNgay);
        itemView.setOnClickListener(this);
        //itemView.setOnClickListener(this);
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }
    String Ngay,TaiKhoan,SoTien,MoTa,LoaiThu;
    @Override
    public void onClick(View view) {
//        itemOnClickListener.onClick(view,getAdapterPosition(),false);
        int position= getAdapterPosition();
        ModelKhoanThu modelKhoanThu =thuList.get(position);
        Intent intent=new Intent(this.context, KhoanThuChiTietActivity.class);
        intent.putExtra("ngay", modelKhoanThu.getNgay());
        intent.putExtra("taikhoan", modelKhoanThu.getTaiKhoan());
        intent.putExtra("sotien", modelKhoanThu.getSoTien());
        intent.putExtra("mota", modelKhoanThu.getMoTa());
        intent.putExtra("loaithu", modelKhoanThu.getLoaiThu());
        context.startActivity(intent);

    }
}

public class KhoanThuApdater  extends RecyclerView.Adapter<KhoanThuViewHolder>{

    private List<ModelKhoanThu> listData= new ArrayList<>();
    private Context context;

    public KhoanThuApdater(List<ModelKhoanThu> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public KhoanThuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.item_khoan_thu,parent,false);
        return new KhoanThuViewHolder(itemView,context,listData);
    }


    @Override
    public void onBindViewHolder(KhoanThuViewHolder holder, int position) {

        holder.txt_KhoangThu.setText(listData.get(position).getLoaiThu());

        holder.txt_SoTien.setText(listData.get(position).getSoTien() + " đ");
        holder.txtNgay.setText(listData.get(position).getNgay());
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }

}
