package us.jfreedman.src.ns.frc.common.packets;

import java.util.HashMap;

/**
 * Created by Joshua on 2/24/2016.
 * Project: NS-FRC-Impl
 */
public class Packet99 extends Packet<Packet99> {
    private HashMap<Class<?>, Object> plugins;

    public Packet99(HashMap<Class<?>, Object> plugins) {
        this.plugins = plugins;
    }

    public HashMap<Class<?>, Object> getPlugins() {
        return plugins;
    }
}
