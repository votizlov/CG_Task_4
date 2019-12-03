package main.draw;


import main.math.Vector3;
import main.screen.ScreenConverter;
import main.third.ICamera;
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

    public Vector3 isHitting(PolyLine3D face){//point + dir * t = p соединяем с вершинами и углы равны 2pi
        float l = 0f;
        while(l<cutOff){
            Vector3 rayPoint = point.add(direction.mul(l));
            if(face.checkRayCollision(rayPoint,precision))
                return rayPoint;
            l += precision;
        }
        return null;
    }

    public void pointRay(int i, int j, ICamera cam,ScreenConverter sc) {
        float fov = cam.getFov();
        float aspect = cam.getAspect();
        float dx = fov*aspect/sc.getWs();
        float dy = fov/sc.getHs();

        for(int a = 0;a<j;a++){
            for(int b = 0;b<i;b++){

            }
        }
    }
}
