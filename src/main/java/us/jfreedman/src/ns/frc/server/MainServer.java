package us.jfreedman.src.ns.frc.server;

import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.packets.Packet;
import us.jfreedman.src.ns.frc.server.gui.MainGUI;
import us.jfreedman.src.ns.frc.server.injector.Injector;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainServer {

    MainGUI mainGUI = MainGUI.getInstance();

    public MainServer() {
        Logger logger = new Logger();
        PluginBus.getInstance().init();
        Injector.getInstance().inject(MainGUI.getInstance());
        try {
            ServerSocket serverSocket = new ServerSocket(7093, 3);
            Socket client;
            mainGUI.setup();

            while (true) {
                client = serverSocket.accept();
                try (ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream())) {
                    while (client.isConnected()) {
                        try {
                            Object unknown = objectInputStream.readObject();
                            if (unknown instanceof Packet<?>) {
                                PluginBus.getInstance().firePacket((Packet) unknown);
                            }
                        } catch (EOFException ignored) {
                            logger.debugError(ignored.getMessage());
                        } catch (SocketException e0) {
                            break;
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
