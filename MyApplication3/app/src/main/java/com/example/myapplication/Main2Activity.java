package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView tvHoten,tvTuoi,tvQuequan;
    Button btBack;
    ImageView ivAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ivAnh=(ImageView)findViewById(R.id.ivAnh);
        tvHoten=(TextView) findViewById(R.id.tvHoten);
        tvTuoi=(TextView) findViewById(R.id.tvTuoi);
        tvQuequan=(TextView) findViewById(R.id.tvQuequan);
        btBack=(Button)findViewById(R.id.btBack);

        Bundle tuis=getIntent().getExtras();

        if (tuis!=null){
            tvHoten.setText(tuis.getString("name"));
            tvTuoi.setText(tuis.getString("age"));
            tvQuequan.setText(tuis.getString("hometown"));
            ivAnh.setImageResource(tuis.getInt("image"));
        }

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
