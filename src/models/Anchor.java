package models;

import main.draw.Ray;
import main.math.Vector3;
import main.third.IModel;
import main.third.Material;
import main.third.PolyLine3D;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Anchor implements IModel {
    private List<PolyLine3D> p = new LinkedList<>();

    public Anchor(Vector3 p) {
        this.p.add(new PolyLine3D(Collections.singletonList(
                p
        ),false));
    }

    public void setPos(Vector3 v){
        p.clear();
        p.add(new PolyLine3D(Collections.singletonList(
                v
        ),false));
    }

    @Override
    public List<PolyLine3D> getLines() {
        return p;
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
