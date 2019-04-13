package com.example.trongnhan.assignmentps04601;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    String default_pin="1234";//khai báo mk mặc định 1234
    EditText etOldPin,etNewPin,etRePin;
    Button btnCPin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;//khởi tạo đối tượng editor để mỏ file share.. và đưa dữ liệu vào

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etLogin=(EditText)findViewById(R.id.etLogin);
        Button btnLogin=(Button)findViewById(R.id.btnLogin);
        Button btnReset=(Button)findViewById(R.id.btnReset);//mode-private : chỉ cho ứng dụng hiện tại truy cập vào file share preference này thôi mà không một ứng dụng nào có quyền truy cập vào được
        preferences=getSharedPreferences("account",MODE_PRIVATE);//khởi tạo đối tượng account thuôcj kiểu sharepreference.
        //bắt sự kiện cho btnLogin
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storedPin=preferences.getString("pin","1234");

                if(etLogin.getText().toString().equals(storedPin)){
                    Intent i =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    etLogin.setError("Sai mật khẩu");
                }

            }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPin();
            }
        });

        etLogin.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String storedPin=preferences.getString("pin","1234");

                    if(etLogin.getText().toString().equals(storedPin)){
                        Intent i =new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        etLogin.setError("Sai mật khẩu");
                    }
                    return true;
                }
                return false;
            }
        });

    }
    private void showDialogPin() {
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        View dialogView=inflater.inflate(R.layout.change_pin, null);
        alertBuilder.setView(dialogView);
        final AlertDialog alertDialog=alertBuilder.create();//tạo 1 AlertDialog với các đối tượng
        alertDialog.setCancelable(true);//đặt hộp thoại xem có hủy được hay ko
        alertDialog.show();
        etOldPin=(EditText)alertDialog.findViewById(R.id.etold);
        etNewPin=(EditText)alertDialog.findViewById(R.id.etnew);
        etRePin=(EditText)alertDialog.findViewById(R.id.etrepin);
        btnCPin=(Button)alertDialog.findViewById(R.id.savechange);

        btnCPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOldPin=etOldPin.getText().toString();
                String strNewPin=etNewPin.getText().toString();
                String strRePin=etRePin.getText().toString();

                editor=preferences.edit();
                String storedPin=preferences.getString("pin","1234");
                if (strOldPin.equals("") || strNewPin.equals("") || strRePin.equals("")) {
                    Toast.makeText(LoginActivity.this, "(*)Các mục đang trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (strOldPin.equals(storedPin)) {
                        if (strNewPin.equals(strRePin)) {
                            editor.putString("pin",strNewPin);
                            editor.commit();//thay đổi tùy chọn từ SharedPreferences chỉnh sửa cho đối tượng mà nó đang chỉnh sửa
                            editor.clear();//dánh dấu trong trình chỉnh sửa để xóa tát cả các giá trị khỏi các tùy chọn
                            Toast.makeText(LoginActivity.this, "Đã lưu mật khẩu mới", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(LoginActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


}

