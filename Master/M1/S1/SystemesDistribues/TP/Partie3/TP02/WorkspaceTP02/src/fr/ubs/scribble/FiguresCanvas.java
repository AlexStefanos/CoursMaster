package fr.ubs.scribble;

import fr.ubs.scribble.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * A canvas used to draw figures, composed of a list of figures and a figure currently drawing
 * <p>
 * A new figure is created when the mouse is pressed, and the location of the mouse is registered
 * (startX, startY). The figure is resized when the mouse is dragged, and it is added to the list of
 * figures when the mouse is released.
 * <p>
 * A figure is selected when the mouse is clicked on this figure, and deselected when the mouse is
 * clicked outside the figure. When a figure is selected, the mouse pointer's appearance changes when
 * moving on the figure, and the figure can be moved by dragging over it or deleted by pressing the
 * 'del' key.
 *
 * @author Pascale Launay
 */
public class FiguresCanvas extends JPanel implements MouseMotionListener, MouseListener, KeyListener, Client
{
    /**
     * the initial width of the canvas
     */
    private static final int WIDTH = 800;

    /**
     * the initial height of the canvas
     */
    private static final int HEIGHT = 600;

    /**
     * the default cursor
     */
    private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    /**
     * the move cursor
     */
    private static final Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);

    /**
     * the figures currently drawn on the canvas
     */
    private final Figures figures;

    /**
     * the figure from the list of figures that is selected (may be null)
     */
    private Figure selectedFigure;

    /**
     * the x coordinate of the mouse location when the mouse is pressed
     */
    private double startX;

    /**
     * the y coordinate of the mouse location when the mouse is pressed
     */
    private double startY;

    /**
     * the x coordinate of the selected figure location when the mouse is pressed
     */
    private double figureStartX;

    /**
     * the y coordinate of the selected figure location when the mouse is pressed
     */
    private double figureStartY;

    /**
     * true when the mouse is currently dragging (either to resize the new figure or to move the selected figure)
     */
    private boolean dragging;

    /**
     * true when the cursor has a 'move' appearance, meaning that the mouse is over a selected figure, and dragging
     * will move the figure
     */
    private boolean moveCursor;

    /**
     * the shape currently selected
     */
    private Shape shape;

    /**
     * the color currently selected
     */
    private Color color;

    /**
     * the scale to apply to draw figures
     */
    private double scale;

    /**
     * the x translation to apply to draw figures
     */
    private double tx;

    /**
     * the y translation to apply to draw figures
     */
    private double ty;

    private Server server;
    private Client client;
    private int id, ctNotify;

    /**
     * Constructor
     */
    public FiguresCanvas(String host, int port) throws Exception {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        this.figures = new Figures();

        // register listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        Registry registry = LocateRegistry.getRegistry(host, port);
        this.server = (Server) registry.lookup("Server");
        ctNotify = 0;
        notifyServer();
    }

    /**
     * Draws the canvas content: all figures and the currently drawing figure if any
     *
     * @param g the graphics context.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            // compute the transform to be applied to the figures
            scale = Math.min(1.0 * width / WIDTH, 1.0 * height / HEIGHT);
            tx = (width - WIDTH * scale) / 2;
            ty = (height - HEIGHT * scale) / 2;

            // draw the background
            g2d.setColor(Color.BLACK);
            g2d.fill(new Rectangle2D.Double(0, 0, width, height));
            g2d.setColor(Color.WHITE);
            g2d.fill(new Rectangle2D.Double(tx, ty, WIDTH * scale, HEIGHT * scale));

            // draw the figures
            figures.draw(g2d, scale, tx, ty);
        }
    }

    /**
     * Set the currently selected shape
     *
     * @param shape the new shape
     */
    public void setShape(Shape shape)
    {
        this.shape = shape;
        requestFocusInWindow();
    }

    /**
     * Set the currently selected color and change the color of the selected figure, if any
     *
     * @param color the new color
     */
    public void setColor(Color color) throws RemoteException
    {
        this.color = color;
        if (this.selectedFigure != null) {
            this.selectedFigure.setColor(color);
            // !!! the figure color has been updated
            try {
                this.server.updateFigure(this.selectedFigure, id);
                notifyServer();
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
            }
            repaint();
        }
        requestFocusInWindow();
    }

    /**
     * Called when the mouse is pressed: either start moving the selected figure (if moveCursor) or start a
     * new figure drawing
     *
     * @param event the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent event)
    {
        requestFocusInWindow();
        double x = getX(event);
        double y = getY(event);
        startX = x;
        startY = y;
        if (!moveCursor) { // create a new figure
            figures.createFigure(shape, color, startX, startY);
        } else { // start moving the selected figure
            figureStartX = selectedFigure.getX();
            figureStartY = selectedFigure.getY();
        }
    }

    /**
     * Called when the mouse is released. Adds the currently drawing figure if any or else select the figure
     * at the mouse location if any
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent event)
    {
        double x = getX(event);
        double y = getY(event);
        if (dragging) { // a figure is currently drawing or moving
            if (!moveCursor) {
                figures.addCurrentFigure();
                // !!! the figure has been added
                try {
                    server.createFigure(this.selectedFigure, id);
                    notifyServer();
                } catch(RemoteException e) {
                    System.err.println(e.getMessage());
                }

            } else {
                // !!! the figure location has been updated
                try {
                    server.updateFigure(this.selectedFigure, id);
                    notifyServer();
                } catch(RemoteException e) {
                    System.err.println(e.getMessage());
                }
            }
        } else { // select the figure at the mouse location or deselect the currently selected figure
            Figure selectedFigure = figures.getFigureAt(x, y);
            select(selectedFigure);
        }
        dragging = false;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released)
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent event)
    {
        // NOTHING
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent event)
    {
        // NOTHING
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent event)
    {
        // NOTHING
    }

    /**
     * Called when the mouse is dragged. Move the selected figure if the cursor has the 'move' appearance
     * or resize the currently drawing figure
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent event)
    {
        double x = getX(event);
        double y = getY(event);
        if (!dragging && (Math.abs(x - startX) > 2 || Math.abs(y - startY) > 2)) {
            // start dragging
            if (!moveCursor) {
                select(null);
            }
            dragging = true;
        }
        if (dragging) {
            if (moveCursor) { // move the selected figure
                selectedFigure.setLocation(figureStartX + x - startX, figureStartY + y - startY);
            } else { // resize the currently drawing figure
                figures.resizeCurrentFigure(x - startX, y - startY);
            }
            repaint();
        }
    }

    /**
     * Called when the mouse is moved (but not dragged). Changes the cursor appearance if the
     * mouse enters or leaves a selected figure ('move' appearance)
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent event)
    {
        double x = getX(event);
        double y = getY(event);
        if (moveCursor && dragging) { // the selected figure is moving. Do not change the cursor
            return;
        }
        Figure figure = figures.getFigureAt(x, y);
        if (!moveCursor && figure != null && figure.isSelected()) { // enters a selected figure
            setCursor(MOVE_CURSOR);
            moveCursor = true;
        } else if (moveCursor && (figure == null || !figure.isSelected())) { // leaves a selected figure
            setCursor(DEFAULT_CURSOR);
            moveCursor = false;
        }
    }

    /**
     * Invoked when a key has been typed.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent event)
    {
        // NOTHING
    }

    /**
     * Invoked when a key has been pressed.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_DELETE && this.selectedFigure != null) {
            figures.remove(this.selectedFigure);
            // !!! the figure has been removed
            try {
                server.deleteFigure(this.selectedFigure, id);
                notifyServer();
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
            }
            this.selectedFigure = null;
            repaint();
        }
    }

    /**
     * Invoked when a key has been released.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent event)
    {
        // NOTHING
    }

    /**
     * Select the given figure (if not null) and unselect the previously selected figure (if any)
     *
     * @param selectedFigure the figure to be selected (may be null)
     */
    private void select(Figure selectedFigure)
    {
        if (selectedFigure == this.selectedFigure) {
            return;
        }
        if (this.selectedFigure != null) {
            // unselect the previously selected figure
            this.selectedFigure.setSelected(false);
            this.selectedFigure = null;
            repaint();
        }
        if (selectedFigure != null) {
            this.selectedFigure = selectedFigure;
            this.selectedFigure.setSelected(true);
            setCursor(MOVE_CURSOR);
            moveCursor = true;
            repaint();
        }
    }
    
    public void notifyServer() throws RemoteException {
        if(ctNotify == 0) {
            System.out.println("Notifying Server Connection");
            id = this.server.getId();
        } else
            System.out.println("Notifying Server");
        ctNotify++;
    }

//    public void updateFigures(Figures figures) throws RemoteException {
//        for(int i = 0; i < this.figures.size(); i++) {
//            for(int j = 0; j < figures.size(); j++) {
//                if(figures.get(j).getId() != this.figures.get(i).getId()) {
//                    this.figures.add(figures.get(i));
//                }
//            }
//        }
//    }

    public Figures getFigures() throws RemoteException {
        return(this.figures);
    }

    /**
     * Give the x location of the mouse represented by the given mouse event after applying the transform
     * applied to the canvas
     *
     * @param event the mouse event
     * @return the x location of the mouse
     */
    private double getX(MouseEvent event)
    {
        return (event.getX() - tx) / scale;
    }

    /**
     * Give the y location of the mouse represented by the given mouse event after applying the transform
     * applied to the canvas
     *
     * @param event the mouse event
     * @return the y location of the mouse
     */
    private double getY(MouseEvent event)
    {
        return (event.getY() - ty) / scale;
    }
}
