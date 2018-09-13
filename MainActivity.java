package com.example.akerfeldt.automa_decks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final String STATE_CONTAINER_NUMBERS="container_numbers";
    private static final String STATE_ROUND="round";
    private static final String STATE_I="i";


    private int[] container_numbers={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    private int round=1;
    private int i=0;
    private int[] images={R.drawable.card1,R.drawable.card2,R.drawable.card3,R.drawable.card4,R.drawable.card5,
            R.drawable.card6,R.drawable.card7,R.drawable.card8,R.drawable.card9,R.drawable.card10,
            R.drawable.card11,R.drawable.card12,R.drawable.card13,R.drawable.card14,R.drawable.card15,R.drawable.card16};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            i = savedInstanceState.getInt(STATE_I, 0);
            round=savedInstanceState.getInt(STATE_ROUND,1);
            container_numbers=savedInstanceState.getIntArray(STATE_CONTAINER_NUMBERS);
        }

        final ImageView imageCard=(ImageView)findViewById(R.id.imageCard);


        //this if is used for handle the app when calls onCreate() again e.g orientation change
        if(round==1) {
            shuffle();
            imageCard.setImageResource(images[container_numbers[0]]);
        }
        else{
            imageCard.setImageResource(images[container_numbers[i]]);
        }


        final Button nextButton=(Button)findViewById(R.id.nextButton);
        final Button backButton=(Button)findViewById(R.id.backButton);
        Button shuffleButton=(Button)findViewById(R.id.shuffleButton);

        if(round==1) {
            backButton.setEnabled(false);
        }

        final TextView turnNumber=(TextView) findViewById(R.id.turnNumber);
        turnNumber.setText(round+"");



        /////////////////////////////nextButton///////////////////////////////////////////////////
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                round++;
                turnNumber.setText(round+"");
                backButton.setEnabled(true);

                if(i==0) {
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==1){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==2){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==3){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==4){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==5){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==6){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==7){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==8){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==9){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==10){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==11){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==12){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==13){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==14){
                    imageCard.setImageResource(images[container_numbers[i+1]]);
                    i++;
                }
                else if(i==15){
                    imageCard.setImageResource(R.drawable.end);
                    nextButton.setEnabled(false);
                    i=0;
                }
            }
        });

        /////////////////////////////////backButton///////////////////////////////////////////////////
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                round--;
                turnNumber.setText(round + "");
                if(round==1){
                    backButton.setEnabled(false);
                }

                nextButton.setEnabled(true);
                if(i==0) {
                    imageCard.setImageResource(images[container_numbers[15]]);
                    i=15;
                }
                else if(i==1){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==2){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==3){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==4){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==5){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==6){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==7){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==8){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==9){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==10){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==11){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==12){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==13){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==14){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }
                else if(i==15){
                    imageCard.setImageResource(images[container_numbers[i-1]]);
                    i--;
                }

            }

        });

        /////////////////////////shuffleButton//////////////////////////////////////////////////
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle();
                i=0;
                round=1;
                turnNumber.setText(round+"");
                backButton.setEnabled(false);
                imageCard.setImageResource(images[container_numbers[0]]);
                nextButton.setEnabled(true);
            }
        });
        //////////////////////////////////////////////////////////////////////////////



    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putIntArray(STATE_CONTAINER_NUMBERS,container_numbers );
        outState.putInt(STATE_ROUND,round);
        outState.putInt(STATE_I,i);
    }

    private void shuffle(){

        Random rand = new Random();
        int j;
        int max;
        int swap;
        int pick;
        for (j = 0; j < 5; j++) {
            max = 15;
            while (max != 0) {
                pick = rand.nextInt(max + 1);
                swap = container_numbers[pick];
                container_numbers[pick] = container_numbers[max];
                container_numbers[max] = swap;
                max--;
            }
        }
    }

}
