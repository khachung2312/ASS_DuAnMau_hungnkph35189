package com.example.ass_du_an_mau.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_du_an_mau.R;
import com.example.ass_du_an_mau.dao.PhieuMuonDAO;
import com.example.ass_du_an_mau.dao.ThanhVienDAO;
import com.example.ass_du_an_mau.fragment.ThanhVienFragment;
import com.example.ass_du_an_mau.model.PhieuMuon;
import com.example.ass_du_an_mau.model.ThanhVien;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienViewHolder> {
    ArrayList<ThanhVien> arrThanhVien = new ArrayList<>();
    ThanhVienDAO thanhVienDAO;
    Context context;
    LayoutInflater inflater;
    View viewDeleteThanhVien, viewUpdateThanhVien;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> arrPM = new ArrayList<>();
    ThanhVienFragment thanhVienFragment;
    View viewAlert;
    public ThanhVienAdapter(Context context, ThanhVienFragment thanhVienFragment, ArrayList<ThanhVien> arrThanhVien) {
        this.context = context;
        this.arrThanhVien = arrThanhVien;
        this.thanhVienFragment = thanhVienFragment;
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.item_thanh_vien, parent, false);
        ThanhVienViewHolder thanhVienViewHolder = new ThanhVienViewHolder(viewItem);
        viewAlert = parent;
        return thanhVienViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        thanhVienFragment = new ThanhVienFragment();
        thanhVienDAO = new ThanhVienDAO(context);
        ThanhVien thanhVien = arrThanhVien.get(position);
        holder.tv_name_tv.setText(thanhVien.hoTen);
        holder.tv_bith_date.setText(thanhVien.namSinh);

        inflater = LayoutInflater.from(context);
        viewDeleteThanhVien = inflater.inflate(R.layout.dialog_delete_tv, null);
        viewUpdateThanhVien = inflater.inflate(R.layout.dialog_update_tv, null);

        holder.img_delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewDeleteThanhVien.getParent() != null) {
                    ((ViewGroup)viewDeleteThanhVien.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(viewDeleteThanhVien);

                Button btn_delete_tv, btn_cancel_delete_tv;
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                btn_delete_tv = viewDeleteThanhVien.findViewById(R.id.btn_dialog_delete_tv);
                btn_cancel_delete_tv = viewDeleteThanhVien.findViewById(R.id.btn_dialog_cancel_delete_tv);
                btn_cancel_delete_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btn_delete_tv.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        int check = 1;
                        phieuMuonDAO = new PhieuMuonDAO(context);
                        arrPM = (ArrayList<PhieuMuon>) phieuMuonDAO.getAllPhieuMuon();
                        for(int i = 0; i < arrPM.size(); i ++) {
                            if(arrPM.get(i).maTV == thanhVien.maTV) {
                                check = -1;
                            }
                        }

                        if(check > 0) {
                            thanhVienDAO.deleteThanhVien(thanhVien.maTV + "");
                            arrThanhVien.remove(thanhVien);
                            notifyDataSetChanged();
                            alertDialog.dismiss();
                            Snackbar.make(viewAlert, "Xoá thành viên thành công.", Snackbar.LENGTH_LONG)
                                    .setBackgroundTint(R.color.primary_color)
                                    .show();
                        } else {
                            alertDialog.dismiss();
                            Snackbar.make(viewAlert, "Không thể xoá thành viên do đang mượn sách!", Snackbar.LENGTH_LONG)
                                    .setBackgroundTint(R.color.red)
                                    .setAction("Đóng", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        }
                                    })
                                    .show();
                        }
                    }
                });
            }
        });
        holder.item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewUpdateThanhVien.getParent() != null) {
                    ((ViewGroup)viewUpdateThanhVien.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(viewUpdateThanhVien);

                EditText ed_update_name_tv, ed_update_bith_date;
                Button btn_update_tv, btn_cancel_update;
                TextInputLayout layout_ed_bith, input_name_tv;
                input_name_tv = viewUpdateThanhVien.findViewById(R.id.input_name_tv_update);
                layout_ed_bith = viewUpdateThanhVien.findViewById(R.id.input_update_bith_date_tv);
                ed_update_name_tv = viewUpdateThanhVien.findViewById(R.id.ed_update_name_tv);
                ed_update_bith_date = viewUpdateThanhVien.findViewById(R.id.ed_update_bith_date_tv);
                btn_update_tv = viewUpdateThanhVien.findViewById(R.id.btn_dialog_update_tv);
                btn_cancel_update = viewUpdateThanhVien.findViewById(R.id.btn_dialog_cancle_update_tv);
                ed_update_name_tv.setText(thanhVien.hoTen);
                ed_update_bith_date.setText(thanhVien.namSinh);
                
                layout_ed_bith.setError("");
                input_name_tv.setError("");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                
                ed_update_bith_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                ed_update_bith_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                        datePickerDialog.show();
                    }
                });
                
                btn_update_tv.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        int check = 1; 
                        if(ed_update_name_tv.getText().toString().isEmpty()) {
                            input_name_tv.setError("Vui lòng nhập tên thành viên!");
                            check = -1;
                        } else if(ed_update_name_tv.getText().toString().length() < 4) {
                            input_name_tv.setError("Tên thành phải dài hơn 3 kí tự!");
                            check = -1;
                        } else if(ed_update_name_tv.getText().toString().length() > 16) {
                            input_name_tv.setError("Tên thành viên không được dài quá 16 kí tự!");
                            check = -1;
                        } else {
                            input_name_tv.setError("");
                        }
                        
                        if(ed_update_bith_date.getText().toString().isEmpty()) {
                            layout_ed_bith.setError("Vui lòng nhập ngày sinh!");
                            check = -1;
                        } else {
                            layout_ed_bith.setError("");
                        }
                        
                        if(check > 0) {
                            ThanhVien thanhVien1 = new ThanhVien();
                            thanhVien1.maTV = thanhVien.maTV;
                            thanhVien1.hoTen = ed_update_name_tv.getText().toString();
                            thanhVien1.namSinh = ed_update_bith_date.getText().toString();
                            thanhVienDAO.updateThanhVien(thanhVien1);
                            notifyDataSetChanged();
                            alertDialog.dismiss();
                            layout_ed_bith.setError("");
                            input_name_tv.setError("");
                            Snackbar.make(viewAlert, "Cập nhật thông tin thành công.", Snackbar.LENGTH_LONG)
                                    .setBackgroundTint(R.color.primary_color)
                                    .show();
                        }
                    }
                });

                btn_cancel_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrThanhVien.size();
    }
}
