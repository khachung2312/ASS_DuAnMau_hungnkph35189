package com.example.ass_du_an_mau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.ass_du_an_mau.R;
import com.example.ass_du_an_mau.model.Sach;

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> arrSach;
    TextView tv_name_sach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> arrSach) {
        super(context, 0, arrSach);
        this.arrSach = arrSach;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final  Sach sach = arrSach.get(position);
        if(sach != null) {
            tv_name_sach = view.findViewById(R.id.tv_name_sach_spinner_frag_pm);
            tv_name_sach.setText(sach.tenSach);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_spinner_drop, null);
        }
        final  Sach sach = arrSach.get(position);
        if(sach != null) {
            tv_name_sach = view.findViewById(R.id.tv_name_sach_spinner_frag_pm_drop);
            tv_name_sach.setText(sach.tenSach);
        }
        return view;
    }
}
