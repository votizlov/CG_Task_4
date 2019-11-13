package math;

public class Vector3 {

    private double[] crd;

    public Vector3(double x, double y, double z) {
        crd = new double[]{x, y, z};
    }

    public Vector3(Vector4 v) {
        if(Math.abs(v.getW()) < 1e-10) {
            crd = new double[]{v.getX(), v.getY(), v.getZ()};
        } else {
            crd = new double[]{
                    v.getX() / v.getW(),
                    v.getY() / v.getW(),
                    v.getZ() / v.getW()
            };
        }
    }


    public double getX() {
        return crd[0];
    }

    public double getY() {
        return crd[1];
    }

    public double getZ() {
        return crd[2];
    }

    public double at(int index) {
        return crd[index];
    }

}
