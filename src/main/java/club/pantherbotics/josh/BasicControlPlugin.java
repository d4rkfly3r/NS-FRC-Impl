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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Basic Control Plugin")
public class BasicControlPlugin {

    private JTextArea logTextArea = new JTextArea();
    private JButton exitButton = new JButton("Exit");
    private JButton minimizeButton = new JButton("Minimize");

    @Inject
    private
    MainGUI mainGUI;

    @Inject
    private
    Logger logger;

    @Listener
    public void init(Packet99 ignored) {
        exitButton.addActionListener(e -> System.exit(0));
        minimizeButton.addActionListener(e -> mainGUI.setState(Frame.ICONIFIED));

        mainGUI.setLayout(null);

        logTextArea.setEditable(false);
//        logTextArea.addCaretListener(e -> truncateLogText());
        logTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        ((DefaultCaret) logTextArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane pane = new JScrollPane(logTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setSize(mainGUI.getWidth() / 3, mainGUI.getHeight() / 3);
        pane.setLocation(mainGUI.getWidth() - (pane.getWidth()), mainGUI.getHeight() - (pane.getHeight()));
        mainGUI.add(pane);

        minimizeButton.setFont(new Font("Arial", Font.BOLD, 40));
        minimizeButton.setBackground(Color.CYAN);
        minimizeButton.setSize(mainGUI.getWidth() / 3, 75);
        minimizeButton.setLocation(mainGUI.getWidth() - (minimizeButton.getWidth()), mainGUI.getHeight() - pane.getHeight() - minimizeButton.getHeight());
        mainGUI.add(minimizeButton);

        exitButton.setFont(new Font("Arial", Font.BOLD, 40));
        exitButton.setBackground(Color.red);
        exitButton.setSize(mainGUI.getWidth() / 3, 75);
        exitButton.setLocation(mainGUI.getWidth() - (exitButton.getWidth()), mainGUI.getHeight() - pane.getHeight() - minimizeButton.getHeight() - exitButton.getHeight());
        mainGUI.add(exitButton);

        mainGUI.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                pane.setSize(mainGUI.getWidth() / 3, mainGUI.getHeight() / 3);
                pane.setLocation(mainGUI.getWidth() - (pane.getWidth()), mainGUI.getHeight() - (pane.getHeight()));
                minimizeButton.setSize(mainGUI.getWidth() / 3, 75);
                minimizeButton.setLocation(mainGUI.getWidth() - (minimizeButton.getWidth()), mainGUI.getHeight() - pane.getHeight() - minimizeButton.getHeight());
                exitButton.setSize(mainGUI.getWidth() / 3, 75);
                exitButton.setLocation(mainGUI.getWidth() - (exitButton.getWidth()), mainGUI.getHeight() - pane.getHeight() - minimizeButton.getHeight() - exitButton.getHeight());
            }
        });

        mainGUI.lockWindow();
    }

    private void truncateLogText() {
        int numLinesToTruncate = logTextArea.getLineCount() - 100;
        if (numLinesToTruncate > 0) {
            try {
                int posLastLine = logTextArea.getLineEndOffset(numLinesToTruncate - 1);
                logTextArea.replaceRange("", 0, posLastLine);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
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
}
