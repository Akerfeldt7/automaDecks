package com.example.akerfeldt.automa_decks;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class scythe_automa extends AppCompatActivity {

    private static final String STATE_CONTAINER_NUMBERS="container_numbers";
    private static final String STATE_ROUND="round";
    private static final String STATE_I="i";
    private static final String STATE_STARS="stars";
    private static final String STATE_STARSTRACKERIMAGE="starsTrackerImage";
    private static final String STATE_REVERSE="reverse";
    private static final String STATE_DISCARD_COMBAT_CARDS="discard_combat_cards";

    private int[] container_numbers={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    private ArrayList<Integer> discard_combat_cards;
    private int[] images={R.drawable.scythe1,R.drawable.scythe2,R.drawable.scythe3,R.drawable.scythe4,R.drawable.scythe5,
            R.drawable.scythe6,R.drawable.scythe7,R.drawable.scythe8,R.drawable.scythe9,R.drawable.scythe10,
            R.drawable.scythe11,R.drawable.scythe12,R.drawable.scythe13,R.drawable.scythe14,R.drawable.scythe15,R.drawable.scythe16,R.drawable.scythe17,
            R.drawable.scythe18,R.drawable.scythe19};
    private int[] images_reverse={R.drawable.scythe1_rev,R.drawable.scythe2_rev,R.drawable.scythe3_rev,R.drawable.scythe4_rev,R.drawable.scythe5_rev,
            R.drawable.scythe6_rev,R.drawable.scythe7_rev,R.drawable.scythe8_rev,R.drawable.scythe9_rev,R.drawable.scythe10_rev,R.drawable.scythe11_rev,
            R.drawable.scythe12_rev,R.drawable.scythe13_rev,R.drawable.scythe14_rev,R.drawable.scythe15_rev,R.drawable.scythe16_rev,R.drawable.scythe17_rev,
            R.drawable.scythe18_rev,R.drawable.scythe19_rev};
    private int i=0;
    private int round=1;
    private int stars=1;
    private boolean starsTrackerImage=false;
    private boolean reverse=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scythe_automa);

        if (savedInstanceState != null) {
            i = savedInstanceState.getInt(STATE_I, 0);
            round=savedInstanceState.getInt(STATE_ROUND,1);
            container_numbers=savedInstanceState.getIntArray(STATE_CONTAINER_NUMBERS);
            stars=savedInstanceState.getInt(STATE_STARS,1);
            starsTrackerImage=savedInstanceState.getBoolean(STATE_STARSTRACKERIMAGE,false);
            reverse=savedInstanceState.getBoolean(STATE_REVERSE,false);
            discard_combat_cards=savedInstanceState.getIntegerArrayList(STATE_DISCARD_COMBAT_CARDS);
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
        final Button shuffleButton=(Button)findViewById(R.id.shuffleButton);
        final Button combatButton=(Button)findViewById(R.id.combatButton);
        final Button advanceStarTrackerButton=(Button)findViewById(R.id.advanceStarTrackerButton);
        advanceStarTrackerButton.setEnabled(false);
        final Button starsTrackerButton=(Button)findViewById(R.id.starsTrackerButton);
        final Button showCombatCardsButton=(Button)findViewById(R.id.showCombatCardsButton);

        discard_combat_cards=new ArrayList<Integer>();

        if(round==1) {
            backButton.setEnabled(false);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("nextButton","start i:"+i);
                round++;
                backButton.setEnabled(true);
                if(i+1>=18){
                    combatButton.setEnabled(false);
                }
                if (i == 18) {
                    if(reverse==false) {
                        imageCard.setImageResource(R.drawable.scythe_end);
                    }
                    else{
                        imageCard.setImageResource(R.drawable.scythe_end_rev);
                    }
                    nextButton.setEnabled(false);
                    combatButton.setEnabled(false);
                    i=0;
                }
                else {
                    while(discard_combat_cards.contains(container_numbers[i+1])){
                        i++;
                        if(i==18){
                            this.onClick(v);
                        }
                    }
                    if(reverse==false) {
                        imageCard.setImageResource(images[container_numbers[i + 1]]);
                    }
                    else{
                        imageCard.setImageResource((images_reverse[container_numbers[i+1]]));
                    }
                    i++;
                }
                Log.d("nextButton","end i:"+i);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("backButton","start i:"+i);
               round--;
                if (round==1) {
                    backButton.setEnabled(false);
                }

                nextButton.setEnabled(true);

                if (i == 0) {
                    if (reverse == false) {
                        imageCard.setImageResource(images[container_numbers[18]]);
                    }
                    else {
                        imageCard.setImageResource(images_reverse[container_numbers[18]]);
                    }
                    i = 18;
                }
                else {
                    while(discard_combat_cards.contains(container_numbers[i-1])){
                        i--;
                        if(i==0){
                            this.onClick(v);
                        }
                    }
                    if (reverse == false) {
                        imageCard.setImageResource(images[container_numbers[i - 1]]);
                    } else {
                        imageCard.setImageResource(images_reverse[container_numbers[i - 1]]);
                    }
                    if(i-1<=18){
                        combatButton.setEnabled(true);
                    }
                    i--;
                }
                Log.d("backButton","end i:"+i);
            }
        });

        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discard_combat_cards.clear();
                shuffle();
                i=0;
                round=1;
                backButton.setEnabled(false);
                nextButton.setEnabled(true);
                combatButton.setEnabled(true);
                if(reverse==false){
                    imageCard.setImageResource(images[container_numbers[0]]);
                }
                else{
                    imageCard.setImageResource(images_reverse[container_numbers[0]]);
                }

                if(starsTrackerImage){ //an patithei to koumpi shuffle prin to finish_combat
                    discard_combat_cards.add(container_numbers[0]);
                    i=1;
                    nextButton.setEnabled(false);
                }
            }
        });





        starsTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(starsTrackerImage==false){
                    starsTrackerImage=true;
                    advanceStarTrackerButton.setEnabled(true);
                    nextButton.setEnabled(false);
                    backButton.setEnabled(false);
                    shuffleButton.setEnabled(false);
                    combatButton.setEnabled(false);
                    if(stars==1){
                        imageCard.setImageResource(R.drawable.startracker_card1);
                    }
                    else if(stars==2){
                        imageCard.setImageResource(R.drawable.startracker_card2);
                    }
                    else if(stars==3){
                        imageCard.setImageResource(R.drawable.startracker_card3);
                    }
                    else if(stars==4){
                        imageCard.setImageResource(R.drawable.startracker_card4);
                    }
                    else if(stars==5){
                        imageCard.setImageResource(R.drawable.startracker_card5);
                    }
                    else if(stars==6){
                        imageCard.setImageResource(R.drawable.startracker_card6);
                    }
                    else if(stars==7){
                        imageCard.setImageResource(R.drawable.startracker_card7);
                    }
                    else if(stars==8){
                        imageCard.setImageResource(R.drawable.startracker_card8);
                    }
                    else if(stars==9){
                        imageCard.setImageResource(R.drawable.startracker_card9);
                    }
                    else if(stars==10){
                        imageCard.setImageResource(R.drawable.startracker_card10);
                    }
                    else if(stars==11){
                        imageCard.setImageResource(R.drawable.startracker_card11);
                    }
                    else if(stars==12){
                        imageCard.setImageResource(R.drawable.startracker_card12);
                    }
                    else if(stars==13){
                        imageCard.setImageResource(R.drawable.startracker_card13);
                    }
                    else if(stars==14){
                        imageCard.setImageResource(R.drawable.startracker_card14);
                    }
                    else if(stars==15){
                        imageCard.setImageResource(R.drawable.startracker_card15);
                    }
                    else if(stars==16){
                        imageCard.setImageResource(R.drawable.startracker_card16);
                    }
                    else if(stars==17){
                        imageCard.setImageResource(R.drawable.startracker_card17);
                    }
                    else if(stars==18){
                        imageCard.setImageResource(R.drawable.startracker_card18);
                    }
                    else if(stars==19){
                        imageCard.setImageResource(R.drawable.startracker_card19);
                    }
                    else if(stars==20){
                        imageCard.setImageResource(R.drawable.startracker_card20);
                    }
                    else if(stars==21){
                        imageCard.setImageResource(R.drawable.startracker_card21);
                    }
                    else if(stars==22){
                        imageCard.setImageResource(R.drawable.startracker_card22);
                    }
                }
                else{
                    nextButton.setEnabled(true);
                    backButton.setEnabled(true);
                    shuffleButton.setEnabled(true);
                    combatButton.setEnabled(true);
                    advanceStarTrackerButton.setEnabled(false);
                    starsTrackerImage=false;
                    if(reverse==false) {
                        imageCard.setImageResource(images[container_numbers[i]]);
                    }
                    else{
                        imageCard.setImageResource(images_reverse[container_numbers[i]]);
                    }
                }
            }
        });

        advanceStarTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stars!=22) {
                    stars++;
                }
                if(stars==2){
                    imageCard.setImageResource(R.drawable.startracker_card2);
                }
                else if(stars==3){
                    imageCard.setImageResource(R.drawable.startracker_card3);
                }
                else if(stars==4){
                    imageCard.setImageResource(R.drawable.startracker_card4);
                }
                else if(stars==5){
                    imageCard.setImageResource(R.drawable.startracker_card5);
                }
                else if(stars==6){
                    imageCard.setImageResource(R.drawable.startracker_card6);
                }
                else if(stars==7){
                    imageCard.setImageResource(R.drawable.startracker_card7);
                }
                else if(stars==8){
                    imageCard.setImageResource(R.drawable.startracker_card8);
                }
                else if(stars==9){
                    imageCard.setImageResource(R.drawable.startracker_card9);
                }
                else if(stars==10){
                    imageCard.setImageResource(R.drawable.startracker_card10);
                    reverse=true;
                    shuffle();
                    i=0;
                    round=1;
                }
                else if(stars==11){
                    imageCard.setImageResource(R.drawable.startracker_card11);
                }
                else if(stars==12){
                    imageCard.setImageResource(R.drawable.startracker_card12);
                }
                else if(stars==13){
                    imageCard.setImageResource(R.drawable.startracker_card13);
                }
                else if(stars==14){
                    imageCard.setImageResource(R.drawable.startracker_card14);
                }
                else if(stars==15){
                    imageCard.setImageResource(R.drawable.startracker_card15);
                }
                else if(stars==16){
                    imageCard.setImageResource(R.drawable.startracker_card16);
                }
                else if(stars==17){
                    imageCard.setImageResource(R.drawable.startracker_card17);
                }
                else if(stars==18){
                    imageCard.setImageResource(R.drawable.startracker_card18);
                }
                else if(stars==19){
                    imageCard.setImageResource(R.drawable.startracker_card19);
                }
                else if(stars==20){
                    imageCard.setImageResource(R.drawable.startracker_card20);
                }
                else if(stars==21){
                    imageCard.setImageResource(R.drawable.startracker_card21);
                }
                else if(stars==22){
                    imageCard.setImageResource(R.drawable.startracker_card22);
                }
            }
        });


        combatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j;
                boolean need_shuffle=false;
                j=i;

                if(starsTrackerImage==false) {
                    showCombatCardsButton.setVisibility(View.VISIBLE);
                    if (j + 1 <= 18) {
                        while (discard_combat_cards.contains(container_numbers[j + 1])) {
                            j++;
                            if (j + 1 > 18) {
                                need_shuffle=true;
                                break;
                            }
                        }
                        if(need_shuffle){
                            imageCard.setImageResource(R.drawable.scythe_end);
                            if(reverse==false) {
                                imageCard.setPivotX(imageCard.getWidth() / 2);
                                imageCard.setPivotY(imageCard.getHeight() / 2);
                                imageCard.setRotation(270);
                            }
                            else{
                                imageCard.setPivotX(imageCard.getWidth() / 2);
                                imageCard.setPivotY(imageCard.getHeight() / 2);
                                imageCard.setRotation(90);
                            }
                            combatButton.setText("FINISH COMBAT");
                            nextButton.setEnabled(false);
                            backButton.setEnabled(false);
                            shuffleButton.setEnabled(true);
                            starsTrackerButton.setEnabled(false);
                            advanceStarTrackerButton.setEnabled(false);
                            starsTrackerImage = true;
                        }
                        else{
                            imageCard.setImageResource(images[container_numbers[j + 1]]);
                            imageCard.setPivotX(imageCard.getWidth() / 2);
                            imageCard.setPivotY(imageCard.getHeight() / 2);
                            imageCard.setRotation(270);
                            discard_combat_cards.add(container_numbers[j + 1]);
                            combatButton.setText("FINISH COMBAT");
                            nextButton.setEnabled(false);
                            backButton.setEnabled(false);
                            shuffleButton.setEnabled(false);
                            starsTrackerButton.setEnabled(false);
                            advanceStarTrackerButton.setEnabled(false);
                            starsTrackerImage = true;
                        }

                    }
                    else{
                        imageCard.setImageResource(R.drawable.scythe_end);
                        if(reverse==false) {
                            imageCard.setPivotX(imageCard.getWidth() / 2);
                            imageCard.setPivotY(imageCard.getHeight() / 2);
                            imageCard.setRotation(270);
                        }
                        else{
                            imageCard.setPivotX(imageCard.getWidth() / 2);
                            imageCard.setPivotY(imageCard.getHeight() / 2);
                            imageCard.setRotation(90);
                        }

                        combatButton.setText("FINISH COMBAT");
                        nextButton.setEnabled(false);
                        backButton.setEnabled(false);
                        shuffleButton.setEnabled(true);
                        starsTrackerButton.setEnabled(false);
                        advanceStarTrackerButton.setEnabled(false);
                        starsTrackerImage = true;

                    }
                }
                else{
                    showCombatCardsButton.setVisibility(View.INVISIBLE);
                    combatButton.setText("COMBAT");
                    imageCard.setRotation(0);
                    nextButton.setEnabled(true);
                    if(round!=1){
                        backButton.setEnabled(true);
                    }
                    shuffleButton.setEnabled(true);
                    starsTrackerButton.setEnabled(true);
                    starsTrackerImage=false;
                    if(reverse==false){
                        imageCard.setImageResource(images[container_numbers[i]]);
                    }
                    else{
                        imageCard.setImageResource(images_reverse[container_numbers[i]]);
                    }

                }
                Log.d("combatButton","i:"+i);
                Log.d("combatButton","j:"+j);
            }
        });

        showCombatCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO kapoio fragment pou na deixnei tis combat cards pou exoun xrisimopoihthei
            }
        });





    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putIntArray(STATE_CONTAINER_NUMBERS,container_numbers );
        outState.putInt(STATE_ROUND,round);
        outState.putInt(STATE_I,i);
        outState.putInt(STATE_STARS,stars);
        outState.putBoolean(STATE_STARSTRACKERIMAGE,starsTrackerImage);
        outState.putBoolean(STATE_REVERSE,reverse);
        outState.putIntegerArrayList(STATE_DISCARD_COMBAT_CARDS,discard_combat_cards);
    }

    private void shuffle(){

        Random rand = new Random();
        int j;
        int max;
        int swap;
        int pick;
        for (j = 0; j < 5; j++) {
            max = 18;
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
