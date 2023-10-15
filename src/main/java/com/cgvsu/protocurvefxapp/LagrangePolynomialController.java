package com.cgvsu.protocurvefxapp;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LagrangePolynomialController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    ArrayList<Point2D> points = new ArrayList<>();

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        canvas.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryClick(event);
                case SECONDARY -> handleSecondaryClick(event);
            }
            redraw(canvas.getGraphicsContext2D());
        });
    }

    private void handlePrimaryClick(MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());
        points.add(clickPoint);
    }

    private void handleSecondaryClick(MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());

        final int DELETE_RADIUS = 10;

        points.removeIf(point -> Math.abs(point.getX() - clickPoint.getX()) <= DELETE_RADIUS && Math.abs(point.getY() - clickPoint.getY()) <= DELETE_RADIUS);
    }

    private void redraw(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        final int POINT_RADIUS = 3;
        LagrangePolynomialDrawer.drawInterpolation(points, canvas.getGraphicsContext2D(), Color.BLUE, Color.RED);
        graphicsContext.setFill(Color.BLACK);
        for (Point2D point : points) {
            graphicsContext.fillOval(
                    point.getX() - POINT_RADIUS, point.getY() - POINT_RADIUS,
                    2 * POINT_RADIUS, 2 * POINT_RADIUS);
        }
    }
}