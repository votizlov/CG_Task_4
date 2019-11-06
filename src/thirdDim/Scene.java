package thirdDim;

import math.Vector3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.signum;

public class Scene {
    public List<IModel> models = new ArrayList<>();

    public BufferedImage draw(ScreenConverter sc, Camera c) {
        BufferedImage bi = new BufferedImage(sc.getWs(), sc.getHs(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        ArrayList<PolyLine3D> lines = new ArrayList<>();
        for (IModel m : models
        ) {
            for (PolyLine3D line : m.getLines()
            ) {
                List<Vector3> vector3s = new LinkedList<>();
                for (Vector3 v : line.getPoints()
                ) {
                    vector3s.add(c.w2c(v));
                }
                lines.add(new PolyLine3D(vector3s, false));
            }
        }
        lines.sort(new Comparator<PolyLine3D>() {
            @Override
            public int compare(PolyLine3D polyLine3D, PolyLine3D t1) {
                return (int) Math.signum(polyLine3D.avgZ() - t1.avgZ());
            }
        });
        g.setColor(Color.WHITE);
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        g.setColor(Color.BLACK);
        for (PolyLine3D pl: lines
             ) {
            ScreenPoint last = sc.r2s(pl.getPoints().get(0));
            for(int i = 1;i<pl.getPoints().size();i++){
                ScreenPoint np = sc.r2s(pl.getPoints().get(i));
                g.drawLine(last.getI(),last.getJ(),np.getI(),np.getJ());
                last = np;
            }
        }
        g.dispose();
        return bi;
    }
}
