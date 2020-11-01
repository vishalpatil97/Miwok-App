package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // on view of Numbers Text view
        TextView numbers = findViewById(R.id.Numbers);
        // On click listener of Number textView
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NumberIntent = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(NumberIntent);
            }
        });

        // Find view by Family Category
        TextView family = findViewById(R.id.FamilyMembers);
        // On click listener for Family textView
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent FamilyIntent = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(FamilyIntent);
            }
        });
        // Find view by Phrase category
        TextView phrases = findViewById(R.id.Phrases);
        // On click listener for phrase textView
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PhrasesIntent = new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(PhrasesIntent);
            }
        });
        //Find view by Colors category
        TextView colors = findViewById(R.id.Colors);
        // On click listener of Colors textView
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this,ColorActivity.class);
                startActivity(colorsIntent);
            }
        });
    }

}