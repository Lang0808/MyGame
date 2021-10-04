package com.example.mygame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite characterSprite;
    TouchListener touchListener;

    GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        thread=new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        characterSprite=new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.crab));
        touchListener=new TouchListener(characterSprite);
        setOnTouchListener(touchListener);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    public void update(){
        characterSprite.update();
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas!=null){
            characterSprite.draw(canvas);
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry=true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
