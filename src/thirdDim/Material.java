package thirdDim;

import java.awt.*;

public class Material {
    private Color color;
    private double reflectivity;

    public Color getColor() {
        return color;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public double getAbsorption() {
        return absorption;
    }

    private double absorption;

    public Material(Color color, double reflectivity, double absorption) {
        this.color = color;
        this.reflectivity = reflectivity;
        this.absorption = absorption;
    }
}
