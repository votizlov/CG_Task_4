package main.draw;

import main.math.Vector3;
import main.screen.ScreenConverter;
import main.third.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    private static final int MAX_RAY_DEPTH = 3;
    private static final float PRECISION = 0.0001f;
    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;

    public Renderer(ScreenConverter sc, Camera cam, Scene scene) {
        this.sc = sc;
        this.cam = cam;
        this.scene = scene;
    }

    public Image renderImage() {
        BufferedImage bi = new BufferedImage(sc.getWs(), sc.getHs(), BufferedImage.TYPE_INT_RGB);
        IModel primRayHit = null;
        Vector3 primRayHitPoint = null;

        for (int j = 0; j < sc.getWs(); ++j) {
            for (int i = 0; i < sc.getHs(); ++i) {

                // compute primary ray direction
                Ray primRay = new Ray(cam.getCameraPos(), cam.getCameraDir(), 10f);
                primRay.pointRay(i, j, cam.getFov());
                // shoot prim ray in the scene and search for intersection
                //Point pHit;
                //Normal nHit;
                //float minDist = INFINITY;
                //Object object = NULL;
                for (IModel m : scene.getModelsList()) {
                    for (PolyLine3D p : m.getLines()
                    ) {
                        Vector3 t = primRay.isHitting(p);
                        if (t != null) {
                            primRayHit = m;
                            primRayHitPoint = t;
                            break;
                        }
                    }
                    if (primRayHit != null)
                        break;
                }
                boolean isInShadow = false;
                if (primRayHit != null) {
                    // compute illumination
                    for (PointLight p : scene.getLights()
                    ) {
                        Ray shadowRay = new Ray(primRayHitPoint, new Vector3(primRayHitPoint, p.getPos()), new Vector3(primRayHitPoint, p.getPos()).length());
                        for (IModel m : scene.getModelsList()) {
                            for (PolyLine3D p1 : m.getLines()
                            ) {
                                Vector3 t = primRay.isHitting(p1);
                                if (t != null) {
                                    isInShadow = true;
                                    break;
                                }
                            }
                            if (primRayHit != null)
                                break;
                        }
                    }
                }
                if (!isInShadow) {
                    bi.setRGB(i, j, Color.YELLOW.getRGB());
                } else {
                    //pixels[i][j] = 0;
                }
                //intersectedModels.clear();
            }

        }
        return bi;
    }
    /*
    #define MAX_RAY_DEPTH 3

color Trace(const Ray &ray, int depth)
{
    Object *object = NULL;
    float minDist = INFINITY;
    Point pHit;
    Normal nHit;
    for (int k = 0; k < objects.size(); ++k) {
        if (Intersect(objects[k], ray, &pHit, &nHit)) {
            // ray origin = eye position of it's the prim ray
            float distance = Distance(ray.origin, pHit);
            if (distance < minDistance) {
                object = objects[i];
                minDistance = distance;
            }
        }
    }
    if (object == NULL)
        return 0;
    // if the object material is glass, split the ray into a reflection
    // and a refraction ray.
    if (object->isGlass && depth < MAX_RAY_DEPTH) {
        // compute reflection
        Ray reflectionRay;
        reflectionRay = computeReflectionRay(ray.direction, nHit);
        // recurse
        color reflectionColor = Trace(reflectionRay, depth + 1);
        Ray refractioRay;
        refractionRay = computeRefractionRay(
            object->indexOfRefraction,
            ray.direction,
            nHit);
        // recurse
        color refractionColor = Trace(refractionRay, depth + 1);
        float Kr, Kt;
        fresnel(
            object->indexOfRefraction,
            nHit,
            ray.direction,
            &Kr,
            &Kt);
        return reflectionColor * Kr + refractionColor * (1-Kr);
    }
    // object is a diffuse opaque object
    // compute illumination
    Ray shadowRay;
    shadowRay.direction = lightPosition - pHit;
    bool isShadow = false;
    for (int k = 0; k < objects.size(); ++k) {
        if (Intersect(objects[k], shadowRay)) {
            // hit point is in shadow so just return
            return 0;
        }
    }
    // point is illuminated
    return object->color * light.brightness;
}

// for each pixel of the image
for (int j = 0; j < imageHeight; ++j) {
    for (int i = 0; i < imageWidth; ++i) {
        // compute primary ray direction
        Ray primRay;
        computePrimRay(i, j, &primRay);
        pixels[i][j] = Trace(primRay, 0);
    }
}
     */
}
