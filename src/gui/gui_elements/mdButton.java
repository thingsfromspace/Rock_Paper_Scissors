package gui.gui_elements;

import gui.gui_elements.options_screen.Preferences;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.Node;
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

@SuppressWarnings("unused")
public class mdButton extends Rectangle {

    public boolean isButton = true;
    private Node parent;

    // these should all remain the same so the shadows behave in the same way
    private static int startingRadius = 10;
    private static int endingRadius = 40;
    private static int startingXOffset = 10;
    private static int endingXOffset = 40;
    private static int startingYOffset = 7;
    private static int endingYOffset = 35;
    // how many times has the button been clicked
    @SuppressWarnings("WeakerAccess")
    public int timesClicked = 0;
    private String name;

    // keeps track of what elements are on the button
    private boolean image;
    private double startingImageWidth;
    private double startingImageHeight;
    private double endingImageWidth;
    private double endingImageHeight;

    private boolean text;
    private double startingTextWidth;
    private double startingTextHeight;
    private double endingTextWidth;
    private double endingTextHeight;
    private Color textColor;
    private String hoverStringPath = "/Users/tannerleonardmac/Documents/code_google_drive/AP_Computer_Science/Rock Paper Scissors/src/gui/gui_elements/Assets/hover.mp3";
    public AudioClip hoverSound = new AudioClip(new File(hoverStringPath).toURI().toString());

    private String clickStringPath = "/Users/tannerleonardmac/Documents/code_google_drive/AP_Computer_Science/Rock Paper Scissors/src/gui/gui_elements/Assets/click.mp3";
    public AudioClip clickSound = new AudioClip(new File(clickStringPath).toURI().toString());

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
    // drop shadow
    private DropShadow dropShadow = new DropShadow();

    // the items that need to be rendered
    private Text buttonText;
    private ImageView buttonImage;

    // CONSTRUCTORS
    private mdButton() {
    }

    // SETTERS

    public mdButton(String name, Node parent, double width, double height, double x, double y) {
        this.parent = parent;
        this.name = name;

        constructRectangle(this, width, height, x, y,
                buttonColor);
        roundCorners(15, 15);

        startingWidth = width;
        startingHeight = height;

        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
        this.setDsEffects(10, 10, 5, Color.DARKGRAY);

        clickSound.volumeProperty().set(0.15);
        hoverSound.volumeProperty().set(0.075);
    }

    public String toString() {
        return this.name;
    }

    public static void setStartingRadius(int startingRadius) {
        mdButton.startingRadius = startingRadius;
    }

    public static void setEndingRadius(int endingRadius) {
        mdButton.endingRadius = endingRadius;
    }

    public static void setStartingXOffset(int startingXOffset) {
        mdButton.startingXOffset = startingXOffset;
    }

    public static void setEndingXOffset(int endingXOffset) {
        mdButton.endingXOffset = endingXOffset;
    }

    public static void setStartingYOffset(int startingYOffset) {
        mdButton.startingYOffset = startingYOffset;
    }

