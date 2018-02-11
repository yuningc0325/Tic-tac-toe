package com.example.rickchang.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //switch
    public boolean gameSwitch=true;
    // 0 means cross, 1 for cricle.
    public int playerStatus =0;
    // 2 means unplayed
    public int[] gamestate={2,2,2,2,2,2,2,2,2};
    //
    public int[][] winPosition={{0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,4,8},
                                {2,4,6},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8}};

    public void fade(View view){
        ImageView iv = (ImageView) view;
        // connect array index number to the pratical object(ImageView)
        int indexOfGameStateArr = Integer.parseInt(iv.getTag().toString());

        // if the state is umplayed
        if(gamestate[indexOfGameStateArr]==2 && gameSwitch) {
            gamestate[indexOfGameStateArr] = playerStatus;
            iv.setAlpha(0f);
            if (playerStatus == 0) {
                iv.setImageResource(R.drawable.cross);
                playerStatus = 1;

            } else {
                iv.setImageResource(R.drawable.circle);
                playerStatus = 0;
            }
            iv.animate().alpha(1f).setDuration(1500);

            // condition of win
            String winner ="Circle";
            TextView win = (TextView) findViewById(R.id.winMessage);
            LinearLayout l =(LinearLayout)findViewById(R.id.LinerLayout);

            for(int[] el:winPosition){

            if(gamestate[el[0]]==gamestate[el[1]] && gamestate[el[1]]==gamestate[el[2]] && gamestate[el[0]]!=2){
                //switch off the game to avoid from clicking again!
                gameSwitch=false;

               if(gamestate[el[0]]==0){
                       winner="Cross";
                   }
                   win.setText(winner+" "+"win!");
                   l.setVisibility(View.VISIBLE);
               }else{

                boolean gameOver= true;
                for(int gel:gamestate) {
                    if (gel == 2) {
                        gameOver = false;
                    }
                }
                 if(gameOver){
                     win.setText("It's draw!");
                     l.setVisibility(View.VISIBLE);
                 }
                }
            }

            }
        }

    public void playagain(View view){
        // close the layout
        LinearLayout LinerLayout =(LinearLayout)findViewById(R.id.LinerLayout);
        LinerLayout.setVisibility(View.INVISIBLE);

        playerStatus=0;

        // rebuild the array

        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }

        // replace the board, make every view invisible

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grildLayout);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

        }

        //switch on
        gameSwitch=true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
