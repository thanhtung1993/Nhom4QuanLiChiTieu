package com.example.quanlychitieu.Model;



public class ModelKhoanThu {
    int Id;
    String Ngay,TaiKhoan,SoTien,MoTa,LoaiThu;

    public ModelKhoanThu() {
    }

    public ModelKhoanThu(String ngay, String taiKhoan, String soTien, String moTa, String loaiThu) {
        Ngay = ngay;
        TaiKhoan = taiKhoan;
        SoTien = soTien;
        MoTa = moTa;
        LoaiThu = loaiThu;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getSoTien() {
        return SoTien;
    }

    public void setSoTien(String soTien) {
        SoTien = soTien;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getLoaiThu() {
        return LoaiThu;
    }

    public void setLoaiThu(String loaiThu) {
        LoaiThu = loaiThu;
    }
}
