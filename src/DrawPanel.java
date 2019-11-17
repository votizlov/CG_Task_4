import math.Matrix4;
import math.Vector3;
import math.Vector4;
import models.Cube;
import models.Line3d;
import thirdDimention.Camera;
import thirdDimention.Renderer;
import thirdDimention.Scene;
import thirdDimention.ScreenConverter;
import thirdDimention.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;
    private Renderer renderer;
    private boolean isRendererActive = false;

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-2, 2, 4, 4, 500, 500);
        cam = new Camera();
        scene = new Scene();
        renderer = new Renderer(sc,cam,scene);
        scene.models.add(new Line3d(new Vector3(0, 0, 0), new Vector3(0, 0, 2)));
        scene.models.add(new Line3d(new Vector3(0, 0, 0), new Vector3(0, 2, 0)));
        scene.models.add(new Line3d(new Vector3(0, 0, 0), new Vector3(2, 0, 0)));
        scene.models.add(new Cube(new Vector3(1, 1, 1), new Vector3(-1, -1, -1)));

        //Obj obj = ObjUtils.convertToRenderable(ObjReader.read(inputStream)); todo make it work
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case  KeyEvent.VK_R:  isRendererActive = !isRendererActive;
                    break;
                    case KeyEvent.VK_UP:cam.translate.moveX(0.1);
                    break;
                    case KeyEvent.VK_DOWN:cam.translate.moveX(-0.1);
                    break;
                    case KeyEvent.VK_RIGHT:cam.translate.moveY(1);
                    break;
                    case KeyEvent.VK_LEFT:cam.translate.moveZ(1);
                    break;
                }
                repaint();
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        if(!isRendererActive)
            g.drawImage(scene.drawScene(sc, cam), 0, 0, null);
        else
            g.drawImage(renderer.renderImage(),0,0,null);
        for(int i = 0;i<4;i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(cam.rotate.getAt(i,j)+" ");
            System.out.println("\n");
        }
    }

    private ScreenPoint last = null;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        last = new ScreenPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        last = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint np = new ScreenPoint(e.getX(), e.getY());
        if (last != null) {
            int dx = np.getI() - last.getI();
            int dy = np.getJ() - last.getJ();
            double da = dx * Math.PI / 180;
            double db = dy * Math.PI / 180;
            cam.rotate = Matrix4.rotate(da, 0).mul(Matrix4.rotate(db, 1).mul(cam.rotate));
        }
        last = np;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
