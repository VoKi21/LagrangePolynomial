package ru.vsu.cs.cg.g8_1.kilchenko_v_p.lagrangepolynomial;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line {
    public static void lineFromTo(Point2D from, Point2D to, Color startColor, Color endColor, GraphicsContext graphicsContext) {
        int dx = (int) Math.abs(from.getX() - to.getX());
        int dy = (int) Math.abs(from.getY() - to.getY());
        int error = 0;
        int derror = dy + 1;
        int y = (int) from.getY();
        final int WIDTH = 1;
        int diry = (int) Math.signum(to.getY() - from.getY());
        final int STEP = 1;
        final ColorHelper colorHelper = new ColorHelper(startColor, endColor);
        for (int x = (int) from.getX(); x <= to.getX(); x += STEP) {
            double currentPart = Math.abs(x - from.getX()) / Math.abs(to.getX() - from.getX());
            Color currentColor = colorHelper.getColorInPoint(currentPart);
            //graphicsContext.setFill(currentColor);
            graphicsContext.getPixelWriter().setColor(x, y, currentColor);
            //graphicsContext.fillRect(x - WIDTH, y - WIDTH, WIDTH * 2, WIDTH * 2);
            error += derror;
            if (error >= dx + 1) {
                y += diry;
                error -= dx + 1;
            }
        }
    }
}
