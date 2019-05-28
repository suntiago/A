package com.suntiago.a;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaPlayUtil {
    private static MediaPlayUtil mMediaPlayUtil;
    private MediaPlayer mMediaPlayer;
    private static final String TAG = "MediaPlayUtil";

    public static MediaPlayUtil getInstance() {
        if (mMediaPlayUtil == null) {
            mMediaPlayUtil = new MediaPlayUtil();
        }
        return mMediaPlayUtil;
    }

    private MediaPlayUtil() {
        mMediaPlayer = new MediaPlayer();
    }

    public void play(Context c, int id) {
        mMediaPlayer.stop();
        AssetFileDescriptor afd = c.getResources().openRawResourceFd(id);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            afd.close();
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
