package com.example.trongnhan.assignmentps04601.ThongKe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.trongnhan.assignmentps04601.DatabaseHandler;
import com.example.trongnhan.assignmentps04601.R;

import java.util.ArrayList;

public class StatsHomNay extends Activity {
    private ThongKeAdapter thongKeAdapter;
    private ArrayList<ThongKe> listchi;
    private ArrayList<ThongKe> listthu;
    DatabaseHandler db;
    TextView tvTitle;
    ListView listView;
    Button btthu,btchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongke);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        listView=(ListView)findViewById(R.id.listViewhienthithongke);
        btthu=(Button)findViewById(R.id.btthu);
        btchi=(Button)findViewById(R.id.btchi);
        db=new DatabaseHandler(getApplicationContext());
        db.open();
        listthu=db.getloggiaodichThongkehomnay("Khoản Thu");
        final ThongKeAdapter tkThu=new ThongKeAdapter(getParent(),R.layout.custom_listview,listthu);
        listView.setAdapter(tkThu);
        listchi=db.getloggiaodichThongkehomnay("Khoản Chi");
        final ThongKeAdapter tkChi = new ThongKeAdapter(getParent(),R.layout.custom_listview,listchi);
        tvTitle.setText("THỐNG KÊ HÔM NAY");
        btchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(tkChi);
            }
        });
        btthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(tkThu);
            }
        });

    }
}
