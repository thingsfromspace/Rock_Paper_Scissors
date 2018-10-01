package gui.gui_elements;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;

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

    // CONSTRUCTORS
    private mdButton() {
    }

    public mdButton(double width, double height) {
        constructRectangle(this, width, height, 0, 0,
                buttonColor);
        roundCorners(20, 20);

        startingWidth = width;
        startingHeight = height;
        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
    }

    public mdButton(double width, double height, Color fill) {
        this.buttonColor = fill;
        constructRectangle(this, width, height, 0, 0, fill);
        roundCorners(20, 20);

        startingWidth = width;
        startingHeight = height;

        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
    }

    public mdButton(double width, double height, double x, double y) {
        constructRectangle(this, width, height, x, y,
                buttonColor);
        roundCorners(20, 20);

        startingWidth = width;
        startingHeight = height;

        endingWidth = startingWidth * 1.1;
        endingHeight = startingHeight * 1.1;
    }

    public mdButton(double width, double height, double x, double y, Color fill) {
        this.buttonColor = fill;
        constructRectangle(this, width, height, x, y, fill);
        roundCorners(20, 20);

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

    // SETTERS

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

    // BASIC METHODS
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

    public void setButtonColor(Color color) {
        this.buttonColor = color;
    }

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

    private void setSize(double width, double height) {
        this.setX(this.getX() - (width - this.getWidth()) / 2);
        this.setY(this.getY() - (height - this.getHeight()) / 2);

        this.setWidth(width);
        this.setHeight(height);
    }

    public void setDropShadow() {
        this.setEffect(this.dropShadow);
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
            this.setSize((startingWidth + (counter * (endingWidth - startingWidth) / frames)),
                    (startingHeight + (counter * (endingHeight - startingHeight) / frames)));
        } else {
            this.setSize((endingWidth - (counter * (endingWidth - startingWidth) / frames)),
                    (endingHeight - (counter * (endingHeight - startingHeight) / frames)));
        }
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
    }
}
