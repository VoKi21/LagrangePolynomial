package ru.vsu.cs.cg.g8_1.kilchenko_v_p.lagrangepolynomial;

import javafx.scene.paint.Color;

public class ColorHelper {
    private final Color startColor;
    private final double[] colorShift;

    public ColorHelper(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.colorShift = new double[4];
        this.colorShift[0] = startColor.getRed() - endColor.getRed();
        this.colorShift[1] = startColor.getGreen() - endColor.getGreen();
        this.colorShift[2] = startColor.getBlue() - endColor.getBlue();
        this.colorShift[3] = startColor.getOpacity() - endColor.getOpacity();
    }

    public Color getColorInPoint(double part) {
        return new Color(
                startColor.getRed() - colorShift[0] * part,
                startColor.getGreen() - colorShift[1] * part,
                startColor.getBlue() - colorShift[2] * part,
                startColor.getOpacity() - colorShift[3] * part
        );
    }
}
