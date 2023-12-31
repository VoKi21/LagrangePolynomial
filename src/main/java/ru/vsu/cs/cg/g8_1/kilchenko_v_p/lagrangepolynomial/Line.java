package ru.vsu.cs.cg.g8_1.kilchenko_v_p.lagrangepolynomial;

import javafx.geometry.Point2D;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Line {
    private static int normalize(double n) {
        return (int) Math.max(Math.min(n, 3000), -1);
    }
    public static void lineFromTo(Point2D from, Point2D to, Color startColor, Color endColor, PixelWriter pixelWriter) {
        int x0 = normalize(from.getX());
        int y0 = normalize(from.getY());
        int x1 = normalize(to.getX());
        int y1 = normalize(to.getY());

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        int error = dx - dy;

        final ColorHelper colorHelper = new ColorHelper(startColor, endColor);
        final double length = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));

        while (x0 != x1 || y0 != y1) {
            double currentPart = (length - Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2))) / length;
            Color currentColor = colorHelper.getColorInPoint(currentPart);
            pixelWriter.setColor(x0, y0, currentColor);

            int error2 = 2 * error;

            if (error2 > -dy) {
                error -= dy;
                x0 += sx;
            }

            if (error2 < dx) {
                error += dx;
                y0 += sy;
            }
        }
    }
}
