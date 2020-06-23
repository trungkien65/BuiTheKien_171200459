package com.example.buithekien_171200459;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    EditText editTextID;
    EditText editTextSong;
    EditText editTextArtist;
    EditText editTextTime;
    Button buttonAdd;
    Button buttonBack;
    DBSong dbSong = new DBSong(this);
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("DBMan");
        songs = dbSong.getAllSongs();
        editTextID = findViewById(R.id.editText2);
        editTextSong = findViewById(R.id.editText3);
        editTextArtist = findViewById(R.id.editText4);
        editTextTime = findViewById(R.id.editText5);
        buttonAdd = findViewById(R.id.button);
        buttonBack = findViewById(R.id.button2);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextID.getText().toString().trim().equals("") && !editTextSong.getText().toString().trim().equals("")
                        && !editTextArtist.getText().toString().trim().equals("") && !editTextTime.getText().toString().trim().equals("")) {
                    int id = Integer.parseInt(editTextID.getText().toString().trim());
                    boolean ktra_id = true;
                    for (int i = 0; i < songs.size(); i++) {
                        if (songs.get(i).getId() == id) {
                            ktra_id = false;
                            break;
                        }
                    }
                    if (ktra_id == false) {
                        Toast.makeText(Main3Activity.this, "Mã đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        dbSong.addSong(new Song(id, editTextSong.getText().toString().trim(), editTextArtist.getText().toString().trim(), editTextTime.getText().toString().trim()));
                        setResult(RESULT_OK);
                        finish();
                    }
                } else {
                    Toast.makeText(Main3Activity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
