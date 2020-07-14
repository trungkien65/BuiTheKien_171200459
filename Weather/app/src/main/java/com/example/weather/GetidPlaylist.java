package com.example.weather;

import android.util.Log;

import static android.content.ContentValues.TAG;


public class GetidPlaylist {
    public static String IdPlaylist(String data){
        if(MainActivity.GETNAMEPLAYLIST.equals("Hanoi")){
            data = "PL7eynUf97vd9SwKhH-xvUdtfb18w74ae9";
            Log.e("ketqua",data);
        }else{
            data = "PL7eynUf97vd_pzjDMYBpyPEEfz2k1mHIB";
            Log.e("ketqua",data);
        }
        return data;
    }
}
