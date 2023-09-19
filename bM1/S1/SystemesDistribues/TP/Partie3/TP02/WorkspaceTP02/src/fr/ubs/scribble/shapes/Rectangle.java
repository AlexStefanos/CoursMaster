package fr.ubs.scribble.shapes;


import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A rectangle
 *
 * @author Pascale Launy
 */
public class Rectangle extends Shape
{
    /**
     * Constructor
     */
    public Rectangle()
    {
        super("Rectangle");
    }

    @Override
    public void draw(Graphics2D g2d, double x, double y, double width, double height)
    {
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
    }

    public void drawIcon(Graphics2D g2d)
    {
        g2d.draw(new Rectangle2D.Double(1, 1, 22, 10));
    }
}
