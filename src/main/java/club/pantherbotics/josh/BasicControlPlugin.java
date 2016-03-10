package club.pantherbotics.josh;

import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.Plugin;
import us.jfreedman.src.ns.frc.common.injector.Inject;
import us.jfreedman.src.ns.frc.common.packets.Packet01;
import us.jfreedman.src.ns.frc.common.packets.Packet02;
import us.jfreedman.src.ns.frc.common.packets.Packet03;
import us.jfreedman.src.ns.frc.common.packets.Packet99;
import us.jfreedman.src.ns.frc.server.gui.MainGUI;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Basic Control Plugin")
public class BasicControlPlugin {

    TextArea logTextArea = new TextArea();
    JButton exitButton = new JButton("Exit");
    JButton minimizeButton = new JButton("Minimize");

    @Inject
    MainGUI mainGUI;

    @Inject
    Logger logger;

    @Listener
    public void init(Packet99 ignored) {
        exitButton.addActionListener(e -> System.exit(0));
        minimizeButton.addActionListener(e -> mainGUI.setState(Frame.ICONIFIED));

        mainGUI.setLayout(null);

        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        logTextArea.setSize(mainGUI.getWidth() / 3, mainGUI.getHeight() / 3);
        logTextArea.setLocation(mainGUI.getWidth() - (logTextArea.getWidth()), mainGUI.getHeight() - (logTextArea.getHeight()));
        mainGUI.add(logTextArea);

        minimizeButton.setFont(new Font("Arial", Font.BOLD, 40));
        minimizeButton.setBackground(Color.CYAN);
        minimizeButton.setSize(mainGUI.getWidth() / 3, 75);
        minimizeButton.setLocation(mainGUI.getWidth() - (minimizeButton.getWidth()), mainGUI.getHeight() - logTextArea.getHeight() - minimizeButton.getHeight());
        mainGUI.add(minimizeButton);

        exitButton.setFont(new Font("Arial", Font.BOLD, 40));
        exitButton.setBackground(Color.red);
        exitButton.setSize(mainGUI.getWidth() / 3, 75);
        exitButton.setLocation(mainGUI.getWidth() - (exitButton.getWidth()), mainGUI.getHeight() - logTextArea.getHeight() - minimizeButton.getHeight() - exitButton.getHeight());
        mainGUI.add(exitButton);

        mainGUI.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                logTextArea.setSize(mainGUI.getWidth() / 3, mainGUI.getHeight() / 3);
                logTextArea.setLocation(mainGUI.getWidth() - (logTextArea.getWidth()), mainGUI.getHeight() - (logTextArea.getHeight()));
                minimizeButton.setSize(mainGUI.getWidth() / 3, 75);
                minimizeButton.setLocation(mainGUI.getWidth() - (minimizeButton.getWidth()), mainGUI.getHeight() - logTextArea.getHeight() - minimizeButton.getHeight());
                exitButton.setSize(mainGUI.getWidth() / 3, 75);
                exitButton.setLocation(mainGUI.getWidth() - (exitButton.getWidth()), mainGUI.getHeight() - logTextArea.getHeight() - minimizeButton.getHeight() - exitButton.getHeight());
            }
        });

        mainGUI.lockWindow();
    }

    @Listener
    public void defPacket(Packet01 packet01) {
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

    public class DragListener extends MouseInputAdapter {
        Point location;
        MouseEvent pressed;

        public void mousePressed(MouseEvent me) {
            pressed = me;
        }

        public void mouseDragged(MouseEvent me) {
            Component component = me.getComponent();
            location = component.getLocation(location);
            int x = location.x - pressed.getX() + me.getX();
            int y = location.y - pressed.getY() + me.getY();
            component.setLocation(x, y);
        }
    }
}
