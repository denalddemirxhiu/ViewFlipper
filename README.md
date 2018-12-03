# ViewFlipper


## Introduction
ViewFlipper is a great widget to be used in animating multiple views that have been added to it. If given the functionality, it can animate automatically between the views it contains at specified intervals. The ViewFlipper can display only one view at a single instance in time. With ViewFlipper you can choose the animation that is used in flipping between different views and also provide custom animations to the flip.

## History of ViewFlipper
ViewFlipper has been introduced since the first release of Android (API level 1). The widget is still supported up to the latest version of Android currently available at API level 28. ViewFlipper extends ViewAnimator, which is also a widget. The class hierarchy is as follows:
	
| Class Hierarchy             |
|-----------------------------|
| java.lang.Object            |
| android.view.View           |
| android.view.ViewGroup      |
| android.widget.FrameLayout  |
| android.widget.ViewAnimator |
| android.widget.ViewFlipper  |

## Major Methods/Attributes
These methods are very useful in the functionality of a ViewFlipper:
	
| Method                                 | Use                                                                                                       |
|----------------------------------------|-----------------------------------------------------------------------------------------------------------|
| boolean isAutoStart()                  | returns true if the ViewFlipper calls startFlipping automatically when it is displayed                    |
| boolean isFlipping()                   | returns true if the contained views are being flipped                                                     |
| void setAutoStart(boolean autoStart)   | decides if the method is going to automatically call startFlipping() when it is attached to the container |
| void setFlipInterval(int milliseconds) | it sets a flipping interval timer (how long to wait before flipping to the next child view)               |
| void startFlipping()                   | starts a timer to flip through contained views                                                            |
| void stopFlipping()                    | stops the flipping of the child views                                                                     |

#### Useful methods that are used with ViewFlipper but are part of ViewAnimator
	
| Method                                       | Use                                                                                  |
|----------------------------------------------|--------------------------------------------------------------------------------------|
| void addView(View child)                     | Used to add a new view to the ViewFlipper                                            |
| View getCurrentView()                        | Returns the reference to the current child view displayed in the ViewFlipper         |
| void removeView(View view)                   | Removes the specified view from the flipper                                          |
| void setInAnimation(Animation inAnimation)   | Specifies the animation that is going to be used when a child view enters the layout |
| void setOutAnimation(Animation outAnimation) | Specified the animation that is going to be used when the view exits the layout      |
| void showNext()                              | Used to display the next child view manually                                         |
| void showPrevious()                          | Used to display the previous child view manually                                     |

### Main attributes used in ViewFlipper
	
| Attribute        | Use                                                                                                                      |
|------------------|--------------------------------------------------------------------------------------------------------------------------|
| autoStart        | property used to start animating automatically between children views (true or false value)                              |
| flipInterval     | property used to set the time in milliseconds for the animation duration of each flip between child views                |
| animateFirstView | property used to animate the first view of the ViewFlipper when the ViewFlipper is first displayed (true or false value) |
| inAnimation      | property that defines the animation used when the child view enters the screen                                           |
| outAnimation     | property that defines the animation used when the child view leaves the screen                             |

## The code
							MainActivity.java
	
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

## References
	https://abhiandroid.com/ui/viewflipper
	https://developer.android.com/reference/android/widget/ViewFlipper




