/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import thirdDim.Camera;
import thirdDim.IModel;
import thirdDim.Scene;
import thirdDim.ScreenConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class DrawPanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;
    private boolean renderLight;
    
    public DrawPanel() {
        super();
        cam = new Camera();
        //sc = new ScreenConverter(f.getRectangle(), 450, 450);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        sc.setHs(getHeight());
        sc.setWs(getWidth());
        scene.draw(sc,cam);
        g.drawImage(bi, 0, 0, null);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int direction = 0;
        if (e.getButton() == MouseEvent.BUTTON1)
            direction = 1;
        else if (e.getButton() == MouseEvent.BUTTON3)
            direction = -1;
       // w.getExternalForce().setValue(10*direction);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //w.getExternalForce().setValue(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      //  w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      //  w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
    public void toggleModel(IModel model){
        scene.toggleModel(model);
    }
}
