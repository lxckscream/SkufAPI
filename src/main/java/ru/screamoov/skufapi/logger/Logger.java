package ru.screamoov.skufapi.logger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public final String plugin_name;
    public final boolean show_time;

    public Logger(String plugin_name, boolean show_time) {
        this.plugin_name = plugin_name;
        this.show_time = show_time;
    }

    public void info(String message) {
        System.out.println("[INFO/" + plugin_name + (show_time ? " | " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) : "") + "] " + message);
    }

    public void fine(String message) {
        System.out.println("[FINE/" + plugin_name + (show_time ? " | " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) : "") + "] " + message);
    }

    public void warn(String message) {
        System.out.println("[WARN/" + plugin_name + (show_time ? " | " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) : "") + "] " + message);
    }

    public void critical(String message) {
        System.out.println("[CRITICAL/" + plugin_name + (show_time ? " | " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) : "") + "] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR/" + plugin_name + (show_time ? " | " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) : "") + "] " + message);
    }
}
