package com.example.ass_du_an_mau.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.material3.SnackbarKt;
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

public class ThemNguoiDungFragment extends Fragment {
    ThuThuDAO thuThuDAO;
    Button btn_add_user;
    EditText ed_username, ed_name, ed_password, ed_repassword;
    TextInputLayout input_username, input_name, input_pass, input_repass;
    TextView show_err;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        thuThuDAO = new ThuThuDAO(getActivity());
        show_err = view.findViewById(R.id.check_trung);
        input_username = view.findViewById(R.id.input_username_add_user);
        input_name = view.findViewById(R.id.input_name_add_user);
        input_pass = view.findViewById(R.id.input_pass_add_user);
        input_repass = view.findViewById(R.id.input_repass_add_user);
        ed_username = view.findViewById(R.id.ed_username_add_user);
        ed_name = view.findViewById(R.id.ed_name_add_user);
        ed_password = view.findViewById(R.id.ed_password_add_user);
        ed_repassword = view.findViewById(R.id.ed_repassword_add_user);
        btn_add_user = view.findViewById(R.id.btn_add_user);

        btn_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT = ed_username.getText().toString();
                thuThu.hoTen = ed_name.getText().toString();
                thuThu.matKhau = ed_password.getText().toString();
                if(validate() > 0) {
                    if(thuThuDAO.insertThuThu(thuThu) > 0) {
                        Snackbar.make(view, "Thêm thủ thư thành công!", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.primary_color))
                                .show();
                        ed_username.setText("");
                        ed_name.setText("");
                        ed_password.setText("");
                        ed_repassword.setText("");
                    } else {
                        Snackbar.make(view, "Thêm thủ thư thất bại!", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.red))
                                .show();
                    }
                }
            }
        });
    }

    public int validate() {
        int check = 1;
        if(ed_username.getText().length() == 0) {
            input_username.setError("Vui lòng nhập tài khoản!");
            check = -1;
        } else if(ed_username.getText().length() < 3) {
            input_username.setError("Tài khoản phải nhiều hơn 3 kí tự!");
            check = -1;
        } else if(ed_username.getText().length() > 16) {
            input_username.setError("Tài khoản không được nhiều hơn 16 kí tự!");
            check = -1;
        } else {
            input_username.setError("");
        }

        if(ed_name.getText().length() == 0) {
            input_name.setError("Vui lòng nhập họ tên!");
            check = -1;
        } else if(ed_name.getText().length() < 3) {
            input_name.setError("Họ tên phải nhiều hơn 3 kí tự!");
            check = -1;
        } else if(ed_name.getText().length() > 16) {
            input_name.setError("Họ tên không được nhiều hơn 16 kí tự!");
            check = -1;
        } else {
            input_name.setError("");
        }

        if(ed_password.getText().length() == 0) {
            input_pass.setError("Vui lòng nhập mật khẩu!");
            check = -1;
        } else if(ed_password.getText().length() < 3) {
            input_pass.setError("Mật khẩu phải nhiều hơn 3 kí tự!");
            check = -1;
        } else if(ed_password.getText().length() > 16) {
            input_pass.setError("Mật khẩu không được nhiều hơn 16 kí tự!");
            check = -1;
        } else {
            input_pass.setError("");
        }

        if(ed_repassword.getText().length() == 0) {
            input_repass.setError("Vui lòng nhập lại mật khẩu!");
            check = -1;
        } else {
            input_repass.setError("");
            String pass = ed_password.getText().toString();
            String repass = ed_repassword.getText().toString();
            if(!pass.equals(repass)) {
                input_repass.setError("Mật khẩu nhập lại không trùng khớp!");
                check = -1;
            } else {
                input_repass.setError("");
            }
        }
        return check;
    }

}