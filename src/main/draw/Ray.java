package main.draw;


import main.math.Vector3;
import main.third.PolyLine3D;

public class Ray {
    private Vector3 point,direction;
    private float cutOff;
    private float precision;

    public Ray(Vector3 point, Vector3 direction, float cutOff) {
        this.point = point;
        this.direction = direction;
        this.cutOff = cutOff;
    }

    public Vector3 isHitting(PolyLine3D face){//point + dir * t = p
        float l = 0f;
        while(l<cutOff){
            Vector3 rayPoint = point.add(direction.mul(l));
            if(face.checkRayCollision(rayPoint))
                return rayPoint;
            l += precision;
        }
        return null;
    }
}
