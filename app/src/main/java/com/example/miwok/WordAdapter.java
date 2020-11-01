package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceID;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResource) {
        super(context,0,words);
        mColorResourceID = colorResource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }

        // Get the object located at this position
        final Word currentWord = getItem(position);

        //Find the TextView in list_item.xml file with ID
        TextView englishTextView = listItemView.findViewById(R.id.default_text_view);
        // Set this to the name textView
        assert currentWord != null;
        englishTextView.setText(currentWord.getEnglishTransition());

        //Find the TextView in list_item.xml file with ID
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        // Set this to the name TextView
        miwokTextView.setText(currentWord.getMiwokTransition());

        // Find the ImageView
        ImageView imageView = listItemView.findViewById(R.id.image_view);
        if(currentWord.hasImage()) {
            // Adding Image to the View
            imageView.setImageResource(currentWord.getImageResourceID());
            // If image is there set it to visible
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            // Remove the imageView if no image is available
            imageView.setVisibility(View.GONE);
        }
        // Set the theme color for the set item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that  the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        // Set the background color to the textContainer view
        textContainer.setBackgroundColor(color);

        // Return whole view with two textView
        return listItemView;

    }
}
