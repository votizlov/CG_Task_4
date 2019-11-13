import math.Matrix4;
import math.Vector3;
import models.Cube;
import models.Line3d;
import thirdDimention.Camera;
import thirdDimention.Scene;
import thirdDimention.ScreenConverter;
import thirdDimention.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;


    public DrawPanel() {
        super();
        sc = new ScreenConverter(-2, 2, 4, 4, 500, 500);
        cam = new Camera();
        scene = new Scene();
        scene.models.add(new Line3d(new Vector3(0, 0, 0), new Vector3(0, 0, 1)));
        scene.models.add(new Line3d(new Vector3(0, 0, 0), new Vector3(0, 1, 0)));
        scene.models.add(new Line3d(new Vector3(0, 0, 0), new Vector3(1, 0, 0)));
        scene.models.add(new Cube(new Vector3(1, 1, 1), new Vector3(-1, -1, -1)));
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(scene.drawScene(sc, cam), 0, 0, null);
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
