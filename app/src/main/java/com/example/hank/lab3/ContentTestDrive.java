package com.example.hank.lab3;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Hank on 13.12.2015.
 */
public class ContentTestDrive extends Fragment {

    ImageButton buttonGazz;
    ImageButton buttonTormoza;
    TextView tvSpeed;
    Integer  speed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_test_drive, container, false);
        buttonGazz = (ImageButton) v.findViewById(R.id.buttonGazzz);
        buttonTormoza = (ImageButton) v.findViewById(R.id.buttonTormoza);;
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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean onTouchTormoz(){
        if(speed - 1 >= 0)
            tvSpeed.setText((--speed).toString());
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
