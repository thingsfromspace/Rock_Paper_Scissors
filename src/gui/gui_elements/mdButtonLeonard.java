package gui.gui_elements;

import gui.FilePathsLeonard;
import gui.gui_elements.options_screen.PreferencesLeonard;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <h1>This class allows for the creation of a
 * simple material design button that reacts interactively
 * with the mouse.</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class mdButtonLeonard extends Rectangle {


    // BUTTON SETTINGS

    // these should all remain the same so the shadows behave in the same way
    private static int startingRadius = 10;
    private static int endingRadius = 40;
    private static int startingXOffset = 10;
    private static int endingXOffset = 40;
    private static int startingYOffset = 7;
    private static int endingYOffset = 35;
    // how many times has the button been clicked
    public int timesClicked = 0;
    // general settings
    public boolean isButton = true;
    private Color textColor;
    private String name;
    // image settings
    private boolean image;
    private double imageScaleX = 1;


    // ANIMATION SETTINGS

    private boolean animation;
    private Color buttonColor = new Color(1.0, .843, 2.0 / 3, 1);
    private Color shadowColor = Color.DARKGRAY;
    private boolean getLonger;
    private boolean getShorter;
    private boolean isShort = true;
    private double startingWidth;
    private double startingHeight;
    private double endingWidth;
    private double endingHeight;
    private double frames = 5.0;
    private double counter = 0.0;


    // SHADOW SETTINGS
    private double imageScaleY = 1;
    // text settings
    private boolean text;
    // drop shadow
    private DropShadow dropShadow = new DropShadow();
    // file paths
    private String[] filePaths = new FilePathsLeonard().paths;
    // AUDIO SETTINGS
    // hover audio
    private String hoverStringPath = new File(filePaths[0]).getAbsolutePath();
    public AudioClip hoverSound = new AudioClip(new File(hoverStringPath).toURI().toString());
    // click audio
    private String clickStringPath = new File(filePaths[1]).getAbsolutePath();
    public AudioClip clickSound = new AudioClip(new File(clickStringPath).toURI().toString());



    // the items that need to be rendered
    private Text buttonText;
    private ImageView buttonImage;


    // CONSTRUCTORS

    /**
     * Constructs a new mdButton
     *
     * @param name   the name of the button
     * @param width  the width of the button
     * @param height the height of the button
     * @param x      the x coordinate of the button
     * @param y      the y coordinate of the button
     */
    public mdButtonLeonard(String name, double width, double height, double x, double y) {
        // set the name of the button
        this.name = name;

        // construct a rectangle
        adjustRectangleProperties(this, width, height, x, y,
                buttonColor);
        // round the corners
        roundCorners(15, 15);

        // set the dimensions
        startingWidth = width;
        startingHeight = height;
        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;

        // set the drop shadow effects
        this.setDsEffects(10, 10, 5, Color.DARKGRAY);

        // set the volume of the sounds
        clickSound.volumeProperty().set(0.15);
        hoverSound.volumeProperty().set(0.075);
    }


    // SETTERS

    /**
     * Returns the name of this button
     *
     * @return the name of this button
     */
    public String toString() {
        return this.name;
    }

    /**
     * Sets the animation preference
     * @param animation the animation preference
     */
    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    /**
     * Sets the audio preference for the hover audio
     * @param soundOn the audio preference for the hover audio
     */
    public void setHoverSoundON(boolean soundOn) {
        PreferencesLeonard.preferences[1] = soundOn;
    }

    /**
     * Sets the audio preference for the click audio
     * @param soundOn the audio preference for the click audio
     */
    public void setClickSoundON(boolean soundOn) {
        PreferencesLeonard.preferences[0] = soundOn;
    }

    /**
     * sets the drop shadow
     */
    public void setDropShadow() {
        this.setEffect(this.dropShadow);
    }

    /**
     * Sets the button color
     * @param color the button color
     */
    public void setButtonColor(Color color) {
        this.buttonColor = color;
        this.setFill(color);
    }

    /**
     * Adjusts the drop shadow effects
     * @param radius the radius of the drop shadow
     * @param xoffset the x offset of the drop shadow
     * @param yoffset the y offset of the drop shadow
     * @param color the color of the drop shadow
     */
    private void setDsEffects(double radius, double xoffset, double yoffset, Color color) {
        // just set the properties
        dropShadow.setRadius(radius);
        dropShadow.setOffsetX(xoffset);
        dropShadow.setOffsetY(yoffset);
        dropShadow.setColor(color);
    }

    /**
     * adjusts the properties of a rectangle
     *
     * @param rect   the rectangle that you want to adjust
     * @param width  the desired width
     * @param height the desired height
     * @param x      the desired x coordinate
     * @param y      the desired y coordinate
     * @param fill   the desired color of the rectangle
     */
    private void adjustRectangleProperties(Rectangle rect, double width, double height,
                                           double x, double y, Paint fill) {
        // just set the properties
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setFill(fill);
    }

    /**
     * this rounds the corners of this object
     *
     * @param arcWidth  the arcwidth to round the corners
     * @param arcHeight the archeight to round the corners
     */
    private void roundCorners(double arcWidth, double arcHeight) {
        // just set the properties
        this.setArcHeight(arcHeight);
        this.setArcWidth(arcWidth);
    }

    /**
     * This performs necessary actions for when the button is clicked
     */
    public void clicked() {
        // increment the number of clicks
        timesClicked++;

        // if the click sound is on
        if (PreferencesLeonard.preferences[0] && isButton) {
            // play the click sound
            clickSound.play();
        }
    }

    /**
     * This allows you to add text to the button
     * @param text the text you want to add
     * @param font the font of the text
     * @param color the color of the text
     * @return the text object
     */
    public Text addText(String text, Font font, Color color) {
        // create a new Text object
        Text buttonText = new Text(text);
        // set the font
        buttonText.setFont(font);
        // set the color
        buttonText.setFill(color);
        // set the stroke
        buttonText.setStroke(color);
        // update this object's text color
        this.textColor = color;
        // this sets the reference point for the coordinates
        buttonText.setTextOrigin(VPos.TOP);

        // set the x and y of the text appropriately
        buttonText.setX(this.getX() + (this.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setY(this.getY() + (this.getHeight() - buttonText.getBoundsInLocal().getHeight()) / 2);

        // update this object's text properties
        this.buttonText = buttonText;
        this.text = true;

        // return the text
        return buttonText;
    }

    /**
     * This allows you to add an image to the button
     * @param filepath the filepath of the image (relative)
     * @param scaleX the x scale of the image
     * @param scaleY the y scale of the image
     * @return the image
     */
    public ImageView addImage(String filepath, double scaleX, double scaleY) {
        // create a new ImageView to hold the image
        ImageView buttonImage;
        try {
            // obtain the image from the path and set it to buttonImage
            Image imageSource = new Image(new FileInputStream(filepath));
            buttonImage = new ImageView((imageSource));
        } catch (FileNotFoundException exception) {
            return null;
        }

        // set the X and Y of the image appropriately
        buttonImage.setX(this.getX() + (this.getWidth() - buttonImage.getBoundsInLocal().getWidth()) / 2);
        buttonImage.setY(this.getY() + (this.getHeight() - buttonImage.getBoundsInLocal().getHeight()) / 2);

        // set the scaling of the image
        buttonImage.setScaleX(scaleX);
        buttonImage.setScaleY(scaleY);

        // update this object's scaling properties
        this.imageScaleX = scaleX;
        this.imageScaleY = scaleY;

        // update this object's image properties
        this.buttonImage = buttonImage;
        this.image = true;

        // return the button image
        return buttonImage;
    }

    /**
     * This will resize the text appropriately, depending on the size of the corresponding button
     * @param element the text Node
     */
    private void resizeText(Text element) {
        // get the width and height of the text
        double eWidth = element.getLayoutBounds().getWidth();
        double eHeight = element.getLayoutBounds().getHeight();

        // set the new scaling of the text
        element.setScaleX(this.getWidth() / this.startingWidth);
        element.setScaleY(this.getHeight() / this.startingHeight);

        // set the x and y of the text appropriately
        element.setX(element.getX() - (element.getLayoutBounds().getWidth() - eWidth));
        element.setY(element.getY() - (element.getLayoutBounds().getHeight() - eHeight));
    }

    /**
     * This will resize the image appropriately, depending on the size of the corresponding image
     * @param element
     */
    private void resizeImage(ImageView element) {
        // get the width and height of the image
        double eWidth = element.getLayoutBounds().getWidth();
        double eHeight = element.getLayoutBounds().getHeight();

        // set the scaling of the image appropriately
        element.setScaleX((this.getWidth() / this.startingWidth) * this.imageScaleX);
        element.setScaleY((this.getHeight() / this.startingHeight) * this.imageScaleY);

        // set the x and y of the image appropriately
        element.setX(element.getX() - (element.getLayoutBounds().getWidth() - eWidth));
        element.setY(element.getY() - (element.getLayoutBounds().getHeight() - eHeight));
    }

    /**
     * This allows you to resize the button to a new width and height
     * @param width the new width of the button
     * @param height the new height of the button
     */
    private void resizeButton(double width, double height) {
        // set the x and y of the button appropriately
        this.setX(this.getX() - (width - this.getWidth()) / 2);
        this.setY(this.getY() - (height - this.getHeight()) / 2);

        // update the width and height of the button
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * This checks to see if the coordinates fall inside this button
     * @param x the x-coo relative to the screen
     * @param y the y-coo relative to the screen
     * @return a boolean representing whether the point is inside the button
     */
    private boolean containsPoint(double x, double y) {
        // get the bounds of this element relative to the screen
        Bounds boundsInScreen = this.localToScreen(this.getBoundsInLocal());

        // subtract the minX and minY to get relative button coordinates
        x -= (boundsInScreen.getMinX());
        y -= (boundsInScreen.getMinY());

        // check to see if these values fall inside the button and return this result
        return (0 <= x && x <= this.getWidth()) && ((0 <= y && y <= this.getHeight()));
    }


    // ANIMATION METHODS

    /**
     * This animates the drop shadow
     * @param counter how many frames through the animation we are
     * @param grow whether the shadow should grow or shrink
     */
    private void animateDs(double counter, boolean grow) {
        if (grow) {
            // the shadow needs to grow

            // this basically just updates it to be counter/frames way through the animation
            this.setDsEffects(startingRadius + (counter * (endingRadius - startingRadius)) / frames,
                    startingXOffset + (counter * (endingXOffset - startingXOffset)) / frames,
                    startingYOffset + (counter * (endingYOffset - startingYOffset)) / frames, shadowColor);
        } else {
            // the shadow needs to shrink

            // this basically just updates it to be counter/frames way through the animation
            this.setDsEffects(endingRadius - (counter * (endingRadius - startingRadius)) / frames,
                    endingXOffset - (counter * (endingXOffset - startingXOffset)) / frames,
                    endingYOffset - (counter * (endingYOffset - startingYOffset)) / frames, shadowColor);
        }
    }

    /**
     * This animates the size of the button
     * @param counter how many frames through the animation we are
     * @param grow whether the button should grow or shrink
     */
    private void animateSize(double counter, boolean grow) {
        if (grow) {
            // the button needs to grow

            // this basically just updates it to be counter/frames way through the animation
            this.resizeButton((startingWidth + (counter * (endingWidth - startingWidth) / frames)),
                    (startingHeight + (counter * (endingHeight - startingHeight) / frames)));
        } else {
            // the button needs to shrink

            // this basically just updates it to be counter/frames way through the animation
            this.resizeButton((endingWidth - (counter * (endingWidth - startingWidth) / frames)),
                    (endingHeight - (counter * (endingHeight - startingHeight) / frames)));
        }

        // if the button has images or text, resize those too
        if (text) resizeText(buttonText);
        if (image) resizeImage(buttonImage);
    }

    /**
     * This animates the buttons depending on the mouse state and the current button state.
     */
    public void animate() {
        // if animation is turned off, exit this function
        if (!animation) return;

        // get the pointer location
        Point pointerLocation = MouseInfo.getPointerInfo().getLocation();

        // obtain the x and y of the mouse
        double x = pointerLocation.getX();
        double y = pointerLocation.getY();

        // check to see if the mouse is within the button
        boolean onButton = this.containsPoint(x, y);

        // if the mouse is within the button...
        if (onButton) {
            if (isShort && !getLonger) {
                // the button is currently short and the mouse has just entered the button

                // if the hover sound is turned on
                if (PreferencesLeonard.preferences[1] && isButton) {
                    // play the hover sound
                    hoverSound.play();
                }
                // the shadow is short and needs to grow
                getLonger = true;

                // animate the drop shadow and the size of the button
                animateDs(counter, true);
                animateSize(counter, true);

            } else if (isShort) {
                // the shadow is already getting longer, but the animation is not complete

                // increment the counter (which counts the amount of frames that have passed)
                counter++;

                // animate the drop shadow and the size of the button
                animateDs(counter, true);
                animateSize(counter, true);

                // if the animation is already fully extended
                if (counter >= (int) frames) {
                    // reset the counter to zero
                    counter = 0;

                    // update animation states
                    getLonger = false;
                    isShort = false;
                }
            } else if (!isShort && getShorter) {
                // if the button is long and it is currently getting shorter

                // we need to kind of reverse the animation, so set the counter accordingly
                counter = frames - counter;
                if (counter == frames) counter--;

                // update animation states
                isShort = true;
                getLonger = true;
                getShorter = false;
            }
        } else {
            // the mouse is outside of the button
            if (!isShort && !getShorter) {
                // the button is not short and the mouse just exited the button

                // the button needs to get shorter
                getShorter = true;

                // animate the drop shadow and the size of the button
                animateDs(counter, false);
                animateSize(counter, false);

            } else if (!isShort) {
                // the button is not short but the animation has started

                // increment the counter (which counts the amount of frames that have passed)
                counter++;

                // animate the drop shadow and the size of the button
                animateDs(counter, false);
                animateSize(counter, false);

                // if the animation has already completed
                if (counter >= (int) frames) {
                    // reset the counter
                    counter = 0;

                    // update animation states
                    getShorter = false;
                    isShort = true;
                }
            } else if (isShort && getLonger) {
                // if the button is short but is getting longer

                // we need to kind of reverse the animation, so set the counter accordingly
                counter = frames - counter;
                if (counter == frames) counter--;

                // update animation states
                isShort = false;
                getLonger = false;
                getShorter = true;
            }
        }
    }
}
