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
        System.out.println("handleTouchDown called");
        oldX=xVelocity;
        oldY=yVelocity;
        xVelocity=yVelocity=0;
    }

    public void handleUp() {
        System.out.println("handleUp called");
        xVelocity=oldX;
        yVelocity=oldY;
    }

    public void move(int xx, int yy) {
        int dx=xx-this.x;
        int dy=yy-this.y;
        int left=x+dx;
        int top=y+dy;
        if(left<0) left=0;
        if(top<0) top=0;
        if(left+width>screenWidth) left=screenWidth-width;
        if(top+height>screenHeight) top=screenHeight-height;
        x=left;
        y=top;
    }
}
