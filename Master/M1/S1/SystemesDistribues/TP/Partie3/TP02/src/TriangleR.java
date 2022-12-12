package fr.ubs.scribble.shapes;

import java.awt.*;

/**
 * An isosceles right triangle
 *
 * @author Pascale Launay
 */
public class TriangleR extends Shape
{
    /**
     * Constructor
     */
    public TriangleR()
    {
        super("Right triangle");
    }

    @Override
    public void draw(Graphics2D g2d, double x, double y, double width, double height)
    {
        int[] xs = {(int) x, (int) (x + width), (int) x};
        int[] ys = {(int) y, (int) (y + height / 2), (int) (y + height)};
        g2d.fillPolygon(xs, ys, 3);
    }

    @Override
    public void drawIcon(Graphics2D g2d)
    {
        int[] xs = { 5, 19, 5 };
        int[] ys = { 1, 6, 11 };
        g2d.drawPolygon(xs, ys, 3);
    }
}
