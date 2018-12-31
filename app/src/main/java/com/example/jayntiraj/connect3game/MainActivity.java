package com.example.jayntiraj.connect3game;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //0: yellow, 1: red, 2:empty, 3:inactive
    int activePlayer=0;
    int[] places = {2,2,2,2,2,2,2,2,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void dropIn(View view) {

        int place = Integer.parseInt(view.getTag().toString());
        if(places[place]==2) {
            places[place]=activePlayer;
            ImageView counter = (ImageView) view;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o_new);
            } else {
                counter.setImageResource(R.drawable.x_new);
            }
            counter.animate().translationYBy(1500).setDuration(300);
            if(checkForWin() || checkForFull()) {
                endGame();
            }
            switchPlayer();
        }

    }

    public void resetGame(View view) {
        for(int i = 0; i<9; i++) {
            places[i] = 2;
        }
        setContentView(R.layout.activity_main);
    }

    public boolean horizontalWin(int player) {
        for(int i = 0; i<=6; i+=3) {
            if(places[i]==player && places[i+1]==player && places[i+2]==player) {
                return true;
            }
        }
        return false;
    }

    public boolean verticalWin(int player) {
        for(int i=0; i<=2; i++) {
            if(places[i]==player && places[i+3]==player && places[i+6]==player) {
                return true;
            }
        }
        return false;
    }

    public boolean diagonalWin(int player) {
        if(places[0]==player && places[4]==player && places[8]==player) {
            return true;
        } else if(places[2]==player && places[4]==player && places[6]==player) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkForWin() {
        if(horizontalWin(activePlayer) || verticalWin(activePlayer) || diagonalWin(activePlayer)) {
            TextView text = (TextView) findViewById(R.id.textView);
            if(activePlayer==1) {
                text.setText("X Wins!");
            } else {
                text.setText("O Wins!");
            }
            return true;
        }
        return false;
    }

    public void switchPlayer() {
        if(activePlayer==0) {
            activePlayer=1;
        } else {
            activePlayer=0;
        }
    }

    public void endGame() {
        TextView text = (TextView) findViewById(R.id.textView);
        text.setVisibility(View.VISIBLE);
        Button reset = (Button) findViewById(R.id.button);
        reset.setVisibility(View.VISIBLE);
        for(int i=0; i<9; i++) {
            places[i] = 3;
        }

    }

    public boolean checkForFull() {
        for(int i=0; i<9; i++) {
            if(places[i]==2) {
                return false;
            }
        }
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("It's a tie!");
        return true;
    }
}
