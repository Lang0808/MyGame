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
        // The reason why re need variable ok is:
        //    + When user touch on the screen, we only process if they touch the character
        //    + But in ACTION_MOVE, they can move their finger outside the character, and we can't process
        //    + So variables ok will say: ok, they first touched the character, and then move to other position
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
                    character.move(x, y);
                    ok=true;
                    return true;
            }
        }

        return false;
    }
}
