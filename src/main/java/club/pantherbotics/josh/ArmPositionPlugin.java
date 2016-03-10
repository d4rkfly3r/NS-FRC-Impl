package club.pantherbotics.josh;

import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.Plugin;
import us.jfreedman.src.ns.frc.common.injector.Inject;
import us.jfreedman.src.ns.frc.common.packets.Packet02;
import us.jfreedman.src.ns.frc.common.packets.Packet99;
import us.jfreedman.src.ns.frc.server.gui.MainGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua on 3/10/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Arm Position Widget")
public class ArmPositionPlugin {

    double x = 0;

    JPanel jPanel = new DialJPanel();

    @Inject
    MainGUI mainGUI;

    @Inject
    Logger logger;

    @Listener
    public void onInit(Packet99 ignored) {

        jPanel.setSize(300, 300);
        jPanel.setLocation(200, 200);

        mainGUI.add(jPanel);

        logger.debug("YO");

        jPanel.repaint();
        mainGUI.repaint();
    }

    @Listener
    public void onNumberPacket(Packet02 packet02) {
        if (packet02.getKey().equals("Arm Position")) {
            x = packet02.getNumber().doubleValue();
            jPanel.repaint();
        }
    }

    private class DialJPanel extends JPanel {

        @Override
        public void paint(Graphics g2) {
            super.paint(g2);
            Graphics2D g = (Graphics2D) g2;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.fillArc(5, 5, jPanel.getWidth() - 10, jPanel.getHeight() - 10, -10, 200);
            g.setColor(Color.red);
            g.drawLine(jPanel.getWidth() / 2, jPanel.getHeight() / 2, (jPanel.getWidth() / 2) - (int) (Math.cos((Math.abs(x)) * (Math.PI / 5)) * (jPanel.getWidth() / 2 - 40)), (jPanel.getWidth() / 2) - (int) (Math.sin((Math.abs(x)) * (Math.PI / 5)) * (jPanel.getWidth() / 2 - 40)));
            g.fillArc(jPanel.getWidth() / 2 - 10, jPanel.getHeight() / 2 - 10, 20, 20, 0, 360);
            g.setColor(Color.yellow);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            for (int i = 0; i < 6; i++) {
                g.drawString(i + "", ((jPanel.getWidth() / 2) - (int) (Math.cos((Math.abs(i)) * (Math.PI / 5)) * (jPanel.getWidth() / 2 - 30))) - 5, (jPanel.getWidth() / 2) - (int) (Math.sin((Math.abs(i)) * (Math.PI / 5)) * (jPanel.getWidth() / 2 - 30)));
            }
        }
    }
}
