package com.ted.lcdtest;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Copyright (C) 2008 The Android Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Created by Ted.Yt on 9/2/16.
 */
public class PlayFragment extends Fragment {

    private MyTimerTask myTimerTask;

    private Timer mTimer;

    private View mView;

    private boolean isPaused = false;

    private static final int[] colorIds = {
            R.color.lcd_color_red,
            R.color.lcd_color_green,
            R.color.lcd_color_blue,
            R.color.lcd_color_black,
            R.color.lcd_color_white,
            R.mipmap.ck1,
            R.mipmap.chess,
            R.color.lcd_color_gray,
            R.mipmap.view
    } ;

    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.play_fragment, container, false);
        mView = view.findViewById(R.id.exhibit);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (myTimerTask == null){
            myTimerTask = new MyTimerTask();
        }
        if (mTimer == null){
            mTimer = new Timer();
        }

        mTimer.schedule(myTimerTask,2000,2000);
        myTimerTask.run();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (!isPaused){
            pauseTask();
        }
    }


    public void pauseTask(){
        isPaused = true;
        myTimerTask.cancel();
        mTimer.purge();
        mTimer.cancel();

        mTimer = null;
        myTimerTask = null;
    }

    public void reStartTask(){
        isPaused = false;
        if (mTimer == null){
            mTimer = new Timer();
        }

        if (myTimerTask == null){
            myTimerTask = new MyTimerTask();
        }

        mTimer.schedule(myTimerTask,2000,2000);
        myTimerTask.run();
    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            Log.d("tui", "Timer task is running");
            Message msg = myHandler.obtainMessage();
            myHandler.sendMessage(msg);
        }
    }

    private Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            Log.d("tui", "My hanlder is running");
            mView.setBackgroundResource(colorIds[index]);
            index++;
            if (index >= colorIds.length){
                index = 0;
            }
        }
    };
}
