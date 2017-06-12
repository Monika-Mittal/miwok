package com.example.monikamittal.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.words_list,container,false);
        //getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        audioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words=new ArrayList<>();
        words.add(new Word("one","lutti",R.raw.number_one,R.drawable.number_one));
        words.add(new Word("two","otiiko",R.raw.number_two,R.drawable.number_two));
        words.add(new Word("three","tolookosu",R.raw.number_three,R.drawable.number_three));
        words.add(new Word("four","oyyisa",R.raw.number_four,R.drawable.number_four));
        words.add(new Word("five","massokka",R.raw.number_five,R.drawable.number_five));
        words.add(new Word("six","temmokka",R.raw.number_six,R.drawable.number_six));
        words.add(new Word("seven","kenekaku",R.raw.number_seven,R.drawable.number_seven));
        words.add(new Word("eight","kawinta",R.raw.number_eight,R.drawable.number_eight));
        words.add(new Word("nine","wo’e",R.raw.number_nine,R.drawable.number_nine));
        words.add(new Word("ten","na’aacha",R.raw.number_ten,R.drawable.number_ten));
        ListView listView=(ListView) rootView.findViewById(R.id.numbers_listview);
        WordAdapter adapter=new WordAdapter(getActivity(),words,R.color.CATEGORY_NUMBERS);
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
