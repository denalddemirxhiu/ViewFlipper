# ViewFlipper


## Introduction
ViewFlipper is a great widget to be used in animating multiple views that have been added to it. If given the functionality, it can animate automatically between the views it contains at specified intervals. The ViewFlipper can display only one view at a single instance in time. With ViewFlipper you can choose the animation that is used in flipping between different views and also provide custom animations to the flip.

## History of ViewFlipper
ViewFlipper has been introduced since the first release of Android (API level 1). The widget is still supported up to the latest version of Android currently available at API level 28. ViewFlipper extends ViewAnimator, which is also a widget. The class hierarchy is as follows:
	java.lang.Object
	android.view.View
	android.view.ViewGroup
	android.widget.FrameLayout 
	android.widget.ViewAnimator
	android.widget.ViewFlipper

## Major Methods/Attributes
These methods are very useful in the functionality of a ViewFlipper:
	
	1. boolean isAutoStart() - returns true if the ViewFlipper calls startFlipping automatically when it is displayed
	2. boolean isFlipping() - returns true if the contained views are being flipped
	3. void setAutoStart(boolean autoStart) - decides if the method is going to automatically call startFlipping() when it is attached to the container
	4. void setFlipInterval(int milliseconds) - it sets a flipping interval timer (how long to wait before flipping to the next child view)
	5. void startFlipping() - starts a timer to flip through contained views
	6. void stopFlipping() - stops the flipping of the children views
	
	| Method                                 | Use                                                                                                       |
|----------------------------------------|-----------------------------------------------------------------------------------------------------------|
| boolean isAutoStart()                  | returns true if the ViewFlipper calls startFlipping automatically when it is displayed                    |
| boolean isFlipping()                   | returns true if the contained views are being flipped                                                     |
| void setAutoStart(boolean autoStart)   | decides if the method is going to automatically call startFlipping() when it is attached to the container |
| void setFlipInterval(int milliseconds) | it sets a flipping interval timer (how long to wait before flipping to the next child view)               |
| void startFlipping()                   | starts a timer to flip through contained views                                                            |
| void stopFlipping()                    | stops the flipping of the child views                                                                     |

### Useful methods that are used with ViewFlipper but part of ViewAnimator
	
	1. void addView(View child) - Used to add a new view to the ViewFlipper
	2. View getCurrentView() - Returns the reference to the current child view displayed in the ViewFlipper
	3. void removeView(View view) - Removes the specified view from the flipper
	4. void setInAnimation(Animation inAnimation) - Specifies the animation that is going to be used when a child view enters the layout
	5. void setOutAnimation(Animation outAnimation) - Specified the animation that is going to be used when the view exits the layout
	6. void showNext() - used to display the next child view manually
	7. void showPrevious() - used to display the previous child view manually

### Main attributes used in ViewFlipper:
	
	1. autoStart - property used to start animating automatically between children views (true or false value)
	2. flipInterval - property used to set the time in milliseconds for the animation duration of each flip between child views
	3. animateFirstView - property used to animate the first view of the ViewFlipper when the ViewFlipper is first displayed (true or false value)
	4.inAnimation - property that defines the animation used when the child view enters the screen
	5.outAnimation - property that defines the animation used when the child view leaves the screen

## References:
	https://abhiandroid.com/ui/viewflipper
	https://developer.android.com/reference/android/widget/ViewFlipper




