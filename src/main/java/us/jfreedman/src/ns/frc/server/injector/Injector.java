package us.jfreedman.src.ns.frc.server.injector;

import us.jfreedman.src.ns.frc.common.injector.Inject;
import us.jfreedman.src.ns.frc.server.PluginBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joshua on 2/25/2016.
 * Project: NS-FRC-Impl
 */
public class Injector {
    private static Injector ourInstance = new Injector();

    public static Injector getInstance() {
        return ourInstance;
    }

    private Injector() {
    }

    public <T> Injector inject(T t) {
        System.out.println("Starting!");
        PluginBus.getInstance().plugins.forEach((aClass, o) -> {
            System.out.println("Plugin: " + aClass);
            List<Field> fields = new ArrayList<>();
            Collections.addAll(fields, aClass.getDeclaredFields());
            fields.stream()
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .filter(field -> field.getType().equals(t.getClass()))
                    .forEach(field -> {
                        try {
                            field.setAccessible(true);
                            field.set(o, t);
                            System.out.println(field.get(o));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        });
        return this;
    }

}
