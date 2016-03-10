package us.jfreedman.src.ns.frc.client;

import us.jfreedman.src.ns.frc.common.packets.Packet01;
import us.jfreedman.src.ns.frc.common.packets.Packet02;
import us.jfreedman.src.ns.frc.common.packets.Packet03;
import us.jfreedman.src.ns.frc.common.packets.Packet05;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainClient {

    public MainClient() {
        NS.connect("127.0.0.1", null, System.err::println);
        new NS().start();
        NS.addQueue(new Packet01("TeST"), null, null);
        NS.addQueue(new Packet01("Hello"), o -> System.out.println("SUCCESS"), null);
        NS.addQueue(new Packet01("HI!"), null, null);
        NS.addQueue(new Packet02(5), null, null);
        NS.addQueue(new Packet02(50000), null, null);
        NS.addQueue(new Packet03("Josh", "This is MIKE!"), null, null);
//        NS.addQueue(new Packet05("Transmission", true), null, o -> NS.addQueue(new Packet05("Transmission", true), null, null));

        transmission();
    }

    private void transmission() {
        NS.addQueue(new Packet05("Transmission", true), null, o -> {
            System.err.println("Failing");
            transmission();
        });
    }
}
