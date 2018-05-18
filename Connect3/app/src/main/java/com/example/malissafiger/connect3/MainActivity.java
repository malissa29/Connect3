package com.example.malissafiger.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //0=captain, 1=iron
    int activePlayer = 0;

    Boolean gameisActive= true;

    // 2 represents unplayed
    int [] gamestate= {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view) {
        //getimages
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());
        int TappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[TappedCounter] == 2 && gameisActive) {

            gamestate[TappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.captain);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.iron);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(100);

            for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] &&
                        gamestate[winningPosition[1]] == gamestate[winningPosition[2]] &&
                        gamestate[winningPosition[0]] != 2) {
                    //WON
                    gameisActive = false;

                    String winner ="IronMan";

                   if(gamestate[winningPosition[0]] == 0)
                    {

                       winner= "Captain America";

                    }



                    EditText winnerMessage= (EditText)findViewById(R.id.winnermessage);

                    winnerMessage.setText(winner + " has Won!!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);

                    layout.setVisibility(View.VISIBLE);

                }else
                {
                    boolean gameisOver = true;

                    for(int counterstate : gamestate) {

                        if(counterstate == 2) gameisOver = false;
                     }

                     if(gameisOver){
                         EditText winnerMessage= (EditText)findViewById(R.id.winnermessage);

                         winnerMessage.setText("It's a draw!!");

                         LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);

                         layout.setVisibility(View.VISIBLE);

                     }
                }
            }
        }
    }
        public void playAgain (View view)
        {
            gameisActive = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);

            layout.setVisibility(View.INVISIBLE);

            activePlayer = 0;

            for (int i=0; i <gamestate.length; i++)
            {
                gamestate [i]= 2;

            }

            GridLayout gridLayout= (GridLayout) findViewById(R.id.gridLayout);
            for(int i=0; i< gridLayout.getChildCount(); i++)
            {
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
