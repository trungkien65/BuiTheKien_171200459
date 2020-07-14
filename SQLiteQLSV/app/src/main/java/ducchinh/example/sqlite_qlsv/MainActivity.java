package ducchinh.example.sqlite_qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextID, editTextHoTen, editTextNamSinh;
    Button buttonInsert , buttonDelete, buttonUpdate, buttonLoadAll;
    ListView listViewSinhVien;
    DBHelper dbHelper;
    private String getValues(EditText editText ){
        return editText.getText().toString().trim();

    }
    protected void  onStart(){
        super.onStart();
        dbHelper.OpenDB();
    }

    protected void  onStop(){
        super.onStop();
        dbHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextHoTen = (EditText) findViewById(R.id.EditTextHoTen);
        editTextID = (EditText) findViewById(R.id.EditTextMaSV);
        editTextNamSinh = (EditText) findViewById(R.id.EditTextNamSinh);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonLoadAll = (Button) findViewById(R.id.buttonLoad);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        listViewSinhVien = (ListView) findViewById(R.id.ListViewSinhVien);
        dbHelper = new DBHelper(MainActivity.this);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resoulAdd = dbHelper.Insert(Integer.parseInt(getValues(editTextID)),
                        getValues(editTextHoTen) , Integer.parseInt(getValues(editTextNamSinh)));
                if (resoulAdd == -1 )
                {
                    Toast.makeText(MainActivity.this,"Error ", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Successful",Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resoulUpdate = dbHelper.Update(Integer.parseInt(getValues(editTextID)),
                        getValues(editTextHoTen) ,  Integer.parseInt(getValues(editTextNamSinh)));

                if (resoulUpdate ==0 ){
                    Toast.makeText(MainActivity.this,"Error" , Toast.LENGTH_LONG).show();
                }
                else if (resoulUpdate == 1){
                    Toast.makeText(MainActivity.this, "Succressfull update" ,Toast.LENGTH_LONG ).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Error, multiple records update" , Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resoulDetele = dbHelper.Delete(Integer.parseInt(getValues(editTextID)));
                if (resoulDetele == 0){
                    Toast.makeText(MainActivity.this,"error", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"SuccressFull delate DATA", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonLoadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getAllRecord();
                SinhVienCursorAdapter adapter = new SinhVienCursorAdapter(MainActivity.this,
                        R.layout.activity_dong_sinh_vien, cursor);
                listViewSinhVien.setAdapter(adapter);
            }
        });
    }
}
