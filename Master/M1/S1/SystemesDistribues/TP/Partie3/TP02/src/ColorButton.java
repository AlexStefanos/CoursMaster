package fr.ubs.scribble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

/**
 * A button used as a color selector
 *
 * @author Pascale Launay
 */
public class ColorButton extends JButton
{
    public static final String ACTION_COLOR = "color";

    /**
     * the button color
     */
    private Color color;

    /**
     * Constructor
     *
     * @param color the button color
     */
    public ColorButton(Color color)
    {
        this.color = color;
        this.setLayout(new BorderLayout(5, 5));
        this.add(new JLabel("Select color"), BorderLayout.CENTER);
        this.add(new ColorCanvas(), BorderLayout.WEST);
        addActionListener(event -> chooseColor(event));
    }

    public Color getColor()
    {
        return this.color;
    }

    private void chooseColor(ActionEvent event)
    {
        if (!event.getActionCommand().equals(ACTION_COLOR)) {
            Color color = JColorChooser.showDialog(this, "Select color", this.color);
            if (color != null && !this.color.equals(color)) {
                this.color = color;
                repaint();
                for (ActionListener listener : getActionListeners()) {
                    listener.actionPerformed(new ActionEvent(this, 0, ACTION_COLOR));
                }
            }
        }
    }

    /**
     * Canvas where the image of the shape is displayed
     */
    class ColorCanvas extends JPanel
    {
        /**
         * Constructor
         */
        public ColorCanvas()
        {
            setPreferredSize(new Dimension(12, 12));
        }

        @Override
        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.fill(new Rectangle2D.Double(0, 0, 12, 12));
        }
    }
}
