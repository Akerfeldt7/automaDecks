package com.example.akerfeldt.automa_decks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button gwtAutomaBtn=(Button)findViewById(R.id.gwtAutomaBtn);

        gwtAutomaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGwtAutoma=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startGwtAutoma);
            }
        });

        Button scytheAutomaBtn=(Button)findViewById(R.id.scytheAutomaBtn);

        scytheAutomaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startScytheAutoma=new Intent(getApplicationContext(),scythe_automa.class);
                startActivity(startScytheAutoma);
            }
        });


    }
}
