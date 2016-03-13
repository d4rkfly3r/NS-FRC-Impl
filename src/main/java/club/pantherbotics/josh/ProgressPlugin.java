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

/**
 * Created by Joshua on 3/12/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Progress Plugin")
public class ProgressPlugin {

    JProgressBar jProgressBar = new JProgressBar(0, 5);

    @Inject
    MainGUI mainGUI;

    @Inject
    Logger logger;

    @Listener
    public void init(Packet99 packet) {

        DragListener dragListener = new DragListener();
        jProgressBar.addMouseMotionListener(dragListener);
        jProgressBar.addMouseListener(dragListener);

        jProgressBar.setSize(500, 30);
        jProgressBar.setLocation(0, 0);

        mainGUI.add(jProgressBar);


        logger.debug("Progress Plugin Loaded");
    }

    @Listener
    public void onNumberPacket(Packet02 packet) {
        if (packet.getKey().equals("Pressure")) {
            jProgressBar.setValue(packet.getNumber().intValue());
        }
    }
}