    public static void setEndingYOffset(int endingYOffset) {
        mdButton.endingYOffset = endingYOffset;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public void setHoverSoundON(boolean soundOn) {
        Preferences.preferences[1] = soundOn;
    }

    public void setClickSoundON(boolean soundOn) {
        Preferences.preferences[0] = soundOn;
    }

    public void setDropShadow() {
        this.setEffect(this.dropShadow);
    }

    public void setButtonColor(Color color) {
        this.buttonColor = color;
        this.setFill(color);
    }

    // CONSTRUCTORS

    public void setShadowColor(Color color) {
        this.shadowColor = color;
    }

    public void setButtonWidth(double startingWidth) {
        this.startingWidth = startingWidth;
        this.endingWidth = this.startingWidth * 1.1;
    }

    public void setButtonHeight(double startingHeight) {
        this.startingHeight = startingHeight;
        this.endingHeight = this.startingHeight * 1.1;
    }

    private void setDsEffects(double radius, double xoffset, double yoffset, Color color) {
        dropShadow.setRadius(radius);
        dropShadow.setOffsetX(xoffset);
        dropShadow.setOffsetY(yoffset);
        dropShadow.setColor(color);
    }

    private void constructRectangle(Rectangle rect, double width, double height,
                                    double x, double y, Paint fill) {
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setFill(fill);
    }

    private void roundCorners(@SuppressWarnings("SameParameterValue") double arcWidth, @SuppressWarnings("SameParameterValue") double arcHeight) {
        this.setArcHeight(arcHeight);
        this.setArcWidth(arcWidth);
    }

    public void clicked() {
        timesClicked++;
        System.out.println("in click: " + Preferences.preferences[0] + " " + Preferences.preferences[1]);
        if (Preferences.preferences[0] && isButton) {
            clickSound.play();
        }
    }

    public Text addText(String text, Font font, Color color) {
        Text buttonText = new Text(text);
        buttonText.setFont(font);
        buttonText.setFill(color);
        buttonText.setStroke(color);
        this.textColor = color;
        buttonText.setTextOrigin(VPos.TOP);

        buttonText.setX(this.getX() + (this.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setY(this.getY() + (this.getHeight() - buttonText.getBoundsInLocal().getHeight()) / 2);

        this.startingTextWidth = buttonText.getBoundsInLocal().getWidth();
        this.startingTextHeight = buttonText.getBoundsInLocal().getHeight();
        this.endingTextWidth = startingTextWidth * 1.1;
        this.endingTextHeight = startingTextHeight * 1.1;

        this.buttonText = buttonText;
        this.text = true;
        return buttonText;
    }

    public ImageView addImage(String filepath) {
        ImageView buttonImage;
        try {
            Image imageSource = new Image(new FileInputStream(filepath));
            buttonImage = new ImageView((imageSource));
        } catch (FileNotFoundException exception) {
            return null;
        }

        buttonImage.setX(this.getX() + (this.getWidth() - buttonImage.getBoundsInLocal().getWidth()) / 2);
        buttonImage.setY(this.getY() + (this.getHeight() - buttonImage.getBoundsInLocal().getHeight()) / 2);
        // buttonText.getBoundsInLocal().getHeight() / 9

        this.startingImageWidth = buttonImage.getBoundsInLocal().getWidth();
        this.startingImageHeight = buttonImage.getBoundsInLocal().getHeight();
        this.endingImageHeight = startingImageHeight * 1.1;
        this.endingImageWidth = startingImageWidth * 1.1;

        this.buttonImage = buttonImage;
        this.image = true;
        return buttonImage;
    }

    private void resizeText(Text element) {
        double eWidth = element.getLayoutBounds().getWidth();
        double eHeight = element.getLayoutBounds().getHeight();

        element.setScaleX(this.getWidth() / this.startingWidth);
        element.setScaleY(this.getHeight() / this.startingHeight);

        element.setX(element.getX() - (element.getLayoutBounds().getWidth() - eWidth));
        element.setY(element.getY() - (element.getLayoutBounds().getHeight() - eHeight));
    }

    private void resizeImage(ImageView element) {
        double eWidth = element.getLayoutBounds().getWidth();
        double eHeight = element.getLayoutBounds().getHeight();

        element.setScaleX(this.getWidth() / this.startingWidth);
        element.setScaleY(this.getHeight() / this.startingHeight);

        element.setX(element.getX() - (element.getLayoutBounds().getWidth() - eWidth));
        element.setY(element.getY() - (element.getLayoutBounds().getHeight() - eHeight));
    }

    private void resizeButton(double width, double height) {
        this.setX(this.getX() - (width - this.getWidth()) / 2);
        this.setY(this.getY() - (height - this.getHeight()) / 2);

        this.setWidth(width);
        this.setHeight(height);
    }

    private boolean containsPoint(double x, double y) {
        Bounds boundsInScreen = this.localToScreen(this.getBoundsInLocal());

        x -= (boundsInScreen.getMinX());
        y -= (boundsInScreen.getMinY());

        return (0 <= x && x <= this.getWidth()) && ((0 <= y && y <= this.getHeight()));
    }

    // ANIMATION METHODS

    private void animateDs(double counter, boolean grow) {
        if (grow) {
            this.setDsEffects(startingRadius + (counter * (endingRadius - startingRadius)) / frames,
                    startingXOffset + (counter * (endingXOffset - startingXOffset)) / frames,
                    startingYOffset + (counter * (endingYOffset - startingYOffset)) / frames, shadowColor);
        } else {
            this.setDsEffects(endingRadius - (counter * (endingRadius - startingRadius)) / frames,
                    endingXOffset - (counter * (endingXOffset - startingXOffset)) / frames,
                    endingYOffset - (counter * (endingYOffset - startingYOffset)) / frames, shadowColor);
        }
    }

    private void animateSize(double counter, boolean grow) {
        if (grow) {
            this.resizeButton((startingWidth + (counter * (endingWidth - startingWidth) / frames)),
                    (startingHeight + (counter * (endingHeight - startingHeight) / frames)));
        } else {
            this.resizeButton((endingWidth - (counter * (endingWidth - startingWidth) / frames)),
                    (endingHeight - (counter * (endingHeight - startingHeight) / frames)));
        }

        if (text) resizeText(buttonText);
        if (image) resizeImage(buttonImage);
    }

    public void animate() {
        if (!animation) return;

        Point pointerLocation = MouseInfo.getPointerInfo().getLocation();

        double x = pointerLocation.getX();
        double y = pointerLocation.getY();

        // animation properties
        boolean onButton = this.containsPoint(x, y);

        if (onButton) {
            if (isShort && !getLonger) {
                if (Preferences.preferences[1] && isButton) {
                    hoverSound.play();
                }
                // the shadow is short and needs to grow
                getLonger = true;
                animateDs(counter, true);
                animateSize(counter, true);

            } else if (isShort) {
                // the shadow is already getting longer
                counter++;
                animateDs(counter, true);
                animateSize(counter, true);

                if (counter >= (int) frames) {
                    counter = 0;
                    getLonger = false;
                    isShort = false;
                }
            } else if (!isShort && getShorter) {
                counter = frames - counter;
                if (counter == frames) counter--;
                isShort = true;
                getLonger = true;
                getShorter = false;
            }
        } else {
            if (!isShort && !getShorter) {
                getShorter = true;
                animateDs(counter, false);
                animateSize(counter, false);

            } else if (!isShort) {
                counter++;
                animateDs(counter, false);
                animateSize(counter, false);

                if (counter >= (int) frames) {
                    counter = 0;
                    getShorter = false;
                    isShort = true;
                }
            } else if (isShort && getLonger) {
                counter = frames - counter;
                if (counter == frames) counter--;
                isShort = false;
                getLonger = false;
                getShorter = true;
            }

        }

        if (text) buttonText.setStroke(textColor);
    }
}
