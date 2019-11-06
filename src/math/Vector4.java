package math;

import static java.lang.Math.abs;

public class Vector4 {

    public Vector4 normalized(){
        if (abs(this.getW()) < 1e-12) {
            return new Vector4(new double[]{this.getX(), this.getY(), this.getZ()});
        } else {
            return new Vector4(crd = new double[]{
                    this.getX() / this.getW(),
                    this.getY() / this.getW(),
                    this.getZ() / this.getW(),
                    1
            });
        }
    }

    public Vector4 mul(double n){
        return new Vector4(
                this.getX()*n,
                this.getY()*n,
                this.getZ()*n,
                this.getW()*n
        );
    }

    public static Vector4 zero(){
        return new Vector4(0,0,0,0);
    }

    public double getZ() {
        return crd[2];
    }

    public double getY() {
        return crd[1];
    }

    public double getX() {
        return crd[0];
    }

    public double getW() {
        return crd[3];
    }

    public Vector4(double x, double y, double z, double w) {
        crd = new double[]{x, y, z, w};
    }

    public Vector4(Vector3 v, double w) {
        this(v.getX(), v.getY(), v.getZ(), w);
    }

    public Vector4(Vector3 v) {
        this(v, 0);
    }

    public Vector4(double[] crd) {
        this.crd = crd;
    }

    private double[] crd;

}
