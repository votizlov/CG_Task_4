package models;

import java.util.Arrays;
import java.util.List;

import main.draw.Ray;
import main.math.Vector3;
import main.third.IModel;
import main.third.Material;
import main.third.PolyLine3D;


public class Line3D implements IModel {
    private Vector3 p1, p2;

    public Line3D(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return Arrays.asList(new PolyLine3D(
                Arrays.asList(p1, p2)
                , false));// todo zbuffer
    }

    @Override
    public Material getMat() {
        return null;
    }

    @Override
    public Vector3 checkRayCollision(Ray r) {
        return null;
    }

}
