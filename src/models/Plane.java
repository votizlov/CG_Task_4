package models;

import main.draw.Ray;
import main.math.Vector3;
import main.third.IModel;
import main.third.Material;
import main.third.PolyLine3D;

import java.util.LinkedList;
import java.util.List;

public class Plane implements IModel {
    LinkedList<PolyLine3D> lines;

    public Plane(LinkedList<PolyLine3D> lines) {
        this.lines = lines;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return lines;
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
