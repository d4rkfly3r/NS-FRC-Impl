package club.pantherbotics.josh;

import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.Plugin;
import us.jfreedman.src.ns.frc.common.packets.Packet01;
import us.jfreedman.src.ns.frc.common.packets.Packet02;
import us.jfreedman.src.ns.frc.common.packets.Packet03;
import us.jfreedman.src.ns.frc.common.packets.Packet99;
import us.jfreedman.src.ns.frc.server.MainGUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Test Me")
public class Test2Class {

    Logger logger = new Logger(Logger.ANSI_PURPLE + "[TestPL] " + Logger.ANSI_RESET);

    TextArea logTextArea = new TextArea();
    Button exitButton = new Button("Exit");

    @Listener
    public void init(Packet99 ignored) {

        exitButton.addActionListener(e -> System.exit(0));

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        MainGUI.getInstance().setLayout(layout);

        constraints.ipadx = 0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 40;
        constraints.weightx = 1.0;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        MainGUI.getInstance().add(exitButton, constraints);
        MainGUI.getInstance().add(logTextArea);
        MainGUI.getInstance().lockWindow();
    }

    @Listener
    public void defPacket(Packet01 packet01) {
        System.out.println(packet01.getExtra());
        logTextArea.append(packet01.getExtra() + "\n");
        logger.log(packet01.getExtra());
    }

    @Listener
    public void numberPacket(Packet02 packet02) {
        logTextArea.append(packet02.getKey() + " | " + packet02.getNumber() + "\n");
        logger.log(packet02.getKey() + " | " + packet02.getNumber());
    }

    @Listener
    public void stringPacket(Packet03 packet03) {
        logTextArea.append(packet03.getKey() + " | " + packet03.getString() + "\n");
        logger.log(packet03.getKey() + " | " + packet03.getString());
    }
}
