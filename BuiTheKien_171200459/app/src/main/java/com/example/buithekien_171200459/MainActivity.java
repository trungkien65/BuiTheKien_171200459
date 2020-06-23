package com.example.buithekien_171200459;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ListView listView;
    FloatingActionButton floatingActionButton;
    DBSong dbSong = new DBSong(this);
    ArrayList<Song> songs;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("DBMan");
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        registerForContextMenu(listView);

        dbSong.createDefaultNotesIfNeed();
        songs = dbSong.getAllSongs();
        customAdapter = new CustomAdapter(this, songs);
        listView.setAdapter(customAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        listView.setAdapter(customAdapter);
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int pos = info.position;
        Intent myActivity;
        switch (item.getItemId()) {
            case R.id.sua:
                myActivity = new Intent(MainActivity.this, Main4Activity.class);
                Song song = (Song) listView.getAdapter().getItem(pos);
                String idSong = String.valueOf(song.getId());
                myActivity.putExtra("id", idSong);
                myActivity.putExtra("name_song", song.getName_song());
                myActivity.putExtra("name_singer", song.getName_singer());
                myActivity.putExtra("time", song.getTime());
                startActivityForResult(myActivity, 200);
                break;
            case R.id.xoa:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to delete?");
                builder.setCancelable(false);
                builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Song song = (Song) listView.getAdapter().getItem(pos);
                        dbSong.deleteWord(song);
                        songs = dbSong.getAllSongs();
                        customAdapter = new CustomAdapter(MainActivity.this, songs);
                        listView.setAdapter(customAdapter);
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            songs = dbSong.getAllSongs();
            customAdapter = new CustomAdapter(this, songs);
            listView.setAdapter(customAdapter);
        }
        if (requestCode == 200 && resultCode == RESULT_OK) {
            songs = dbSong.getAllSongs();
            customAdapter = new CustomAdapter(this, songs);
            listView.setAdapter(customAdapter);
        }
    }
}
