package fr.ubs.scribble;

import fr.ubs.scribble.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Application entry point
 */
public class ScribbleFrame extends JFrame
{

    /**
     * Print a usage message and exit
     */
    private static void usage()
    {
        System.out.println("Usage: java ScribbleFrame");
        System.exit(-1);
    }

    /**
     * Launch the JavaFX app
     *
     * @param args NONE
     */
    public static void main(String[] args)
    {
        if (args.length > 0) {
            usage();
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    ScribbleFrame frame = new ScribbleFrame();
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor. Initialize the frame widgets
     */
    public ScribbleFrame() throws Exception
    {
        super("Scribble Pad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(5, 5));
        FiguresCanvas canvas = new FiguresCanvas();
        add(makeToolbar(canvas), BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    /**
     * Create the toolbar that contains the selectors for shapes and colors
     *
     * @param canvas the canvas to be notified when a new shape or color is selected
     * @return the toolbar
     */
    private JPanel makeToolbar(FiguresCanvas canvas)
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.setBackground(Color.WHITE);

        // initialize the shapes list
        Set<Class<?>> classes = listPackage(Shape.class.getPackageName());
        Set<Shape> instances = makeShapes(classes);
        Shape[] shapes = new Shape[instances.size()];
        instances.toArray(shapes);

        // initialize the shape selector
        JComboBox<Shape> shapeSelector = new JComboBox<>(shapes);
        shapeSelector.setRenderer((list, shape, index, isSelected, cellHasFocus) -> new ShapeCell(shape));
        panel.add(shapeSelector);
        shapeSelector.addItemListener(event -> canvas.setShape((Shape) shapeSelector.getSelectedItem()));
        canvas.setShape(shapes[0]);

        // initialize the color selector
        ColorButton colorSelector = new ColorButton(new Color(0, 0, 0x8b));
        colorSelector.addActionListener(event -> {
            if (event.getActionCommand().equals(ColorButton.ACTION_COLOR)) {
                canvas.setColor(colorSelector.getColor());
            }
        });
        canvas.setColor(colorSelector.getColor());
        panel.add(colorSelector);

        return panel;
    }

    /**
     * Create instances of the given classes and return them if they are Shape instances
     *
     * @param classes classes in the same package as Shape
     * @return Shape instances
     */
    private Set<Shape> makeShapes(Set<Class<?>> classes)
    {
        Set<Shape> shapes = new HashSet<>();
        for (Class<?> clazz : classes) {
            try {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                if (obj instanceof Shape) {
                    shapes.add((Shape) obj);
                }
            } catch (Exception e) {
                // IGNORE
            }
        }
        return shapes;
    }

    /**
     * Give the classes in the given package from the system classpath
     *
     * @param packagename the name of the package
     * @return the classes found in the system classpath
     */
    private Set<Class<?>> listPackage(String packagename)
    {
        String classpath = System.getProperty("java.class.path");
        System.out.println("CLASSPATH: " + classpath);

        Set<String> names = new HashSet<>();
        Set<Class<?>> classes = new HashSet<>();
        try {
            for (String path : classpath.split(System.getProperty("path.separator"))) {
                File base = new File(path);
                String packagepath = packagename.replaceAll("[.]", "/");
                if (base.isDirectory()) {
                    File dir = new File(path + File.separator + packagepath);
                    if (dir.isDirectory()) {
                        for (File file : dir.listFiles((dir1, name) -> name.endsWith(".class"))) {
                            names.add(packagename + "." + file.getName());
                        }
                    }
                } else if (base.isFile() && base.getName().endsWith(".jar")) {
                    try (ZipInputStream in = new ZipInputStream(new FileInputStream(base))) {
                        ZipEntry entry = in.getNextEntry();
                        while (entry != null) {
                            if (entry.getName().startsWith(packagepath) && entry.getName().endsWith(".class")) {
                                names.add(entry.getName().replaceAll("/", "."));
                            }
                            entry = in.getNextEntry();
                        }
                    }
                }
            }
            for (String name : names) {
                name = name.substring(0, name.length() - 6);
                classes.add(ClassLoader.getSystemClassLoader().loadClass(name));
            }
            if (classes.isEmpty()) {
                System.err.println("No shapes found in your classpath: " + classpath);
                System.exit(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
}
