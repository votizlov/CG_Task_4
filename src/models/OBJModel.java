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
        LinkedList<Vector3> line = new LinkedList<>();
        for(int i = 0;i<obj.getNumFaces();i++){
            for (int j = 0;j<obj.getFace(i).getNumVertices();j++)
                line.add(new Vector3(obj.getVertex(obj.getFace(i).getVertexIndex(j)).getX(),obj.getVertex(obj.getFace(i).getVertexIndex(j)).getY(),obj.getVertex(obj.getFace(i).getVertexIndex(j)).getZ()));
            lines.add(new PolyLine3D(line, true));
            line.clear();
        }
    }
}
