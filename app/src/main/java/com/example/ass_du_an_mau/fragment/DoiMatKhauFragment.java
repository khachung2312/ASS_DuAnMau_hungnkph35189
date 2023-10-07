package com.example.ass_du_an_mau.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ass_du_an_mau.R;
import com.example.ass_du_an_mau.dao.ThuThuDAO;
import com.example.ass_du_an_mau.model.ThuThu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class DoiMatKhauFragment extends Fragment {
    EditText ed_current_password, ed_new_password, ed_repassword;
    Button btn_change;
    ThuThuDAO thuThuDAO;
    TextView show_err;
    TextInputLayout input_current_password, input_new_password, input_repassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ed_current_password = view.findViewById(R.id.ed_current_password);
        ed_new_password = view.findViewById(R.id.ed_new_password);
        ed_repassword = view.findViewById(R.id.ed_repassword);
        btn_change = view.findViewById(R.id.btn_change_password);
        show_err = view.findViewById(R.id.check_trung_change_password);
        input_current_password = view.findViewById(R.id.input_current_password);
        input_new_password = view.findViewById(R.id.input_new_password);
        input_repassword = view.findViewById(R.id.input_repassword);
        thuThuDAO = new ThuThuDAO(getActivity());

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = preferences.getString("USERNAME", "");

                if(validate() > 0) {
                    ThuThu thuThu = thuThuDAO.getIdTT(user);
                    thuThu.matKhau = ed_new_password.getText().toString();
                    thuThuDAO.updateThuThu(thuThu);
                    if(thuThuDAO.updateThuThu(thuThu) > 0) {
                        Snackbar.make(view, "Thay đổi mật khẩu thành công.", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.primary_color))
                                .show();
                        ed_current_password.setText("");
                        ed_new_password.setText("");
                        ed_repassword.setText("");
                    } else {
                        Snackbar.make(view, "Thay đổi mật khẩu thất bại!", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.red))
                                .show();
                    }
                }
            }
        });
    }

    public int validate() {
        int check = 1;
        if(ed_current_password.getText().length() == 0) {
            input_current_password.setError("Vui lòng nhập mật khẩu!");
            check = -1;
        } else {
            input_current_password.setError("");
        }
        if(ed_new_password.getText().length() == 0) {
            input_new_password.setError("Vui lòng nhập mật khẩu mới!");
            check = -1;
        } else if(ed_new_password.getText().length() < 3) {
            input_new_password.setError("Mật khẩu mới phải nhiều hơn 3 kí tự!");
            check = -1;
        } else if(ed_new_password.getText().length() > 16) {
            input_new_password.setError("Mật khẩu mới phải nhỏ hơn 16 kí tự!");
            check = -1;
        } else {
            input_new_password.setError("");
        }
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String currentPass = preferences.getString("PASSWORD","");
        String newPass = ed_new_password.getText().toString();
        String rePass = ed_repassword.getText().toString();

        if(!currentPass.equals(ed_current_password.getText().toString())) {
            input_current_password.setError("Mật khẩu cũ sai!");
            check = -1;
        }
        if(ed_repassword.getText().length() == 0) {
            input_repassword.setError("Mật khẩu mới nhập lại trống!");
            check = -1;
        } else if(!newPass.equals(rePass)) {
            input_repassword.setError("Mật khẩu không trùng khớp!");
            check = -1;
        } else {
            input_repassword.setError("");
        }
        return check;
    }
}