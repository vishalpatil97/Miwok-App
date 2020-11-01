package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    // Creating a Audio Manager
    private AudioManager mAudioManager;
    // Setting on focus change listener
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }

        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> phrase = new ArrayList<>();

        phrase.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phrase.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        phrase.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        phrase.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        phrase.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        phrase.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        phrase.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        phrase.add(new Word("I’m coming", "әәnәm",R.raw.phrase_im_coming));
        phrase.add(new Word("Let’s go", "yoowutis",R.raw.phrase_lets_go));
        phrase.add(new Word("Come here","әnni'nem",R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this,phrase,R.color.colorPhrase);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        // Adding on item click listener to play the pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Release media Player resources to play different audio every time we click
                releaseMediaPlayer();

                // Requesting audio focus for playback
                int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now

                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, phrase.get(i).getAudioResourceID());

                    Log.v("PhrasesActivity", "CurrentWord: " + phrase.get(i));

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mOnCompletion);
                }
            }
        });
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer  = null;

            // Releasing the audio focus when playback complete
            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    // On stop release the resource
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}