package com.example.mygame;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener{
    CharacterSprite character;
    boolean ok=false;

    TouchListener(CharacterSprite Character){
        character=Character;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x=(int)motionEvent.getRawX();
        int y=(int)motionEvent.getRawY();

        if(character.isInside(x, y) || ok){
            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    character.handleTouchDown();
                    ok=true;
                    return true;
                case MotionEvent.ACTION_UP:
                    character.handleUp();
                    ok=false;
                    return true;
                case MotionEvent.ACTION_MOVE:
                    System.out.println("x= "+x+" y= "+y);
                    character.move(x, y);
                    ok=true;
                    return true;
            }
        }

        return false;
    }
}
