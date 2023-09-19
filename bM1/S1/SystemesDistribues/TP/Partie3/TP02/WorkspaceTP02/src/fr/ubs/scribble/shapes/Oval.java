package fr.ubs.scribble.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * An oval
 *
 * @author Pascale Launy
 */
public class Oval extends Shape
{
    /**
     * Constructor
     */
    public Oval()
    {
        super("Oval");
    }

    @Override
    public void draw(Graphics2D g2d, double x, double y, double width, double height)
    {
        g2d.fill(new Ellipse2D.Double(x, y, width, height));
    }

    public void drawIcon(Graphics2D g2d)
    {
        g2d.draw(new Ellipse2D.Double(1, 1, 22, 10));
    }
}
