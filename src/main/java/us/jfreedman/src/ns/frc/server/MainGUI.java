package us.jfreedman.src.ns.frc.server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainGUI extends JFrame {
    private static MainGUI ourInstance = new MainGUI();

    public static MainGUI getInstance() {
        return ourInstance;
    }

    public TextArea logTextArea;

    private MainGUI() {
        super("Main GUI");
        this.setSize(500, 300);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        logTextArea = new TextArea();
        this.add(logTextArea);
    }

    public void setup() {
        PluginBus.getInstance().init();
    }

}
