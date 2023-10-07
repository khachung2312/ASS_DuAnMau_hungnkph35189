package com.example.ass_du_an_mau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static  final  String dbName = "PNLIB";
    static  final int dbVersion = 2;

    public static final String TABLE_NAME_THU_THU = "thuthu";
    public static final String TABLE_NAME_THANH_VIEN = "thanhvien";
    public static final String TABLE_NAME_PHIEU_MUON = "phieumuon";
    public static final String TABLE_NAME_LOAI_SACH = "loaisach";
    public static final String TABLE_NAME_SACH = "sach";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang thu thu
        String tb_ThuThu =
                "create table " + TABLE_NAME_THU_THU + "(" +
                        "maTT TEXT primary key, " +
                        "hoTen TEXT not null, " +
                        "matKhau TEXT not null)";
        db.execSQL(tb_ThuThu);
        String setAdmin = "INSERT INTO thuthu VALUES('admin', 'Admin', 'admin')";
        String setUser1 = "INSERT INTO thuthu VALUES('hung', 'NguyenKhacHung', '1234')";
        db.execSQL(setAdmin);
        db.execSQL(setUser1);


        //tao bang thanh vien
        String tb_ThanhVien =
                "create table " + TABLE_NAME_THANH_VIEN + "(" +
                        "maTV INTEGER primary key AUTOINCREMENT, " +
                        "hoTen TEXT not null, " +
                        "namSinh TEXT not null)";
        db.execSQL(tb_ThanhVien);

        //tao bang loai sach
        String tb_LoaiSach =
                "create table " + TABLE_NAME_LOAI_SACH + "(" +
                        "maLoai INTEGER primary key AUTOINCREMENT, " +
                        "tenLoai TEXT not null)";
        db.execSQL(tb_LoaiSach);

        //tao bang sach
        String tb_Sach =
                "create table " + TABLE_NAME_SACH + "(" +
                        "maSach INTEGER primary key AUTOINCREMENT, " +
                        "tenSach TEXT not null, " +
                        "giaThue INTEGER not null, " +
                        "maLoai INTEGER REFERENCES loaisach(maLoai))";
        db.execSQL(tb_Sach);

        //tao bang loai Phieu muon
        String tb_PhieuMuon =
                "create table " + TABLE_NAME_PHIEU_MUON + "(" +
                        "maPM INTEGER primary key AUTOINCREMENT, " +
                        "maTV INTEGER REFERENCES thanhvien(maTV), " +
                        "maSach INTEGER REFERENCES sach(maSach), " +
                        "tienThue INTEGER not null, " +
                        "ngay DATE not null, " +
                        "traSach INTEGER not null )";
        db.execSQL(tb_PhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = "drop table if exists thuthu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists thanhvien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists loaisach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists phieumuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
