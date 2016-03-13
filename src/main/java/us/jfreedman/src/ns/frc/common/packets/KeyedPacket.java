package us.jfreedman.src.ns.frc.common.packets;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class KeyedPacket<T extends Packet, I> extends Packet<KeyedPacket<T, I>, I> {

    String key;

    public String getKey() {
        return key;
    }
}
