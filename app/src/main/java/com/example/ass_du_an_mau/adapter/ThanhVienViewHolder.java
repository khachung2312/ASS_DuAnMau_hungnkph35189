package com.example.ass_du_an_mau.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_du_an_mau.R;

public class ThanhVienViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name_tv, tv_bith_date;
    ImageView img_delete_tv;
    View item_tv;
    public ThanhVienViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name_tv = itemView.findViewById(R.id.tv_name_tv_frag_tv);
        tv_bith_date = itemView.findViewById(R.id.tv_bird_tv_frag_tv);
        img_delete_tv = itemView.findViewById(R.id.img_delete_thanh_vien);
        item_tv = itemView.findViewById(R.id.item_tv_frag_tv);
    }
}
