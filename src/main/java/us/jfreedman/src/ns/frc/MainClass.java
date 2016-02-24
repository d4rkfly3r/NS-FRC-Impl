package us.jfreedman.src.ns.frc;

import us.jfreedman.src.ns.frc.client.MainClient;
import us.jfreedman.src.ns.frc.server.MainServer;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainClass {

    public static void main(String[] args) {
        if (args.length < 1) return;
        switch (args[0]) {
            case "server":
                new MainServer();
                break;
            case "client":
                new MainClient();
                break;
            default:
        }
    }
}
