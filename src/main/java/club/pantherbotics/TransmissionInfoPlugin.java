package club.pantherbotics;

import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.Plugin;
import us.jfreedman.src.ns.frc.common.injector.Inject;
import us.jfreedman.src.ns.frc.common.packets.Packet05;
import us.jfreedman.src.ns.frc.common.packets.Packet99;
import us.jfreedman.src.ns.frc.server.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Transmission Info Plugin")
public class TransmissionInfoPlugin {

    private JLabel graphic = new JLabel();

    @Inject
    private MainGUI mainGUI;

    @Inject
    private Logger logger;

    @Listener
    public void onInit(Packet99 packet) {

        graphic.setFont(new Font("Arial", Font.BOLD, 40));
        graphic.setHorizontalAlignment(SwingConstants.CENTER);
        graphic.setVerticalAlignment(SwingConstants.CENTER);
        graphic.setText(("Low Gear"));
        graphic.setBackground(Color.BLUE);
        graphic.setSize(225, 100);
        graphic.setLocation(0, mainGUI.getHeight() - graphic.getHeight());
        mainGUI.add(graphic);

        mainGUI.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                graphic.setSize(225, 100);
                graphic.setLocation(0, mainGUI.getHeight() - graphic.getHeight());
            }
        });
        logger.debug("Transmission Plugin Completely Loaded");
    }

    @Listener
    public void onBooleanPacket(Packet05 booleanPacket) {
        if (booleanPacket.getKey().equals("Transmission")) {
            if (booleanPacket.getData()) {
                graphic.setBackground(Color.RED);
                graphic.setText(("High Gear"));
            } else {
                graphic.setBackground(Color.BLUE);
                graphic.setText(("Low Gear"));
            }
        }
    }
}