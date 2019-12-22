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
import java.util.LinkedList;
import javax.swing.JPanel;

import main.draw.IDrawer;
import main.draw.Renderer;
import main.draw.SimpleEdgePolygonDrawer;
import main.math.Matrix4Factories;
import main.math.Vector3;
import main.obj.Obj;
import main.obj.ObjReader;
import main.obj.ObjUtils;
import main.screen.ScreenConverter;
import main.third.*;
import models.*;


public class DrawPanel extends JPanel
        implements CameraController.RepaintListener {
    private Scene scene;
    private ScreenConverter sc;
    private ICamera cam;
    private CameraController camController;
    private boolean isRendererActive = false;

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-1, 1, 2, 2, 1, 1);
        cam = new Camera();
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();

        scene.getModelsList().add(new Parallelepiped(
                new Vector3(-1, -1, -1),
                new Vector3(1, 1, -1),
                new Material(Color.DARK_GRAY, 0.5f)
        ));

        Obj obj = null;
        try {
            obj = ObjUtils.convertToRenderable(ObjReader.read(new FileInputStream("src/models/only_quad_sphere.obj")));
            scene.getModelsList().add(new OBJModel(obj, new Material(Color.CYAN, 0f)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene.getLights().add(new PointLight(6,Color.CYAN,new Anchor(new Vector3(4,4,4))));
        scene.getLights().add(new PointLight(6,Color.ORANGE,new Anchor(new Vector3(-4,-4,-4))));

        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
        this.addKeyListener(camController);
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        IDrawer dr = new SimpleEdgePolygonDrawer(sc, graphics);
        if (isRendererActive)
            scene.drawScene(dr, cam, new Renderer(sc, cam, scene));
        else
            scene.drawScene(dr, cam, null);
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
    public void shouldRepaint(boolean isRendererActive) {
        this.isRendererActive = isRendererActive;
        repaint();
    }
}
