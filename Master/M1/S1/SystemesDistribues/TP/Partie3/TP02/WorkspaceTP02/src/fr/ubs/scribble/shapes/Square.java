package fr.ubs.scribble.shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A square
 *
 * @author Pascale Launy
 */
public class Square extends Shape
{
    /**
     * Constructor
     */
    public Square()
    {
        super("Square");
    }

    @Override
    public Rectangle2D getBoundingBox(double x, double y, double width, double height)
    {
        double size = Math.max(width, height);
        return new Rectangle2D.Double(x, y, size, size);
    }

    @Override
    public void draw(Graphics2D g2d, double x, double y, double width, double height)
    {
        double size = Math.max(width, height);
        g2d.fill(new Rectangle2D.Double(x, y, size, size));
    }

    public void drawIcon(Graphics2D g2d)
    {
        g2d.draw(new Rectangle2D.Double(7, 1, 10, 10));
    }
}
