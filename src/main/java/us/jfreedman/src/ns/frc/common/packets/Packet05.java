package us.jfreedman.src.ns.frc.common.packets;

/**
 * Created by Joshua on 3/10/2016.
 * Project: NS-FRC-Impl
 */
public class Packet05 extends KeyedPacket<Packet05, Boolean> {

    public Packet05(String key, Boolean data) {
        this.innerData = data;
        this.key = key;
    }

    public Boolean getData() {
        return innerData;
    }
}
