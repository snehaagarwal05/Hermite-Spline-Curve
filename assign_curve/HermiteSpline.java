import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HermiteSpline extends JFrame {

    public HermiteSpline() {
        setTitle("Hermite Spline - 4 Click Version");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new DrawPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HermiteSpline());
    }
}

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Point> clicks = new ArrayList<>();

    public DrawPanel() {
        addMouseListener(this);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw clicked points
        g2.setColor(Color.RED);
        for (Point p : clicks) {
            g2.fillOval(p.x - 4, p.y - 4, 8, 8);
        }

        if (clicks.size() == 4) {

            Point P0 = clicks.get(0);
            Point P1 = clicks.get(1);
            Point P2 = clicks.get(2);
            Point P3 = clicks.get(3);

            // Tangent vectors
            double T0x = P1.x - P0.x;
            double T0y = P1.y - P0.y;

            double T1x = P3.x - P2.x;
            double T1y = P3.y - P2.y;

            // Draw tangent lines
            g2.setColor(Color.GRAY);
            g2.drawLine(P0.x, P0.y, P1.x, P1.y);
            g2.drawLine(P3.x, P3.y, P2.x, P2.y);

            // Hermite basis matrix
            double[][] H = {
                    { 2, -2,  1,  1},
                    {-3,  3, -2, -1},
                    { 0,  0,  1,  0},
                    { 1,  0,  0,  0}
            };

            g2.setColor(Color.BLUE);

            Point prev = P0;

            for (double t = 0; t <= 1.0; t += 0.01) {

                double[] T = {t*t*t, t*t, t, 1};

                // Multiply T * H
                double[] TH = new double[4];
                for (int i = 0; i < 4; i++) {
                    TH[i] = 0;
                    for (int j = 0; j < 4; j++) {
                        TH[i] += T[j] * H[j][i];
                    }
                }

                // Geometry vector G = {P0, P3, T0, T1}
                double x = TH[0]*P0.x + TH[1]*P3.x + TH[2]*T0x + TH[3]*T1x;
                double y = TH[0]*P0.y + TH[1]*P3.y + TH[2]*T0y + TH[3]*T1y;

                g2.drawLine(prev.x, prev.y, (int)x, (int)y);
                prev = new Point((int)x, (int)y);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (clicks.size() == 4) {
            clicks.clear();  // Reset after 4 clicks
        }

        clicks.add(e.getPoint());
        repaint();
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
