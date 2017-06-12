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
public class FamilyFragment extends Fragment {
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
        words.add(new Word("father","әpә",R.raw.family_father,R.drawable.family_father));
        words.add(new Word("mother","әṭa",R.raw.family_mother,R.drawable.family_mother));
        words.add(new Word("son","angsi",R.raw.family_son,R.drawable.family_son));
        words.add(new Word("daughter","tune",R.raw.family_daughter,R.drawable.family_daughter));
        words.add(new Word("older brother","taachi",R.raw.family_older_brother,R.drawable.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.raw.family_younger_brother,R.drawable.family_younger_brother));
        words.add(new Word("older sister","teṭe",R.raw.number_three,R.drawable.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.raw.family_older_sister,R.drawable.family_younger_sister));
        words.add(new Word("grandmother","ama",R.raw.family_grandmother,R.drawable.family_grandmother));
        words.add(new Word("grandfather","paapa",R.raw.family_grandfather,R.drawable.family_grandfather));
        ListView listView=(ListView) rootView.findViewById(R.id.numbers_listview);
        WordAdapter adapter=new WordAdapter(getActivity(),words,R.color.CATEGORY_FAMILY);
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
