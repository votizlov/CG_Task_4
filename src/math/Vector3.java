package math;

import static java.lang.Math.abs;

public class Vector3 {
    public double getZ() {
        return crd[2];
    }

    public double getY() {
        return crd[1];
    }

    public double getX() {
        return crd[0];
    }

    public Vector3(double x, double y, double z) {
        crd = new double[]{x, y, z};
    }

    public Vector3(double[] crd) {
        this.crd = crd;
    }

    public Vector3(Vector4 v) {
        if (abs(v.getW()) < 1e-12) {
            crd = new double[]{v.getX(), v.getY(), v.getZ()};
        } else {
            crd = new double[]{
                    v.getX() / v.getW(),
                    v.getY() / v.getW(),
                    v.getZ() / v.getW(),
            };
        }
    }

    private double[] crd;

}
