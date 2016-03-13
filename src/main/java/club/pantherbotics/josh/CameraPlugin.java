package club.pantherbotics.josh;

import us.jfreedman.src.ns.frc.common.DragListener;
import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.Plugin;
import us.jfreedman.src.ns.frc.common.injector.Inject;
import us.jfreedman.src.ns.frc.common.packets.Packet04;
import us.jfreedman.src.ns.frc.common.packets.Packet99;
import us.jfreedman.src.ns.frc.server.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Joshua on 3/11/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Camera Plugin")
public class CameraPlugin {

    long lastTime = 0;

    BufferedImage bi = new BufferedImage(640, 320, BufferedImage.TYPE_INT_ARGB);
    JPanel panel = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(bi, 0, 0, null);
            if (System.currentTimeMillis() - lastTime > 100) {
                g.setColor(Color.RED);
                g.fillRect(0, getHeight() - 30, getWidth(), 30);
            }
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        }
    };

    @Inject
    MainGUI mainGUI;

    @Inject
    Logger logger;

    @Listener
    public void init(Packet99 packet99) {

        DragListener dragListener = new DragListener();
        panel.setSize(640, 320);
        panel.setLocation(10, 10);
        panel.addMouseListener(dragListener);
        panel.addMouseMotionListener(dragListener);

        mainGUI.add(panel);

        logger.debug("Camera Plugin Loaded");
    }

    @Listener
    public void onCamPacket(Packet04 packet) {
        bi = packet.getImage();
        lastTime = System.currentTimeMillis();
        panel.repaint();
    }
}
