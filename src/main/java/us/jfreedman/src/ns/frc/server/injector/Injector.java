package us.jfreedman.src.ns.frc.server.injector;

import us.jfreedman.src.ns.frc.common.Logger;
import us.jfreedman.src.ns.frc.common.injector.Inject;
import us.jfreedman.src.ns.frc.server.PluginBus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joshua on 2/25/2016.
 * Project: NS-FRC-Impl
 */
public class Injector {
    private Logger logger = new Logger(Injector.class);

    private static Injector ourInstance = new Injector();

    public static Injector getInstance() {
        return ourInstance;
    }

    private Injector() {
    }

    public <T> Injector inject(T t) {
        PluginBus.getInstance().plugins.forEach((aClass, o) -> {
            List<Field> fields = new ArrayList<>();
            Collections.addAll(fields, aClass.getDeclaredFields());
            fields.stream()
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .filter(field -> field.getType().equals(t.getClass()))
                    .forEach(field -> {
                        try {
                            field.setAccessible(true);
                            field.set(o, t);
                            logger.debug(field.get(o));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        });
        return this;
    }

    public Injector inject(Class<?> t) {
        PluginBus.getInstance().plugins.forEach((aClass, o) -> {
            List<Field> fields = new ArrayList<>();
            Collections.addAll(fields, aClass.getDeclaredFields());
            fields.stream()
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .filter(field -> field.getType().equals(t))
                    .forEach(field -> {
                        try {
                            field.setAccessible(true);
                            field.set(o, t.getConstructor(Class.class).newInstance(aClass));
                            logger.debug(field.get(o));
                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    });
        });
        return this;
    }
}
