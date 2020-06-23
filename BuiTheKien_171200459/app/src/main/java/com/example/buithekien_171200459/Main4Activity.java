package com.example.buithekien_171200459;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    EditText editTextID;
    EditText editTextSong;
    EditText editTextArtist;
    EditText editTextTime;
    Button buttonEdit;
    Button buttonBack;
    DBSong dbSong = new DBSong(this);
    ArrayList<Song> songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setTitle("DBMan");
        songs = dbSong.getAllSongs();
        editTextID = findViewById(R.id.editText2);
        editTextSong = findViewById(R.id.editText3);
        editTextArtist = findViewById(R.id.editText4);
        editTextTime = findViewById(R.id.editText5);
        buttonEdit = findViewById(R.id.button);
        buttonBack = findViewById(R.id.button2);
        Intent intent = getIntent();
        editTextID.setText(intent.getStringExtra("id"));
        editTextSong.setText(intent.getStringExtra("name_song"));
        editTextArtist.setText(intent.getStringExtra("name_singer"));
        editTextTime.setText(intent.getStringExtra("time"));
        disableEditText();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSong.getText().toString().trim() != "" && editTextArtist.getText().toString().trim() != "" && editTextTime.getText().toString().trim() != "") {
                    long x = dbSong.Update(Integer.parseInt(String.valueOf(editTextID.getText())), editTextSong.getText().toString(), editTextArtist.getText().toString(), editTextTime.getText().toString());
                    if (x == 0) {
                        Toast.makeText(Main4Activity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main4Activity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                } else {
                    Toast.makeText(Main4Activity.this, "Hãy nhập đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void disableEditText() {
        boolean check = false;
        editTextID.setInputType(InputType.TYPE_NULL);
        editTextID.setFocusable(check);
    }

}
