package us.jfreedman.src.ns.frc.server;

import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.packets.Packet;
import us.jfreedman.src.ns.frc.common.packets.Packet99;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class PluginBus {
    private static PluginBus ourInstance = new PluginBus();

    public static PluginBus getInstance() {
        return ourInstance;
    }

    public final HashMap<Class<?>, Object> plugins;

    private PluginBus() {
        plugins = new HashMap<>();
    }

    public void firePacket(Packet packet) {
        plugins.forEach((aClass, instance) -> {
            List<Method> methods = new ArrayList<>();
            Collections.addAll(methods, aClass.getDeclaredMethods());
            methods.stream().forEach(method -> {
                if (method.isAnnotationPresent(Listener.class)) {
                    if (method.getParameterCount() > 0) {
                        if (method.getParameterTypes()[0] == packet.getClass()) {
                            try {
                                method.invoke(instance, packet);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        });
    }

    public void init() {
        ClassFinder.getPluginClasses().forEach(aClass1 -> {
            try {
                plugins.put(aClass1, aClass1.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Plugins: ");
        plugins.forEach((aClass, instance) -> {
            System.out.println("\t" + aClass.getName());
        });
    }
}
