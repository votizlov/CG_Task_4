import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawPanel p = new DrawPanel();
        frame.add(p);
        p.setFocusable(true);
        p.grabFocus();
        frame.setVisible(true);
    }

}
