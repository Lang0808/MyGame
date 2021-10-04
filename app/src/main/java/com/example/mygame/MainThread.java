package com.example.mygame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    private long targetFPS=30;
    private long averageFPS=0;


    public MainThread(SurfaceHolder holder, GameView game){
        super();
        this.surfaceHolder=holder;
        this.gameView=game;
    }

    public void setRunning(boolean run){
        running=run;
    }

    @Override
    public void run(){

        long starttime;
        long timeMillis;
        long waitTime;
        long totalTime=0;
        int frameCount=0;
        long targetTime=1000/targetFPS;

        while(running){
            starttime=System.nanoTime();
            canvas=null;
            try{
                canvas=this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }

            } catch(Exception e){}
            finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            timeMillis=(System.nanoTime()-starttime)/1000000;
            waitTime=targetTime-timeMillis;
            try{
                this.sleep(waitTime);
            }
            catch(Exception e){

            }
            totalTime+=System.nanoTime()-starttime;
            frameCount++;
            if(frameCount==targetFPS){
                averageFPS=1000/((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;
                System.out.println(averageFPS);
            }
        }
    }
}
