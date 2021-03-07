package com.spring.logging;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private static final String logFilePath = "src/main/resources/logs/console-log_" + getCurrentTimeStamp() + ".txt";
    private static boolean firstOutput = true;
    @Value("${console.show-log}")
    private static boolean showLog;

    public static void log(String header, String content) {
        log("[" + header + "] " + content);
    }

    public static void log(String content) {
        content = content + "(" + getCurrentTimeStamp() + ")";
        printToConsole(content);
        appendToFile(content);
    }

    private static void printToConsole(String content) {
        if (showLog) {
            System.out.println(content);
        }
    }

    private static void appendToFile(String content) {
        if (firstOutput) {
            if (new File(logFilePath).getParentFile().mkdirs()) {
                firstOutput = false;
            }
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath)))) {
            writer.println(content);
            writer.flush();
        } catch (IOException e) {
            System.out.println("[ERROR] Can not append content to log-file: " + logFilePath);
            e.printStackTrace();
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
        Date now = new Date();
        return sdf.format(now);
    }
}
