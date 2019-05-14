package com.suntiago.a;

import android.content.Context;
import android.media.AudioManager;

/**
 * @author chenjunwei
 * @desc
 * @date 2019/5/14
 */
public class AudioUtil {
    private AudioManager mAudioManager;
    private static AudioUtil mInstance;

    private AudioUtil(Context context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public synchronized static AudioUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AudioUtil(context);
        }
        return mInstance;
    }

    /**
     * 获取多媒体最大音量
     *
     * @return
     */
    public int getMediaMaxVolume() {
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取多媒体音量
     *
     * @return
     */
    public int getMediaVolume() {
        return mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取通话最大音量
     *
     * @return
     */
    public int getCallMaxVolume() {
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
    }

    /**
     * 获取系统音量最大值
     *
     * @return
     */
    public int getSystemMaxVolume() {

        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
    }

    /**
     * 获取系统音量
     *
     * @return
     */
    public int getSystemVolume() {

        return mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    /**
     * 获取提示音量最大值
     *
     * @return
     */
    public int getAlermMaxVolume() {
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
    }

    /**
     * 设置多媒体音量
     * 这里我只写了多媒体和通话的音量调节，其他的只是参数不同，大家可仿照
     */
    public void setMediaVolume(int volume) {
        //音量类型
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume,
                AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
    }

    /**
     * 设置通话音量
     *
     * @param volume
     */
    public void setCallVolume(int volume) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                volume,
                AudioManager.STREAM_VOICE_CALL);
    }

    /**
     * 关闭/打开扬声器播放
     *
     * @param on
     */
    public void setSpeakerStatus(boolean on) {
        if (on) {
            //扬声器
            mAudioManager.setSpeakerphoneOn(true);
            mAudioManager.setMode(AudioManager.MODE_NORMAL);
        } else {
            // 设置最大音量
            int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
            mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, max, AudioManager.STREAM_VOICE_CALL);
            // 设置成听筒模式
            mAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            // 关闭扬声器
            mAudioManager.setSpeakerphoneOn(false);
            mAudioManager.setRouting(AudioManager.MODE_NORMAL, AudioManager.ROUTE_EARPIECE, AudioManager.ROUTE_ALL);
        }
    }
}
