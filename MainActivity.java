package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red
    String winner;
    boolean over;
    int i,j;

    int activePlayer = 0;

    boolean gameIsActive=true;

    // 2 means not played

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void dropIn(View view) {



            ImageView counter = (ImageView) view;



            int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if (gameState[tappedCounter] == 2 &&gameIsActive ) {

                gameState[tappedCounter] = activePlayer;

                counter.setTranslationY(-1000f);

                if (activePlayer == 0) {

                    counter.setImageResource(R.drawable.yellow_counter);

                    activePlayer = 1;

                } else {

                    counter.setImageResource(R.drawable.red_counter);

                    activePlayer = 0;

                }

                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

                for (int[] winningPosition : winningPositions) {

                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                            gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]] != 2) {

                        // Someone has won!

                        gameIsActive = false;
                        winner="Red";


                        if (gameState[winningPosition[0]] == 0) {

                            winner = "Yellow";

                        }

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText(winner+" has Won!");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    } else {

                        over = true;

                        for (int counterState : gameState) //noinspection IfStatementMissingBreakInLoop
                            if (counterState == 2) over = false;

                        if (over) {

                            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                            winnerMessage.setText("It's a DRAW!");

                            LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                            layout.setVisibility(View.VISIBLE);

                        }

                    }

                }
            }


        }

        public void playAgain(View view) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            over=false;
            gameIsActive=true;
            layout.setVisibility(View.INVISIBLE);
            activePlayer = 0;
            for (i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }
           GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout);
            //System.out.println(gridLayout.getChildAt(0));

            for (int i = 0; i < gridLayout.getChildCount(); i++) {

                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

            }
            /*ImageView counter1=(ImageView )findViewById(R.id.imageView);
            counter1.setImageResource(0);
            ImageView counter2=(ImageView )findViewById(R.id.imageView2);
            counter2.setImageResource(0);
            ImageView counter3=(ImageView )findViewById(R.id.imageView3);
            counter3.setImageResource(0);
            ImageView counter4=(ImageView )findViewById(R.id.imageView4);
            counter4.setImageResource(0);
            ImageView counter5=(ImageView )findViewById(R.id.imageView5);
            counter5.setImageResource(0);
            ImageView counter6=(ImageView )findViewById(R.id.imageView6);
            counter6.setImageResource(0);
            ImageView counter7=(ImageView )findViewById(R.id.imageView7);
            counter7.setImageResource(0);
            ImageView counter8=(ImageView )findViewById(R.id.imageView8);
            counter8.setImageResource(0);
            ImageView counter9=(ImageView )findViewById(R.id.imageView9);
            counter9.setImageResource(0);*/

            //ropIn(0);
            //System.out.println("kirty");
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
