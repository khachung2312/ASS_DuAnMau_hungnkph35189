package com.example.ass_du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.ass_du_an_mau.database.DBHelper;
import com.example.ass_du_an_mau.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public static  final  String TABLE_NAME_LOAI_SACH = DBHelper.TABLE_NAME_LOAI_SACH;
    public LoaiSachDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public long insertLoaiSach (LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.tenLoai);

        return sqLiteDatabase.insert(TABLE_NAME_LOAI_SACH, null, values);
    }

    public long updateLoaiSach (LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.tenLoai);

        return sqLiteDatabase.update(TABLE_NAME_LOAI_SACH, values, "maLoai=?", new String[]{String.valueOf(loaiSach.maLoai)});
    }

    public int deleteLoaiSach(String id) {
        return sqLiteDatabase.delete(TABLE_NAME_LOAI_SACH, "maLoai=?", new String[]{id});
    }

    public List<LoaiSach> getAllLoaiSach() {
        String sql = "SELECT * FROM " + TABLE_NAME_LOAI_SACH;
        return getData(sql);
    }

    public LoaiSach getIdLoaiSach (String id) {
        String sql = "SELECT * FROM " + TABLE_NAME_LOAI_SACH + " WHERE maLoai=?";
        List<LoaiSach> listLoaiSach = getData(sql, id);
        return listLoaiSach.get(0);
    }


    private List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> listLoaiSach = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            loaiSach.tenLoai = cursor.getString(cursor.getColumnIndex("tenLoai"));
            listLoaiSach.add(loaiSach);
        }
        return listLoaiSach;
    }

}
