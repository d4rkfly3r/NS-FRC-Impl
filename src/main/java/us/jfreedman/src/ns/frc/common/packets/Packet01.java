package us.jfreedman.src.ns.frc.common.packets;

/**
 * Created by Joshua Freedman on 2/9/2016.
 * Project: NS-FRC-Impl
 */
public class Packet01 extends Packet<Packet01, String> {

    public Packet01() {
        this("From Josh");
    }

    public Packet01(String extra) {
        this.innerData = extra;
        setDataType(Type.MESSAGE);
    }

    public String getExtra() {
        return innerData;
    }

}
