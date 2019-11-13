package models;

import math.Vector3;
import thirdDimention.IModel;
import thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cube implements IModel {

    private Vector3 TLF, BRN;

    public Cube(Vector3 TLF, Vector3 BRN) {
        this.TLF = TLF;
        this.BRN = BRN;
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> lines = new ArrayList<>();
        List<Vector3> points;
        points = new ArrayList<>();
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(TLF.getX(), TLF.getY(), TLF.getZ()),
                new Vector3(TLF.getX(), BRN.getY(), TLF.getZ()),
                new Vector3(BRN.getX(), BRN.getY(), TLF.getZ()),
                new Vector3(BRN.getX(), TLF.getY(), TLF.getZ())}), true));
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(TLF.getX(), TLF.getY(), BRN.getZ()),
                new Vector3(TLF.getX(), BRN.getY(), BRN.getZ()),
                new Vector3(BRN.getX(), BRN.getY(), BRN.getZ()),
                new Vector3(BRN.getX(), TLF.getY(), BRN.getZ())}), true));




        return lines;
    }
}
