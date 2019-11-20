package main.draw;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {
    /*
    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;

    public Renderer(ScreenConverter sc, Camera cam, Scene scene) {
        this.sc = sc;
        this.cam = cam;
        this.scene = scene;
    }

    public Image renderImage() {
        BufferedImage bi = new BufferedImage(sc.getWs(),sc.getHs(),BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < sc.getWs(); ++j) {
            for (int i = 0; i < sc.getHs(); ++i) {
                // compute primary ray direction
                Ray primRay;
                computePrimRay(i, j, &primRay);
                // shoot prim ray in the scene and search for intersection
                Point pHit;
                Normal nHit;
                float minDist = INFINITY;
                Object object = NULL;
                for (int k = 0; k < objects.size(); ++k) {
                    if (Intersect(objects[k], primRay, &pHit, &nHit)) {
                        float distance = Distance(eyePosition, pHit);
                        if (distance < minDistance) {
                            object = objects[k];
                            minDistance = distance; // update min distance
                        }
                    }
                }
                if (object != NULL) {
                    // compute illumination
                    Ray shadowRay;
                    shadowRay.direction = lightPosition - pHit;
                    bool isShadow = false;
                    for (int k = 0; k < objects.size(); ++k) {
                        if (Intersect(objects[k], shadowRay)) {
                            isInShadow = true;
                            break;
                        }
                    }
                }
                if (!isInShadow)
                    pixels[i][j] = object->color * light.brightness;
                else
                    pixels[i][j] = 0;
            }
        }
        return bi;
    }
    */
}
