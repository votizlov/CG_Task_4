package thirdDimention;

import math.Vector3;

public class ScreenConverter {
    private double xr, yr, wr, hr;
    private int ws, hs;

    public ScreenPoint r2s(Vector3 p) {
        int i = (int)((p.getX() - xr) * ws / wr);
        int j = (int)((yr - p.getY()) * hs / hr);
        return new ScreenPoint(i, j);
    }

    public ScreenConverter(double xr, double yr, double wr, double hr, int ws, int hs) {
        this.xr = xr;
        this.yr = yr;
        this.wr = wr;
        this.hr = hr;
        this.ws = ws;
        this.hs = hs;
    }

    public double getXr() {
        return xr;
    }

    public double getYr() {
        return yr;
    }

    public double getWr() {
        return wr;
    }

    public double getHr() {
        return hr;
    }

    public int getWs() {
        return ws;
    }

    public int getHs() {
        return hs;
    }
}
