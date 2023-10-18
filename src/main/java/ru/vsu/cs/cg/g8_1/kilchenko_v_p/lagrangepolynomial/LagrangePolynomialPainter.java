package ru.vsu.cs.cg.g8_1.kilchenko_v_p.lagrangepolynomial;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class LagrangePolynomialPainter {
    public static void drawInterpolation(
            List<Point2D> points,
            GraphicsContext graphicsContext,
            Color startColor,
            Color endColor
    ) {
        if (points.size() < 2) {
            return;
        }

        double minX = points.get(0).getX();
        double maxX = points.get(0).getX();
        for (Point2D point : points) {
            minX = Math.min(minX, point.getX());
            maxX = Math.max(maxX, point.getX());
        }

        final double STEP = 0.5;
        final double DISTANCE = maxX - minX;
        final ColorHelper colorHelper = new ColorHelper(startColor, endColor);

        Point2D prevPoint = new Point2D(minX, lagrangeInterpolation(points, minX));
        Color prevColor = startColor;
        for (double x = minX; x <= maxX; x += STEP) {
            double y = lagrangeInterpolation(points, x);
            double currentPart = (x - minX) / DISTANCE;
            Color currentColor = colorHelper.getColorInPoint(currentPart);
            Line.lineFromTo(prevPoint, new Point2D(x, y), prevColor, currentColor, graphicsContext);
            prevColor = currentColor;
            prevPoint = new Point2D(x, y);
        }
    }

    public static void drawInterpolation(List<Point2D> points, GraphicsContext graphicsContext, Color color) {
        drawInterpolation(points, graphicsContext, color, color);
    }

    public static void drawInterpolation(List<Point2D> points, GraphicsContext graphicsContext) {
        drawInterpolation(points, graphicsContext, Color.BLACK);
    }

    private static double lagrangeInterpolation(List<Point2D> points, double x) {
        double result = 0.0;

        for (int i = 0; i < points.size(); i++) {
            // current term = x.i
            double term = points.get(i).getY();

            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    // current term *= (x - x.j) / (x.i - x.j)
                    term *= (x - points.get(j).getX()) / (points.get(i).getX() - points.get(j).getX());
                }
            }
            // L(x) += current term
            result += term;
        }

        return result;
    }
}
