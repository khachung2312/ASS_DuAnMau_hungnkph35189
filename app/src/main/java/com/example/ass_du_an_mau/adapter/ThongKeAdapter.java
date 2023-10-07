package com.example.ass_du_an_mau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ass_du_an_mau.R;
import com.example.ass_du_an_mau.dao.ThongKeDAO;
import com.example.ass_du_an_mau.model.Top;

import java.util.ArrayList;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeViewHodel> {
    ArrayList<Top> arrTop = new ArrayList<>();
    ThongKeDAO thongKeDAO;
    Context context;


    public ThongKeAdapter(Context context, ArrayList<Top> arrTop) {
        this.context = context;
        this.arrTop = arrTop;
    }
    @NonNull
    @Override
    public ThongKeViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.item_top, parent, false);
        ThongKeViewHodel thongKeViewHodel = new ThongKeViewHodel(viewItem);
        return thongKeViewHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeViewHodel holder, int position) {
        Top top = arrTop.get(position);
        holder.tv_name_sach.setText("Tên sách: " + top.tenSach);
        holder.numberSach.setText("Số lượt mượn: " + top.soLuong + "");
    }

    @Override
    public int getItemCount() {
        return arrTop.size();
    }
}
