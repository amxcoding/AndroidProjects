package com.example.layoutinflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        Layout Inflater vs findViewById:

        - Layout Inflater: used to create a new View object from one of your xml layouts.

        - findByID: gives you reference to a view that has already been created.

         */


        /*
        To create an copy of our own custom layout and add it:

        To inflate the new view layout, all I did was tell the inflater the name of my xml file (my_layout),
        the parent layout that I want to add it to (mainLayout), and that I don't actually want to add it yet (false).
        (I could also set the parent to null, but then the layout parameters of my custom layout's root view would be ignored.)
         */




        // get a reference to the already created main layout
        @SuppressLint("ResourceType") LinearLayout mainLayout = findViewById(R.layout.activity_main);

        // inflate (create) another copy of our custom layout
        LayoutInflater inflater = getLayoutInflater();
        View myLayout = inflater.inflate(R.layout.my_layout, mainLayout, false);

        // make changes to our custom layout and its subviews
        myLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500));
        TextView textView = (TextView) myLayout.findViewById(R.id.textView);
        textView.setText("New Layout");

        // add our custom layout to the main layout
        mainLayout.addView(myLayout);








    }
}