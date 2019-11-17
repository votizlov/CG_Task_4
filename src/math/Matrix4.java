package math;

public class Matrix4 {
    private double[] matrix;

    public Matrix4(double[][] m) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i * 4 + j] = m[i][j];
            }
        }
    }

    private Matrix4(double[] arr) {
        matrix = arr;
    }

    public double getAt(int i, int j) {
        return matrix[i * 4 + j];
    }

    public void setAt(int i, int j, double v) {
        matrix[i * 4 + j] = v;
    }

    public Matrix4 mul(double num) {
        double[] arr = new double[16];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[i] * num;
        }
        return new Matrix4(arr);
    }

    public static Matrix4 zero() {
        return new Matrix4(new double[16]);
    }

    public static Matrix4 one() {
        Matrix4 m = zero();
        for (int i = 0; i < 4; i++) {
            m.setAt(i, i, 1);
        }
        return m;
    }

    public Vector4 mul(Vector4 v) {
        double[] r = new double[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                r[i] += v.at(j) * getAt(i, j);
            }
        }
        return new Vector4(r[0], r[1], r[2], r[3]);
    }

    public Matrix4 mul(Matrix4 m) {
        Matrix4 r = Matrix4.zero();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++)
                    r.setAt(i, j, r.getAt(i, j) + this.getAt(i, k) * m.getAt(k, j));
                return r;
    }

    public static Matrix4 rotate(double angle, int axis) {
        Matrix4 m = Matrix4.one();
        int a1 = (axis + 1) % 3;
        int a2 = (axis + 2) % 3;

        m.setAt(a1, a1, Math.cos(angle));
        m.setAt(a1, a2, Math.sin(angle));
        m.setAt(a2, a1, -Math.sin(angle));
        m.setAt(a2, a2, Math.cos(angle));

        return m;
    }

    public static Matrix4 move(Vector3 v){
        Matrix4 m = Matrix4.one();

        m.setAt(2,0,v.getX());
        m.setAt(2,1,v.getY());
        m.setAt(2,2,v.getZ());

        return m;
    }

    public void add(Matrix4 matrix4){//todo vibe check
        for (int i = 0;i<4;i++){
            for (int j = 0;j<4;j++){
                this.setAt(i,j,this.getAt(i,j)+matrix4.getAt(i,j));
            }
        }
    }

}
