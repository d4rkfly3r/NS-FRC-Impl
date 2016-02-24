package us.jfreedman.src.ns.frc.client;

import us.jfreedman.src.ns.frc.common.packets.Packet;
import us.jfreedman.src.ns.frc.common.packets.Packet01;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class MainClient {

    public MainClient() {
        new NS().start();
        NS.connect(null, System.err::println);
        NS.addQueue(new Packet01("TeST").setDataType(Packet.Type.SERIALIZED), null, null);
        NS.addQueue(new Packet01("Hello").setDataType(Packet.Type.SERIALIZED), o -> System.out.println("SUCCESS"), null);
        NS.addQueue(new Packet01("HI!").setDataType(Packet.Type.SERIALIZED), null, null);
    }
}
