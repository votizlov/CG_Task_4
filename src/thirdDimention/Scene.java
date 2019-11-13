package thirdDimention;

import math.Vector3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    public List<IModel> models = new ArrayList<>();

    public BufferedImage drawScene(ScreenConverter sc, Camera c) {
        BufferedImage bi = new BufferedImage(sc.getWs(), sc.getHs(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)bi.getGraphics();
        /**/
        ArrayList<PolyLine3D> lines = new ArrayList<>();
        for (IModel m : models) {
            for (PolyLine3D pl : m.getLines()) {
                List<Vector3> vv = new LinkedList<>();
                for (Vector3 v : pl.getPoints()) {
                    vv.add(c.w2c(v));
                }
                lines.add(new PolyLine3D(vv, pl.isClosed()));
            }
        }
        lines.sort(new Comparator<PolyLine3D>() {
            @Override
            public int compare(PolyLine3D o1, PolyLine3D o2) {
                return (int)Math.signum(o1.avgZ() - o2.avgZ());
            }
        });

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.setColor(Color.BLACK);
        for (PolyLine3D pl : lines) {
            ScreenPoint last = sc.r2s(pl.getPoints().get(0));
            for (int i = 1; i < pl.getPoints().size(); i++) {
                ScreenPoint np = sc.r2s(pl.getPoints().get(i));
                g.drawLine(last.getI(), last.getJ(), np.getI(), np.getJ());
                last = np;
            }
            if (pl.isClosed()) {
                ScreenPoint np = sc.r2s(pl.getPoints().get(0));
                g.drawLine(last.getI(), last.getJ(), np.getI(), np.getJ());
            }
        }


        g.dispose();
        return bi;
    }



}
