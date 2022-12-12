package fr.ubs.scribble;

import fr.ubs.scribble.shapes.Shape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A figure to be drawn. Composed of a shape, a color, a location and a size
 *
 * @author Pascale Launay
 */
public class Figure implements Serializable
{
    /**
     * the last unique id
     */
    private static int LAST_ID;

    /**
     * the shape of the figure
     */
    private final Shape shape;

    /**
     * figure unique identifier
     */
    private int id;

    /**
     * the color of the figure
     */
    private Color color;

    /**
     * the x location of the upper left point of the figure bounding box
     */
    private double x;

    /**
     * the y location of the upper left point of the figure bounding box
     */
    private double y;

    /**
     * the width of the figure bounding box
     */
    private double width;

    /**
     * the height of the figure bounding box
     */
    private double height;

    /**
     * true if the figure is selected
     */
    private boolean selected;

    /**
     * Constructor
     *
     * @param shape the shape of the figure
     * @param color the color of the figure
     */
    public Figure(Shape shape, Color color)
    {
        this.id = ++LAST_ID;
        this.shape = shape;
        this.color = color;
    }

    /**
     * Give the figure unique id
     *
     * @return the figure unique id
     */
    public int getId()
    {
        return id;
    }

    /**
     * true if the figure has a null width or height
     *
     * @return true if the figure is empty
     */
    public boolean isEmpty()
    {
        return width == 0 || height == 0;
    }

    /**
     * Check whether the given point is inside the bounding box of the figure
     *
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return true if the point is inside the figure
     */
    public boolean isInside(double x, double y)
    {
        Rectangle2D rect = shape.getBoundingBox(this.x, this.y, this.width, this.height);
        return x >= rect.getX() && x <= rect.getX() + rect.getWidth() &&
                y >= rect.getY() && y <= rect.getY() + rect.getHeight();
    }

    /**
     * Check whether the figure is selected
     *
     * @return true if the figure is selected
     */
    public boolean isSelected()
    {
        return this.selected;
    }

    /**
     * Change the selected state of the figure
     *
     * @param selected true if the figure is selected
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    /**
     * Give the x location of the upper left point of the figure bounding box
     *
     * @return x location of the figure
     */
    public double getX()
    {
        return x;
    }

    /**
     * Give the y location of the upper left point of the figure bounding box
     *
     * @return y location of the figure
     */
    public double getY()
    {
        return y;
    }

    /**
     * Give the color of the figure
     *
     * @return the color of the figure
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Change the location of the figure
     *
     * @param x x location of the upper left point of the figure bounding box
     * @param y y location of the upper left point of the figure bounding box
     */
    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Change the figure color
     *
     * @param color the new figure color
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Change the size of the figure
     *
     * @param width  width of the figure bounding box
     * @param height height of the figure bounding box
     */
    public void setSize(double width, double height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * Draw the figure using the given graphics context
     *
     * @param g2d   the graphics context
     * @param scale the scale to apply to draw figures
     * @param tx    the x translation to apply to draw figures
     * @param ty    the y translation to apply to draw figures
     */
    public void draw(Graphics2D g2d, double scale, double tx, double ty)
    {
        g2d.setColor(color);
        shape.draw(g2d, x * scale + tx, y * scale + ty, width * scale, height * scale);
        if (selected) {
            drawSelection(g2d, scale, tx, ty);
        }
    }

    /**
     * Draw small squares around the figure that show that the figure is selected
     *
     * @param g2d   the graphics context
     * @param scale the scale to apply to draw figures
     * @param tx    the x translation to apply to draw figures
     * @param ty    the y translation to apply to draw figures
     */
    private void drawSelection(Graphics2D g2d, double scale, double tx, double ty)
    {
        Rectangle2D rect = shape.getBoundingBox(x, y, width, height);
        g2d.setColor(new Color(0, 0, 0x8b));
        g2d.setStroke(new BasicStroke(.5f));
        double x0 = rect.getX() - 2, x1 = (x0 + rect.getWidth() / 2) * scale + tx, x2 = (x0 + rect.getWidth()) * scale + tx;
        double y0 = rect.getY() - 2, y1 = (y0 + rect.getHeight() / 2) * scale + ty, y2 = (y0 + rect.getHeight()) * scale + ty;
        x0 = x0 * scale + tx;
        y0 = y0 * scale + ty;
        double[][] points = {{x0, y0}, {x0, y1}, {x0, y2}, {x1, y0}, {x1, y2}, {x2, y0}, {x2, y1}, {x2, y2}};
        for (double[] p : points) {
            g2d.draw(new Rectangle2D.Double(p[0], p[1], 4, 4));
        }
    }

    @Override
    public String toString()
    {
        return shape.getName() + " " + x + "," + y + " " + width + "x" + height;
    }
}
