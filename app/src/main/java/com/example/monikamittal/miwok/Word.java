package com.example.monikamittal.miwok;

public class Word {
    private String defaultWord;
    private String miwokWord;
    private int imageViewId=HAS_IMAGE;
    private int song;
    private static final int HAS_IMAGE=-1;
    Word(String defaultWord,String miwokWord,int song)
    {
        this.defaultWord=defaultWord;
        this.miwokWord=miwokWord;
        this.song=song;
    }
    Word(String defaultWord,String miwokWord,int song,int imageView)
    {
        this(defaultWord,miwokWord,song);
        imageViewId=imageView;
    }
    String getDefaultWord()
    {
        return defaultWord;
    }
    String getMiwokWord()
    {
        return miwokWord;
    }
    int getImageViewId()
    {
        return  imageViewId;
    }
    boolean hasImage()
    {
        return imageViewId!=HAS_IMAGE;
    }
    int getSong()
    {
        return song;
    }
}