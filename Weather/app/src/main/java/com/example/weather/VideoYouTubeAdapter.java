package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoYouTubeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VideoYouTube> videoYouTubeList;

    public VideoYouTubeAdapter(Context context, int layout, List<VideoYouTube> videoYouTubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYouTubeList = videoYouTubeList;
    }

    @Override
    public int getCount() {
        return videoYouTubeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgThumbnail;
        TextView txtTitle;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null ){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textviewTitle);
            holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.imageviewThumbnail);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoYouTube videoYouTube = videoYouTubeList.get(position);

        holder.txtTitle.setText(videoYouTube.getTitle());
        Picasso.get().load(videoYouTube.getThumbnail()).into(holder.imgThumbnail);

        return convertView;
    }
}
