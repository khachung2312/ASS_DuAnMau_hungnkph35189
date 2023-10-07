package com.example.ass_du_an_mau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ChangedPackages;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ass_du_an_mau.dao.ThuThuDAO;
import com.example.ass_du_an_mau.fragment.DoanhThuFragment;
import com.example.ass_du_an_mau.fragment.DoiMatKhauFragment;
import com.example.ass_du_an_mau.fragment.LoaiSachFragment;
import com.example.ass_du_an_mau.fragment.PhieuMuonFragment;
import com.example.ass_du_an_mau.fragment.SachFragment;
import com.example.ass_du_an_mau.fragment.ThanhVienFragment;
import com.example.ass_du_an_mau.fragment.ThemNguoiDungFragment;
import com.example.ass_du_an_mau.fragment.TopFragment;
import com.example.ass_du_an_mau.model.ThuThu;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    LayoutInflater layoutInflater;
    View dialogLogOut;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    View mHeaderView;
    TextView tvUsername;
    FragmentManager fragmentManager;

    ThemNguoiDungFragment themNguoiDungFragment;
    DoiMatKhauFragment doiMatKhauFragment;
    DoanhThuFragment doanhThuFragment;
    LoaiSachFragment loaiSachFragment;
    PhieuMuonFragment phieuMuonFragment;
    SachFragment sachFragment;
    ThanhVienFragment thanhVienFragment;
    TopFragment topFragment;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        //anh xa dialog logout
        layoutInflater = getLayoutInflater();
        dialogLogOut = layoutInflater.inflate(R.layout.dialog_logout, null);


        //drawer app
        drawerLayout = findViewById(R.id.drawer_app);
        navigationView = findViewById(R.id.menu_drawer);

        //custom toolbar
        toolbar = findViewById(R.id.toolbar_app);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.icon_menu_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //hien thi user name len header
        mHeaderView = navigationView.getHeaderView(0);
        tvUsername = mHeaderView.findViewById(R.id.tv_username_header);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getIdTT(user);
        String username = thuThu.hoTen;
        tvUsername.setText("Welcome, \n" + username);

        if(!user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.nav_them_nguoi_dung).setVisible(false);
        }

        themNguoiDungFragment = new ThemNguoiDungFragment();
        doiMatKhauFragment = new DoiMatKhauFragment();
        doanhThuFragment = new DoanhThuFragment();
        loaiSachFragment = new LoaiSachFragment();
        phieuMuonFragment = new PhieuMuonFragment();
        sachFragment = new SachFragment();
        thanhVienFragment = new ThanhVienFragment();
        topFragment = new TopFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, phieuMuonFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_phieu_muon) {
                    setTitle("Quản lý phiếu mượn");
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, phieuMuonFragment).commit();
                } else if (itemId == R.id.nav_loai_sach) {
                    setTitle("Quản lý loại sách");
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, loaiSachFragment).commit();
                } else if (itemId == R.id.nav_sach) {
                    setTitle("Quản lý sách");
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, sachFragment).commit();
                } else if (itemId == R.id.nav_thanh_vien) {
                    setTitle("Quản lý thành viên");
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, thanhVienFragment).commit();
                } else if (itemId == R.id.nav_top) {
                    setTitle("10 sách mượn nhiều nhất");

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, topFragment).commit();
                } else if (itemId == R.id.nav_doanh_thu) {
                    setTitle("Doanh thu");

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, doanhThuFragment).commit();
                } else if (itemId == R.id.nav_them_nguoi_dung) {
                    setTitle("Thêm người dùng");

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, themNguoiDungFragment).commit();
                } else if (itemId == R.id.nav_doi_mat_khau) {
                    setTitle("Đổi mật khẩu");

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, doiMatKhauFragment).commit();
                } else if(itemId == R.id.nav_dang_xuat) {
                    if(dialogLogOut.getParent() != null) {
                        ((ViewGroup) dialogLogOut.getParent()).removeAllViews();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(dialogLogOut);

                    Button btn_logout, btn_cancel_logout;
                    btn_logout = dialogLogOut.findViewById(R.id.btn_dialog_log_out);
                    btn_cancel_logout = dialogLogOut.findViewById(R.id.btn_dialog_cancel_log_out);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    btn_logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            MainActivity.this.finish();
                        }
                    });
                    btn_cancel_logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }
}