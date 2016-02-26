package us.jfreedman.src.ns.frc.server.gui;

import us.jfreedman.src.ns.frc.common.packets.Packet99;
import us.jfreedman.src.ns.frc.server.PluginBus;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainGUI extends JFrame {
    private static final int SCALE = 400;

    public MainGUI() {
        super("Main GUI");
        this.setSize(5 * SCALE, 3 * SCALE);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public MainGUI lockWindow() {
        this.setVisible(false);
        this.setAlwaysOnTop(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 600);
        this.setVisible(true);
        return this;
    }

    public MainGUI unlockWindow() {
        this.setVisible(false);
        this.setAlwaysOnTop(false);
        this.setSize(5 * SCALE, 3 * SCALE);
        this.setVisible(true);
        return this;
    }

    public MainGUI setup() {
        PluginBus.getInstance().firePacket(new Packet99());
        return this;
    }

}
