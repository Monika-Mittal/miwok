package com.example.monikamittal.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    private AudioManager audioManager;
    private MediaPlayer.OnCompletionListener mOnCompleteListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasePlayer();
        }
    };
    private  AudioManager.OnAudioFocusChangeListener onFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                releasePlayer();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK||focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
        }
    };

    private void releasePlayer()
    {
        if(mMediaPlayer!=null)
        {
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.words_list,container,false);
        audioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words=new ArrayList<>();
        words.add(new Word("red","weṭeṭṭi",R.raw.color_red,R.drawable.color_red));
        words.add(new Word("green", "chokokki",R.raw.color_green,R.drawable.color_green));
        words.add(new Word("brown","ṭakaakki",R.raw.color_brown,R.drawable.color_brown));
        words.add(new Word("gray","ṭopoppi",R.raw.color_gray,R.drawable.color_gray));
        words.add(new Word("black","kululli",R.raw.color_black,R.drawable.color_black));
        words.add(new Word("white", "kelelli",R.raw.color_white,R.drawable.color_white));
        words.add(new Word("dusty yellow","ṭopiisә",R.raw.color_dusty_yellow,R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwiiṭә",R.raw.color_mustard_yellow,
                R.drawable.color_mustard_yellow));
        ListView listView=(ListView) rootView.findViewById(R.id.numbers_listview);
        WordAdapter adapter=new WordAdapter(getActivity(),words,R.color.CATEGORY_COLORS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word =words.get(position);
                releasePlayer();
                int result= audioManager.requestAudioFocus(onFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager. AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager. AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mMediaPlayer=MediaPlayer.create(getActivity(),word.getSong());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompleteListener);}
            }
        });
        return rootView;
    }

}
