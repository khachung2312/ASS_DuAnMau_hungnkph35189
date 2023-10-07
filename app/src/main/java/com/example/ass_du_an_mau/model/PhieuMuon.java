package com.example.ass_du_an_mau.model;

public class PhieuMuon {
    public int maPM;
    public int maTV, maSach, tienThue, traSach;
    public String ngay;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, int maTV, int maSach, int tienThue, int traSach, String ngay) {
        this.maPM = maPM;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.ngay = ngay;
    }
}
