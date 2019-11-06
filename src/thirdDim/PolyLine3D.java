package thirdDim;

import math.Vector3;

import java.util.LinkedList;
import java.util.List;

public class PolyLine3D {
    private List<Vector3> points;

    public PolyLine3D(List<Vector3> points) {
        this.points = points;
    }

    public PolyLine3D(List<Vector3> points, boolean closed) {
        this.points = points;
        if (closed)
            points.add(points.get(0));
    }

    public List<Vector3> getPoints() {
        return points;
    }
    public double avgZ(){//todo разобраться с тем, что в списке может быть 1 лишняя точка
        double sum = 0;
        for (int i = 0;i<points.size();i++){
            sum += points.get(i).getZ();
        }
        return sum / points.size();
    }
}
