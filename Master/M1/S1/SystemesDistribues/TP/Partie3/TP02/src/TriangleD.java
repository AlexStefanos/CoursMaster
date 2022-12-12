package fr.ubs.scribble.shapes;

import java.awt.*;

/**
 * An isosceles down triangle
 *
 * @author Pascale Launay
 */
public class TriangleD extends Shape
{
    /**
     * Constructor
     */
    public TriangleD()
    {
        super("Down triangle");
    }

    @Override
    public void draw(Graphics2D g2d, double x, double y, double width, double height)
    {
        int[] xs = {(int) x, (int) (x + width/2), (int) (x + width)};
        int[] ys = {(int) y, (int) (y + height), (int) y};
        g2d.fillPolygon(xs, ys, 3);
    }

    @Override
    public void drawIcon(Graphics2D g2d)
    {
        int[] xs = { 5, 12, 19 };
        int[] ys = { 1, 11, 1 };
        g2d.drawPolygon(xs, ys, 3);
    }
}
