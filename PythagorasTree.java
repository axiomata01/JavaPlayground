import java.util.Random;

/**
 * Rekursiver Pythagoras-Baum mit zufälligem Winkel α.
 * Zeichnet auf der oberen Seite jedes Quadrats zwei kleinere Quadrate.
 * @author Taisiia Dobrozorova
 * @author Xiang Lan
 * @author Ying Chen
 * @version 1.0
 */
public class PythagorasTree {

    private static final Random rand = new Random();

    /**
     * Zeichnet rekursiv einen Pythagoras-Baum.
     * @param x0 linke untere x-Koordinate des Quadrats
     * @param y0 linke untere y-Koordinate des Quadrats
     * @param size Seitenlänge des Quadrats
     * @param angle Drehwinkel in Radiant
     * @param depth aktuelle Rekursionstiefe
     * @param n maximale Rekursionstiefe
     */
    public static void drawTree(double x0, double y0,
                                double size, double angle,
                                int depth, int n) {

        if (depth >= n) return;

        double ux = size * Math.cos(angle);
        double uy = size * Math.sin(angle);
        double vx = -size * Math.sin(angle);
        double vy =  size * Math.cos(angle);

        double x1 = x0 + ux;
        double y1 = y0 + uy;
        double x2 = x1 + vx;
        double y2 = y1 + vy;
        double x3 = x0 + vx;
        double y3 = y0 + vy;

        StdDraw.line(x0, y0, x1, y1);
        StdDraw.line(x1, y1, x2, y2);
        StdDraw.line(x2, y2, x3, y3);
        StdDraw.line(x3, y3, x0, y0);

        double alpha = Math.PI / 6 + rand.nextDouble() * (Math.PI / 3 - Math.PI / 6);

        double sizeLeft  = size * Math.cos(alpha);
        double sizeRight = size * Math.sin(alpha);

        // Linkes Quadrat
        double lx = x3;
        double ly = y3;
        double langle = angle + alpha;
        drawTree(lx, ly, sizeLeft, langle, depth + 1, n);

        // Rechtes Quadrat, startet am oberen rechten Eckpunkt des linken Quadrats
        double rx = lx + sizeLeft * Math.cos(langle);
        double ry = ly + sizeLeft * Math.sin(langle);
        double rangle = langle - Math.PI / 2;
        drawTree(rx, ry, sizeRight, rangle, depth + 1, n);
    }

    /**
     * Main-Methode, startet die Zeichnung.
     * @param args optional: Rekursionstiefe n
     */
    public static void main(String[] args) {

        int n = (args.length > 0) ? Integer.parseInt(args[0]) : 5;

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.clear();

        drawTree(0.45, 0.0, 0.1, 0.0, 0, n);

        StdDraw.show();
    }
}
