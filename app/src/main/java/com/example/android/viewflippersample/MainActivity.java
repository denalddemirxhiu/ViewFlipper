/*
    Denald Demirxhiu

    This is a tutorial application used to see how ViewFlipper works
    and how it can be used. The ViewFlipper contains three image views initially.
    The flipping interval is set by default to 2 seconds. The SeekBar is used to control
    the flipping interval between child views. The buttons are used to start/stop automatic flipping,
    show the previous view, show the next view, remove the current view and add a new image view to
    the viewFlipper.

    Attributes used in the layout file are
        - flipInterval
        - autoStart

    The ViewFlipper is set to start flipping automatically when loaded in the screen with a flipping
    interval of 2 seconds. Changes are made through the described buttons and the SeekBar.
 */

package com.example.android.viewflippersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    // viewFlipper is going to contain the reference to the ViewFlipper
    // found in the layout file
    private ViewFlipper viewFlipper;

    // Buttons to control viewFlipper operations
    private Button next, previous, autoPlay, add, remove;

    // SeekBar to control the flipping interval
    private SeekBar seekBar;

    // Text view to display the current flip interval
    private TextView intervalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the views in the layout file
        getViews();

        // Setting the respective animations for entering and leaving the ViewFlipper
        final Animation inAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        final Animation outAnim = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        final Animation inPrevAnim = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        final Animation outPrevAnim = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // Setting the animation when the view enters
        viewFlipper.setInAnimation(inAnim);

        // Setting the animation when the view leaves
        viewFlipper.setOutAnimation(outAnim);

        // Shows the next item in the viewFlipper
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(inAnim);
                viewFlipper.setOutAnimation(outAnim);
                // Shows the next child view in the viewFlipper
                viewFlipper.showNext();
            }
        });

        // Checking if the viewFlipper is set to automatically flip between views
        // when it is loaded in the screen
        if( viewFlipper.isAutoStart() ) {
            // Setting the text of the autoPlay button
            autoPlay.setText("Stop Auto Flip");
        }


        // Shows the previous item in the viewFlipper
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(inPrevAnim);
                viewFlipper.setOutAnimation(outPrevAnim);
                // Shows the previous child view in the viewFlipper
                viewFlipper.showPrevious();
            }
        });

        // Automatically starts flipping between child views
        autoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewFlipper.isFlipping()) {
                    autoPlay.setText("Start Auto Flip");
                    // Stops automatic flipping between children views
                    viewFlipper.stopFlipping();
                } else {
                    viewFlipper.setInAnimation(inAnim);
                    viewFlipper.setOutAnimation(outAnim);
                    autoPlay.setText("Stop Auto Flip");
                    // Starts the automatic flipping between children views
                    viewFlipper.startFlipping();
                }
            }
        });

        // Add a new Image view to ViewFlipper
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a new image with image four (Image to be added soon)
                ImageView image = new ImageView(getApplicationContext());
                image.setImageResource(R.drawable.four);
                image.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                // Adding the image to the list of images in the viewFlipper
                viewFlipper.addView(image);
            }
        });

        // Removes the current image view from the ViewFlipper
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Removing the currently displayed image in the viewFlipper
                viewFlipper.removeView(viewFlipper.getCurrentView());
            }
        });

        // Sets the flip interval of the ViewFlipper
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Setting the Flip Interval
                viewFlipper.setFlipInterval(progress * 500);
                intervalText.setText("Flip Interval: " + (double)progress/2 + " Seconds");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    // Getting all the views found in the layout file
    private void getViews() {
        viewFlipper = findViewById(R.id.viewFlipperSample);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        autoPlay = findViewById(R.id.autoPlay);
        add = findViewById(R.id.addImage);
        remove = findViewById(R.id.removeImage);
        seekBar = findViewById(R.id.seekBar);
        intervalText = findViewById(R.id.intervalText);
    }
}
