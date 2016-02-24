package us.jfreedman.src.ns.frc.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Joshua on 2/24/2016.
 * Project: NS-FRC-Impl
 */
@SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions", "unused"})
public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private final ArrayList<String> log;
    private Calendar time;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final SimpleDateFormat logSDF = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss");
    private final String prefix;
    private final String suffix;
    private final String cleanupCode = ANSI_RESET;

    private static final String ERROR = ANSI_RED + "NS ERROR: " + ANSI_RESET;
    private static final String ERROR_SUFFIX = "!!";
    private static final String CLEAR_LOG = ANSI_CYAN + "Log has been cleared!" + ANSI_RESET;

    private static final int logLevel = 0; // 0 -> most details 10 -> least details

    public Logger() {
        this(new ArrayList<>(), ANSI_PURPLE + "[NS-FRC] " + ANSI_RESET, "");
    }

    public Logger(ArrayList<String> pastLog) {

        this(pastLog, ANSI_PURPLE + "[NS-FRC] " + ANSI_RESET, "");
    }

    public Logger(String prefix) {
        this(new ArrayList<>(), prefix, "");
    }

    public Logger(String prefix, String suffix) {
        this(new ArrayList<>(), prefix, suffix);
    }

    public Logger(ArrayList<String> pastLog, String prefix) {
        this(pastLog, prefix, "");
    }

    public Logger(ArrayList<String> pastLog, String prefix, String suffix) {
        this.time = Calendar.getInstance();
        this.log = pastLog;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void debug(Object message) {
        this.time = Calendar.getInstance();
        if (message != null && logLevel < 5) {
            String msg = ANSI_YELLOW + "DEBUG" + ANSI_RESET + " [" + sdf.format(this.time.getTime()) + "] " + prefix + message.toString() + suffix + cleanupCode;
            log.add(msg.replaceAll("(\\[[0-99]+m)", "") + "\n");
            System.out.println(msg);
        }
    }

    public void debugQuiet(String message) {
        this.time = Calendar.getInstance();
        if (message != null && logLevel < 5) {
            String msg = message + cleanupCode;
            log.add(msg.replaceAll("(\\[[0-99]+m)", "") + "\n");
            System.out.println(msg);
        }
    }

    public void debugError(String message) {
        this.time = Calendar.getInstance();
        if (message != null && logLevel < 5) {
            String msg = ANSI_YELLOW + "DEBUG" + ANSI_RESET + " [" + sdf.format(this.time.getTime()) + "] " + ERROR + message + ERROR_SUFFIX + cleanupCode;
            log.add(msg.replaceAll("(\\[[0-99]+m)", "") + "\n");
            System.out.println(ANSI_RED + msg);
        }
    }

    public void log(Object message) {
        this.time = Calendar.getInstance();
        if (message != null) {
            String msg = "[" + sdf.format(this.time.getTime()) + "] " + prefix + message.toString() + suffix + cleanupCode;
            log.add(msg.replaceAll("(\\[[0-99]+m)", "") + "\n");
            System.out.println(msg);
        }
    }

    public void logQuiet(String message) {
        this.time = Calendar.getInstance();
        if (message != null) {
            String msg = message + cleanupCode;
            log.add(msg.replaceAll("(\\[[0-99]+m)", "") + "\n");
            System.out.println(msg);
        }
    }

    public void logError(String message) {
        this.time = Calendar.getInstance();
        if (message != null) {
            String msg = "[" + sdf.format(this.time.getTime()) + "] " + ERROR + message + ERROR_SUFFIX + cleanupCode;
            log.add(msg.replaceAll("(\\[[0-99]+m)", "") + "\n");
            System.out.println(ANSI_RED + msg);
        }
    }

    public void logClear() {
        log.add(CLEAR_LOG.replaceAll("(\\[[0-99]+m)", "") + "\n");
        for (int i = 1; i < 50; i++)
            System.out.print("\n");
        System.out.println(CLEAR_LOG);
    }

    public void logClear(int lines) {
        log.add(CLEAR_LOG.replaceAll("(\\[[0-99]+m)", "") + "\n");
        for (int i = 1; i < lines; i++)
            System.out.print("\n");
        System.out.println(CLEAR_LOG);
    }

    public boolean saveLog() {
        this.time = Calendar.getInstance();
        File rootLogDir = new File("logs");

        if (!rootLogDir.exists())
            //noinspection ResultOfMethodCallIgnored
            rootLogDir.mkdirs();

        File logFile = new File(rootLogDir, logSDF.format(this.time.getTime()) + ".nslog");
        try (FileOutputStream fos = new FileOutputStream(logFile); OutputStreamWriter osw = new OutputStreamWriter(fos)) {

            log.forEach(s -> {
                try {
                    osw.write(s);
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getLog() {
        return log.toString();
    }

}
