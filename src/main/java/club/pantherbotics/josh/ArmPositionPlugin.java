package club.pantherbotics.josh;

import us.jfreedman.src.ns.frc.common.DragListener;
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

    double currentPosition = 0;
    JPanel dialPanel = new DialJPanel();

    @Inject
    MainGUI mainGUI;

    @Inject
    Logger logger;

    @Listener
    public void onInit(Packet99 ignored) {

        DragListener dragListener = new DragListener();
        dialPanel.setOpaque(false);
        dialPanel.addMouseListener(dragListener);
        dialPanel.addMouseMotionListener(dragListener);
        dialPanel.setSize(300, 150);
        dialPanel.setLocation(200, 200);

        mainGUI.add(dialPanel);

        logger.debug("YO");

        dialPanel.repaint();
        mainGUI.repaint();
    }

    @Listener
    public void onNumberPacket(Packet02 packet02) {
        if (packet02.getKey().equals("Arm Position")) {
            currentPosition = packet02.getNumber().doubleValue();
            dialPanel.repaint();
        }
    }

    private class DialJPanel extends JPanel {

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            Graphics2D g = (Graphics2D) graphics;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setStroke(new BasicStroke(3));
//            g.setColor(Color.CYAN);
//            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.fillArc(5, 5, dialPanel.getWidth() - 10, dialPanel.getHeight() * 2, -10, 200);
            g.setColor(Color.yellow);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            for (int i = 0; i < 6; i++) {
                g.drawString(i + "", ((dialPanel.getWidth() / 2) - (int) (Math.cos((Math.abs(i)) * (Math.PI / 5)) * (dialPanel.getWidth() / 2 - 20))) - 5, (dialPanel.getHeight()) - (int) (Math.sin((Math.abs(i)) * (Math.PI / 5)) * (dialPanel.getHeight() - 20)));
            }
            g.drawString("Arm Pos", dialPanel.getWidth() / 2 - 3 * 10, dialPanel.getHeight() / 2);
            g.setColor(Color.red);
            g.drawLine(dialPanel.getWidth() / 2, dialPanel.getHeight(), (dialPanel.getHeight()) - (int) (Math.cos((Math.abs(currentPosition)) * (Math.PI / 5)) * (dialPanel.getWidth() / 2 - 32)), (dialPanel.getWidth() / 2) - (int) (Math.sin((Math.abs(currentPosition)) * (Math.PI / 5)) * (dialPanel.getHeight() - 32)));
            g.fillArc(dialPanel.getWidth() / 2 - 10, dialPanel.getHeight() - 10, 20, 20, 0, 360);
        }
    }
}
