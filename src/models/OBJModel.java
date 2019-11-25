package models;

import main.math.Vector3;
import main.obj.Obj;
import main.third.IModel;
import main.third.Material;
import main.third.PolyLine3D;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OBJModel implements IModel {

    private LinkedList<PolyLine3D> lines;

    @Override
    public List<PolyLine3D> getLines() {
        return lines;
    }

    @Override
    public Material getMat() {
        return null;
    }

    public OBJModel(Obj obj) {
        lines = new LinkedList<>();
        for(int i = 0;i<obj.getNumVertices()-5;i+=5){
            lines.add(new PolyLine3D(Arrays.asList(new Vector3(obj.getVertex(i).getX(), obj.getVertex(i).getY(), obj.getVertex(i).getZ()),
                    new Vector3(obj.getVertex(i+1).getX(), obj.getVertex(i+1).getY(), obj.getVertex(i+1).getZ()),
                    new Vector3(obj.getVertex(i+2).getX(), obj.getVertex(i+2).getY(), obj.getVertex(i+2).getZ()),
                    new Vector3(obj.getVertex(i+3).getX(), obj.getVertex(i+3).getY(), obj.getVertex(i+3).getZ()),
                    new Vector3(obj.getVertex(i+4).getX(),obj.getVertex(i+4).getY(),obj.getVertex(i+4).getZ())), true));
        }
    }
}
