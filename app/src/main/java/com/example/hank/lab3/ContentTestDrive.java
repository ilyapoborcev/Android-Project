package com.example.hank.lab3;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.*;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Hank on 13.12.2015.
 */
public class ContentTestDrive extends Fragment {
    View v;
    String LOG_TAG = "popsLog";
    ImageButton buttonGazz;
    ImageButton buttonTormoza;
    TextView tvSpeed;
    Integer  speed;

    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.content_test_drive, container, false);
        buttonGazz = (ImageButton) v.findViewById(R.id.buttonGazzz);
        buttonTormoza = (ImageButton) v.findViewById(R.id.buttonTormoza);
        tvSpeed = (TextView) v.findViewById(R.id.tvSpeed);
        speed = 0;
        tvSpeed.setText(speed.toString());

        buttonGazz.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchGazz();
            }
        });
        buttonTormoza.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchTormoz();
            }
        });
        return v;
    }

    public boolean onTouchGazz(){
        tvSpeed.setText((++speed).toString());
        try {
            Thread.sleep(200);
            managerAudio(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean onTouchTormoz(){
            if(speed - 10 >= 0) {
                speed = -10;
                tvSpeed.setText((speed).toString());
                try {
                    managerAudio(-1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                speed = 0;
                tvSpeed.setText(speed.toString());
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }

    public void managerAudio(Integer sound) throws IOException {
        switch (sound) {
            case 3:
                releaseMP();
                mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.s01);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            case 50:
                releaseMP();
                mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.s02);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            case 100:
                releaseMP();
                mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.s04);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            case -1:
                releaseMP();
                mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.tormozaaaa);
                mediaPlayer.start();
                break;
        }
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkSpeedSound(){
        try {
            if(speed == 0) {
                managerAudio(1);
                mediaPlayer.start();
            }
            else if (speed == 40) {
                managerAudio(2);
                mediaPlayer.start();
            }
            else if(speed == 80) {
                managerAudio(3);
                mediaPlayer.start();
            }
            else {
                managerAudio(4);
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}