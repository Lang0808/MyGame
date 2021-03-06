package com.example.mygame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {
    private Bitmap image;
    private int x, y;
    private int xVelocity=10, yVelocity=5;
    private int screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight=Resources.getSystem().getDisplayMetrics().heightPixels;
    private int oldX, oldY;
    private int width, height;
    private int dX, dY;

    public CharacterSprite(Bitmap bmp){
        image=bmp;
        x=100;
        y=100;
        width=image.getWidth();
        height=image.getHeight();

    }

    public void update(){
        if(x<0 && y<0){
            x=screenWidth/2;
            y=screenHeight/2;
        }
        else {
            x=x+xVelocity;
            y=y+yVelocity;
            if(x>screenWidth-image.getWidth() || x<0){
                xVelocity=-xVelocity;
            }
            if(y>screenHeight-image.getHeight() || y<0){
                yVelocity=-yVelocity;
            }
        }
    }

    public void handleOnTouch(){

    }

    public boolean isInside(int xx, int yy){
        return xx>=x && xx<=x+width && yy>=y && yy<=y+height;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);

    }

    public void handleTouchDown() {
        oldX=xVelocity;
        oldY=yVelocity;
        xVelocity=yVelocity=0;
    }

    public void handleUp() {
        xVelocity=oldX;
        yVelocity=oldY;
        dX=0;
        dY=0;
    }

    public void move(int xx, int yy) {
        if(dX==0 && dY==0){
            dX=x-xx;
            dY=y-yy;
        }
        int left=xx+dX;
        int top=yy+dY;
        if(left<0) left=0;
        if(top<0) top=0;
        if(left+width>screenWidth) left=screenWidth-width;
        if(top+height>screenHeight) top=screenHeight-height;
        x=left;
        y=top;
    }
}
