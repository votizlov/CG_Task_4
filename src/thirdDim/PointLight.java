package thirdDim;

import math.Vector3;

import java.awt.*;

public class PointLight {
    public PointLight(Vector3 pos, Color color, double intensity) {
        this.pos = pos;
        this.color = color;
        this.intensity = intensity;
    }

    private Vector3 pos;
    private Color color;

    public Vector3 getPos() {
        return pos;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    private double intensity;
}
