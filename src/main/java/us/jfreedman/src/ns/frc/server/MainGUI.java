package us.jfreedman.src.ns.frc.server;

import us.jfreedman.src.ns.frc.common.Plugin;

import javax.swing.*;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainGUI extends JFrame {
    private static MainGUI ourInstance = new MainGUI();

    public static MainGUI getInstance() {
        return ourInstance;
    }

    private MainGUI() {
        super("Main GUI");
        this.setSize(500, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setup() {
        ClassFinder.getPluginClasses().forEach(aClass -> System.out.println(aClass.getAnnotation(Plugin.class).name()));
    }

}
