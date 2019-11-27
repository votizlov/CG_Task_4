/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;

import main.draw.IDrawer;
import main.draw.SimpleEdgePolygonDrawer;
import main.math.Vector3;
import main.obj.Obj;
import main.obj.ObjReader;
import main.obj.ObjUtils;
import main.screen.ScreenConverter;
import main.third.Camera;
import main.third.ICamera;
import main.third.LookAtCamera;
import main.third.Scene;
import models.OBJModel;
import models.Parallelepiped;

/**
 * @author Alexey
 */
public class DrawPanel extends JPanel
        implements CameraController.RepaintListener {
    private Scene scene;
    private ScreenConverter sc;
    private ICamera cam;
    private CameraController camController;

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-1, 1, 2, 2, 1, 1);
        cam = new LookAtCamera();
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();

        scene.getModelsList().add(new Parallelepiped(
                new Vector3(-0.4f, -0.4f, -0.4f),
                new Vector3(0.4f, 0.4f, 0.4f)
        ));
/*
        Obj obj = null;
        try {
            obj = ObjUtils.convertToRenderable(ObjReader.read(new FileInputStream("src/models/only_quad_sphere.obj")));
            scene.getModelsList().add(new OBJModel(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        IDrawer dr = new SimpleEdgePolygonDrawer(sc, graphics);
        scene.drawScene(dr, cam);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
        /*for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(cam.getTranslate().getAt(i, j) + " ");
            }
            System.out.println();
        }*/
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}
