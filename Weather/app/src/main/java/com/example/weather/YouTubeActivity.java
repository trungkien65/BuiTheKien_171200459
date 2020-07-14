package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YouTubeActivity extends AppCompatActivity {


    public  static  String API_KEY = "AIzaSyC91JqBHe6dpXJ15fwsXIHxUw9HXctqg0c";


    ListView lvVideo;
    ArrayList<VideoYouTube> arrayVideo;
    VideoYouTubeAdapter adapter;
    String data = "";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);

        lvVideo = (ListView) findViewById(R.id.listviewVideo);
        arrayVideo = new ArrayList<>();

        adapter = new VideoYouTubeAdapter(this,R.layout.row_video_youtube,arrayVideo);
        lvVideo.setAdapter(adapter);

        //GetIdPlaylisṭ̣̣̣();
        String id = MainActivity.GETNAMEPLAYLIST;
        switch (id){
            case "Hanoi":
                data = "PL7eynUf97vd9SwKhH-xvUdtfb18w74ae9";
                GetJsonYoutube(data);
                break;
            case "Ho Chi Minh City":
                data = "PL7eynUf97vd_pzjDMYBpyPEEfz2k1mHIB";
                GetJsonYoutube(data);
                break;
            case "Haiphong":
                data = "PL7eynUf97vd8cr8yAxyCWOBdrzPxKkMwk";
                GetJsonYoutube(data);
                break;
            case "Paris":
                data = "PL7eynUf97vd-cQAJ7nYNT2bhfNNlP9xV3";
                GetJsonYoutube(data);
                break;
        }

/*        if(id.equals("Hanoi")){

        }else if(id.equals()){

        }*/


        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(YouTubeActivity.this,PlayVideoActivity.class);
                intent.putExtra("idVideoYouTube" , arrayVideo.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }

    private void GetJsonYoutube(final String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String urlid = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + data + "&key=AIzaSyC91JqBHe6dpXJ15fwsXIHxUw9HXctqg0c&maxResults=50";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlid, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title =""; String url = ""; String IdVideo= "";

                            for (int i = 1 ; i < jsonItems.length() ; i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);

                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");

                                title = jsonSnippet.getString("title");

                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                url = jsonMedium.getString("url");

                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                                IdVideo = jsonResourceID.getString("videoId");

                                arrayVideo.add(new VideoYouTube(title, url, IdVideo));
                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(YouTubeActivity.this,"error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}

