package models;

import main.draw.Ray;
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
    private Material m;

    public OBJModel(Obj obj, Material material) {
        System.out.println("Vertices: " + obj.getNumVertices() + " Faces: " + obj.getNumFaces() +" Normals: " + obj.getNumNormals());
        lines = new LinkedList<>();
        LinkedList<Vector3> line = new LinkedList<>();
        for(int i = 0;i<obj.getNumFaces();i++){
            for (int j = 0;j<obj.getFace(i).getNumVertices();j++)
                line.add(new Vector3(obj.getVertex(obj.getFace(i).getVertexIndex(j)).getX(),obj.getVertex(obj.getFace(i).getVertexIndex(j)).getY(),obj.getVertex(obj.getFace(i).getVertexIndex(j)).getZ()));
            lines.add(new PolyLine3D(line, true));
            line.clear();
        }
        this.m = material;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return lines;
    }

    @Override
    public Material getMat() {
        return m;
    }

    @Override
    public Vector3 checkRayCollision(Ray r) {
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
