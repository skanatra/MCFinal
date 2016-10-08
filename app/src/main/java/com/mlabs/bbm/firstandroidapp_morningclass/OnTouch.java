package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class OnTouch extends AppCompatActivity {
    EditText x1,x2,y1,y2,xdiff,ydiff,direction,quadrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        final ImageView imgView = (ImageView)findViewById(R.id.zxc);
        x1=(EditText)findViewById(R.id.x1);
        x2=(EditText)findViewById(R.id.x2);
        y1=(EditText)findViewById(R.id.y1);
        y2=(EditText)findViewById(R.id.y2);
        xdiff=(EditText)findViewById(R.id.dx);
        ydiff=(EditText)findViewById(R.id.dy);
        direction=(EditText)findViewById(R.id.direction);
        quadrant=(EditText)findViewById(R.id.quadrant);

        imgView.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, initY = 0, finalX = 0, finalY = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();

                        display(initX, finalX, initY, finalY);
                        return false;
                }return false;
            }
        });
    }

    public void display(float inix, float finx, float iniy, float finy){
        String x = "", y = "";
        float delx, dely;

        delx = inix-finx;
        dely = iniy-finy;

        if(inix == finy){
        }else if(inix > finx){
            x = "Left";
        }else if(inix < finx){
            x = "Right";
        }

        if(iniy == finy){

        }else if(iniy > finy){
            y = "Up";
        }else if (iniy < finy){
            y = "Down";
        }

        x1.setText("x1: "+Float.toString(inix));
        x2.setText("x2: "+Float.toString(finx));
        y1.setText("y1: "+Float.toString(iniy));
        y2.setText("y2: "+Float.toString(finx));
        xdiff.setText("xdiff: "+Float.toString(delx));
        ydiff.setText("ydiff: "+Float.toString(dely));

        if(x.equals("Right")){
            if(y.equals("Up")){
                direction.setText("Swipe: Right-Up");
                quadrant.setText("Quadrant: 1");
            }else{
                direction.setText("Swipe: Right-Down");
                quadrant.setText("Quadrant: 4");
            }
        }else if(x.equals("Left")){
            if(y.equals("Up")){
                direction.setText("Swipe: Left-Up");
                quadrant.setText("Quadrant: 2");
            }else{
                direction.setText("Swipe: Left-Down");
                quadrant.setText("Quadrant: 3");
            }
        }else{
            direction.setText("Swipe: Origin");
            quadrant.setText("Quadrant: 0");
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

