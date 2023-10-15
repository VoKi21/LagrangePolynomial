package com.cgvsu.protocurvefxapp;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public class LagrangePolynomialDrawer {
    public static void drawInterpolation(List<Point2D> points, GraphicsContext graphicsContext) {
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
        final double WIDTH = 0.5;

        for (double x = minX; x <= maxX; x += STEP) {
            double y = lagrangeInterpolation(points, x);
            graphicsContext.fillOval(x - WIDTH, y - WIDTH, WIDTH * 2, WIDTH * 2);
        }
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
