package main.third;

import main.math.*;

public class LookAtCamera implements ICamera {
    private Matrix4 translate, rotate, scale, projection, view, camera;
    //private Matrix3x4 view;

    public LookAtCamera() {
        translate = Matrix4.one();
        rotate = Matrix4.one();
        scale = Matrix4.one();
        projection = Matrix4Factories.cameraProjectionMatrix(90,0.1,10);
        camera = Matrix4.lookAtCameraMatrix();
        //view = camera.invert();
    }

    /**
     * Метод, преобразуюший точку из меировой системы координат в систему координат камеры.
     * Сначала к вектору применяется масштаб(S), далее поворот(R), потом перенос(T) и в конце - проекция(P).
     * r = P * T * R * S * v
     *
     * @param v вектор, который надо перевести
     * @return новый вектор
     */
    @Override
    public Vector3 w2s(Vector3 v) {
        return projection.mul(
                camera.mul(
                        translate.mul(
                                rotate.mul(
                                        scale.mul(
                                                new Vector4(v, 1)
                                        )
                                )
                        )
                )
        ).asVector3();
    }

    public void modifyProjection(Matrix4 dp) {
        this.projection = dp.mul(this.projection);
    }

    public Matrix4 getProjection() {
        return projection;
    }

    public void setProjection(Matrix4 projection) {
        this.projection = projection;
    }

    public void modifyRotate(Matrix4 dp) {
        this.rotate = dp.mul(this.rotate);
    }

    public Matrix4 getRotate() {
        return rotate;
    }

    public void setRotate(Matrix4 rotate) {
        this.rotate = rotate;
    }

    public void modifyScale(Matrix4 dp) {
        this.scale = dp.mul(this.scale);
    }

    public Matrix4 getScale() {
        return scale;
    }

    public void setScale(Matrix4 scale) {
        this.scale = scale;
    }

    public void modifyTranslate(Matrix4 dp) {
        this.translate = dp.mul(this.translate);
    }

    public Matrix4 getTranslate() {
        return translate;
    }

    public void setTranslate(Matrix4 translate) {
        this.translate = translate;
    }
}
