package com.example.monikamittal.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;




public class WordAdapter extends ArrayAdapter<Word> {
  private int background_color;
    public WordAdapter(Context context, ArrayList<Word> objects, int color) {
        super(context, 0, objects);
        background_color=color;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Word worditem=getItem(position);
        View listView=convertView;
        if(listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView defaultWord=(TextView) listView.findViewById(R.id.default_word);
        TextView miwokWord=(TextView) listView.findViewById(R.id.miwok_word);
        defaultWord.setText(worditem.getDefaultWord());
        miwokWord.setText(worditem.getMiwokWord());
        ImageView image=(ImageView) listView.findViewById(R.id.imageView);
        if(worditem.hasImage())
        {
            image.setImageResource(worditem.getImageViewId());
            image.setVisibility(View.VISIBLE);
        }
        else {
            image.setVisibility(View.GONE);
        }
        View container=listView.findViewById(R.id.container);
        int color= ContextCompat.getColor(getContext(),background_color);
        container.setBackgroundColor(color);
        return listView;
    }
};