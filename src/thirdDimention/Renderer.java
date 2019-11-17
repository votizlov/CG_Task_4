package thirdDimention;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {
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
        return bi;
    }
}
