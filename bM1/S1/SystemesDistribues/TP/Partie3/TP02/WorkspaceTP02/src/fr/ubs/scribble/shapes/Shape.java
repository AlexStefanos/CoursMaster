package fr.ubs.scribble.shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Generic definition of a shape, that provide methods to draw a figure and an icon
 * that represents the figure
 *
 * @author Pascale Launay
 */
public abstract class Shape implements Serializable
{
    /**
     * the name of the shape
     */
    private final String name;

    /**
     * Constructor
     *
     * @param name the name of the shape
     */
    protected Shape(String name)
    {
        this.name = name;
    }

    /**
     * Give the name of the shape
     *
     * @return the name of the shape
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Give the shape bounding box for the given area
     *
     * @param x      the shape x location
     * @param y      the shape y location
     * @param width  the shape width
     * @param height the shape height
     * @return a rectangle that contains the shape
     */
    public Rectangle2D getBoundingBox(double x, double y, double width, double height)
    {
        return new Rectangle2D.Double(x, y, width, height);
    }

    /**
     * Draw the shape
     *
     * @param g2d    graphics context used to draw the shape
     * @param x      the shape x location
     * @param y      the shape y location
     * @param width  the shape width
     * @param height the shape height
     */
    public abstract void draw(Graphics2D g2d, double x, double y, double width, double height);

    /**
     * draw an icon that represents the shape in a 24x12 area
     *
     * @param g2d graphics context used to draw the icon
     */
    public abstract void drawIcon(Graphics2D g2d);
}
