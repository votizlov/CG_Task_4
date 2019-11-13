package thirdDimention;

import math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class PolyLine3D {
    private List<Vector3> points;
    private boolean closed;

    public PolyLine3D(List<Vector3> p, boolean closed) {
        points = new ArrayList<>(p);
        if (closed) points.add(points.get(0));
        this.closed = closed;
    }

    public List<Vector3> getPoints() {
        return  points;
    }

    public boolean isClosed() {
        return closed;
    }

    public double avgZ() {
        double sum = 0;
        for (int i = 0; i < points.size(); i++) { // если линия замкнута, то первая точка будет в списке дважды. Решить проблему
            sum += points.get(i).getZ();
        }
        return  sum / points.size();
    }

}
