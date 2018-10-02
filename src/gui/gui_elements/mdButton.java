package gui.gui_elements;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SuppressWarnings("unused")
public class mdButton extends Rectangle {

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

    public mdButton(double width, double height) {
        constructRectangle(this, width, height, 0, 0,
                buttonColor);
        roundCorners(15, 15);

        startingWidth = width;
        startingHeight = height;
        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
    }
    public mdButton(double width, double height, Color fill) {
        this.buttonColor = fill;
        constructRectangle(this, width, height, 0, 0, fill);
        roundCorners(15, 15);

        startingWidth = width;
        startingHeight = height;

        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
    }
    public mdButton(double width, double height, double x, double y) {
        constructRectangle(this, width, height, x, y,
                buttonColor);
        roundCorners(15, 15);

        startingWidth = width;
        startingHeight = height;

        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
    }
    public mdButton(double width, double height, double x, double y, Color fill) {
        this.buttonColor = fill;
        constructRectangle(this, width, height, x, y, fill);
        roundCorners(15, 15);

        startingWidth = width;
        startingHeight = height;

        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
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

    public void setDropShadow() {
        this.setEffect(this.dropShadow);
    }

    public void setButtonColor(Color color) {
        this.buttonColor = color;
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

    public void setDsEffects(double radius, double xoffset, double yoffset, Color color) {
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

        EventHandler<MouseEvent> mouseClickHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (rect.contains(new Point2D(e.getX(), e.getY()))) {
                    timesClicked++;
                }
            }
        };

        rect.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseClickHandler);
    }

    private void roundCorners(@SuppressWarnings("SameParameterValue") double arcWidth, @SuppressWarnings("SameParameterValue") double arcHeight) {
        this.setArcHeight(arcHeight);
        this.setArcWidth(arcWidth);
    }

    public Text addText(String text, Font font, Color color) {
        Text buttonText = new Text(text);
        buttonText.setFont(font);
        buttonText.setStroke(color);
        this.textColor = color;
        buttonText.setTextOrigin(VPos.TOP);

        buttonText.setX(this.getX() + (this.getWidth() - buttonText.getBoundsInLocal().getWidth()) / 2);
        buttonText.setY(buttonText.getBoundsInLocal().getHeight() / 9 + this.getY() + (this.getHeight() - buttonText.getBoundsInLocal().getHeight()) / 2);

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
        Point pointerLocation = MouseInfo.getPointerInfo().getLocation();

        double x = pointerLocation.getX();
        double y = pointerLocation.getY();

        // animation properties
        boolean onButton = this.containsPoint(x, y);

        if (onButton) {
            if (isShort && !getLonger) {
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
