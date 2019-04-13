package com.example.trongnhan.assignmentps04601;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.trongnhan.assignmentps04601.ThongKe.StatsActivity;

public class MainActivity extends TabActivity {
        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            SharedPreferences preferences=getSharedPreferences("account",MODE_PRIVATE);
            String storedPin=preferences.getString("pin","1234");
            if(storedPin.equals("1234")){
                Toast.makeText(MainActivity.this,"Vui lòng thay đổi mật khẩu để bảo mật",Toast.LENGTH_SHORT).show();
            }
            final TabHost tabHost = getTabHost();
            TabHost.TabSpec thuchi = tabHost.newTabSpec("Thu Chi");//tạo một tabspec mới sử dụng tabhost
            thuchi.setIndicator("Thu Chi", getResources().getDrawable(R.mipmap.ic_launcher));//đặt "Thu chi " làm chỉ số
           //khi nhấn vào tab thu chi sẽ chuyển đến trang thuchimainactivity
            Intent i = new Intent(MainActivity.this, ThuChiMainActivity.class);
            thuchi.setContent(i);//
            TabHost.TabSpec thongke = tabHost.newTabSpec("Thống Kê");
            thongke.setIndicator("Thống Kê", getResources().getDrawable(R.mipmap.ic_launcher));
            Intent o = new Intent(this, StatsActivity.class);
            thongke.setContent(o);
            TabHost.TabSpec caidat = tabHost.newTabSpec("Cài Đặt");
            caidat.setIndicator("Cài Đặt", getResources().getDrawable(R.mipmap.ic_launcher));
            Intent p = new Intent(this, CaiDatActivity.class);
            caidat.setContent(p);
//moi
         //   TabHost.TabSpec quanlythu = tabHost.newTabSpec("Quản lý thu");
          //  caidat.setIndicator("Quản lý thu", getResources().getDrawable(R.mipmap.ic_launcher));
          //  Intent ql = new Intent(this, QuanLyThu.class);
          //  quanlythu.setContent(ql);
          //  tabHost.addTab(quanlythu);

            tabHost.addTab(thuchi);//thêm 1 tab trong tab widget
            tabHost.addTab(thongke);
            tabHost.addTab(caidat);
            tabHost.setCurrentTab(0);
        }
}

