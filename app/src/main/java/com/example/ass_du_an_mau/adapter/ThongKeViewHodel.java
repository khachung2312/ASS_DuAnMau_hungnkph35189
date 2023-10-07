package com.example.ass_du_an_mau.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_du_an_mau.R;


public class ThongKeViewHodel extends RecyclerView.ViewHolder {
    TextView tv_name_sach, numberSach;
    public ThongKeViewHodel(@NonNull View itemView) {
        super(itemView);
        tv_name_sach = itemView.findViewById(R.id.tv_name_sach_frag_top);
        numberSach = itemView.findViewById(R.id.tv_number_sach_frag_top);
    }
}
