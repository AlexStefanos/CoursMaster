package fr.ubs.scribble.shapes;

import java.awt.*;

/**
 * An isosceles left triangle
 *
 * @author Pascale Launay
 */
public class TriangleL extends Shape
{
    /**
     * Constructor
     */
    public TriangleL()
    {
        super("Left triangle");
    }

    @Override
    public void draw(Graphics2D g2d, double x, double y, double width, double height)
    {
        int[] xs = {(int) x, (int) (x + width), (int) (x + width)};
        int[] ys = {(int) (y + height / 2), (int) y, (int) (y + height)};
        g2d.fillPolygon(xs, ys, 3);
    }

    @Override
    public void drawIcon(Graphics2D g2d)
    {
        int[] xs = { 5, 19, 19 };
        int[] ys = { 6, 1, 11 };
        g2d.drawPolygon(xs, ys, 3);
    }
}
