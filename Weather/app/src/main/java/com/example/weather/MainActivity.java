package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText edtSearch;
    Button btnSearch,btnChangeActivity,btntest;
    TextView txtName ,txtCoutry,txtTemp,txtStatus,txtDoAm,txtGio,txtMay,txtDay;
    ImageView imgIcon;
    FloatingActionButton fab, fab_nexthour,fab_youtube,fab_mail;
    boolean anhien = false;
    String City = "";
    public static String GETNAMEPLAYLIST;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        GetCurrentWeatherData("hanoi");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city =edtSearch.getText().toString();
                if (city.equals("")){
                    City = "hanoi";
                    GetCurrentWeatherData(City);
                }else {
                    City = city;
                    GetCurrentWeatherData(City);
                }
                GetCurrentWeatherData(city);
            }
        });
       /* btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        /*btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,YouTubeActivity.class);
                startActivity(intent);
            }
        });*/
        fab_nexthour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                String city =edtSearch.getText().toString();
                intent.putExtra("name",city);
                startActivity(intent);
            }
        });
        fab_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,YouTubeActivity.class);
                startActivity(intent);
            }
        });
        fab_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.vn/"));
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anhien == false){
                    hien();
                    anhien= true;
                }else {
                    An();
                    anhien = false;
                }
            }
        });
    }

    public void  GetCurrentWeatherData(final String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=b38e22c62b237519f11cd1e1d5388498";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            GETNAMEPLAYLIST = name;
                            txtName.setText("City : "+GETNAMEPLAYLIST);

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy HH:mm:ss");
                            String Day = simpleDateFormat.format(date);

                            txtDay.setText(Day);
                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon= jsonObjectWeather.getString("icon");

                            Picasso.get().load("http://openweathermap.org/img/wn/"+icon+".png").into(imgIcon);
                            txtStatus.setText(status);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjectMain.getString("temp");
                            String doam = jsonObjectMain.getString("humidity");

                            Double a = Double.valueOf(nhietdo);
                            String Nhietdo = String.valueOf(a.intValue());

                            txtTemp.setText(nhietdo+"°C");
                            txtDoAm.setText(doam+"%");

                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            txtGio.setText(gio+"m/s");

                            JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectClouds.getString("all");
                            txtMay.setText(may+"%");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            txtCoutry.setText("Country :"+country);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }


    private void hien(){
        fab_youtube.show();
        fab_nexthour.show();
        fab_mail.show();
    }

    private void An(){
        fab_youtube.hide();
        fab_nexthour.hide();
        fab_mail.hide();
    }

    private void Anhxa() {
        edtSearch = (EditText) findViewById(R.id.edittextSearch);
        btnSearch = (Button) findViewById(R.id.buttonSearch);
       // btnChangeActivity = (Button) findViewById(R.id.buttonChangeActivity);
        txtName = (TextView) findViewById(R.id.textviewName);
        txtCoutry = (TextView) findViewById(R.id.textviewCountry);
        txtTemp = (TextView) findViewById(R.id.textviewTemp);
        txtStatus = (TextView) findViewById(R.id.textviewStatus);
        txtDoAm = (TextView) findViewById(R.id.textviewDoAm);
        txtGio = (TextView) findViewById(R.id.textviewGio);
        txtMay = (TextView) findViewById(R.id.textviewMay);
        txtDay = (TextView) findViewById(R.id.textviewDay);
        imgIcon = (ImageView) findViewById(R.id.imageIcon);
       // btntest = (Button) findViewById(R.id.btntest);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_nexthour = (FloatingActionButton) findViewById(R.id.fab_nexthour);
        fab_youtube = (FloatingActionButton) findViewById(R.id.fab_youtube);
        fab_mail = (FloatingActionButton) findViewById(R.id.fab_mail);
    }
}
