package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView ivAnh;
    EditText etHoten,etTuoi;
    Spinner spQuequan;
    Button btPassdata;
    ArrayList<String> listQuequan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivAnh=(ImageView)findViewById(R.id.ivAnh);
        etHoten=(EditText)findViewById(R.id.etHoten);
        etTuoi=(EditText)findViewById(R.id.etTuoi);
        spQuequan=(Spinner)findViewById(R.id.spQuequan);
        btPassdata=(Button)findViewById(R.id.btPassdata);

        listQuequan=new ArrayList<>();
        listQuequan.add("Hà Nội");
        listQuequan.add("Sài Gòn");
        listQuequan.add("Đà Nẵng");

        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,R.layout.activity_main2,listQuequan);
        spQuequan.setAdapter(adapter);

        ivAnh.setImageResource(R.drawable.mixi);

        btPassdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht=etHoten.getText().toString();
                String tuoi=etTuoi.getText().toString();

                if (ht.length()>0&&tuoi.length()>0&&tuoi.matches("\\d++")){
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("name",etHoten.getText().toString());
                    intent.putExtra("age",etTuoi.getText().toString());
                    intent.putExtra("hometown",spQuequan.getSelectedItem().toString());
                    intent.putExtra("image",R.drawable.mixi);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this, "Không được để trống họ tên, tuổi và tuổi phải là số nguyên dương !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
