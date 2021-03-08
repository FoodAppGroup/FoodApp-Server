package com.spring.logging;

import com.spring.dataprovider.PropertyReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private static final String logFilePath = PropertyReader.getInstance().getConsole_LogDirectoryPath() + "/console-log_" + getFileTimeStamp() + ".log";
    private static final boolean showLog = PropertyReader.getInstance().getConsole_ShowLog();

    public static void log(String header, String content) {
        log("[" + header + "] " + content);
    }

    public static void log(String content) {
        content = content + " (" + getCurrentTimeStamp() + ")";
        printToConsole(content);
        appendToFile(content);
    }

    private static void printToConsole(String content) {
        if (showLog) {
            System.out.println(content);
        }
    }

    private static void appendToFile(String content) {
        try {
            Files.writeString(
                    Path.of(logFilePath),
                    content + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("[ERROR] Can not append content to log-file: " + logFilePath);
            e.printStackTrace();
        }
    }

    private static String getFileTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return sdf.format(now);
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy");
        Date now = new Date();
        return sdf.format(now);
    }
}
