package main;

import appenders.ConsoleAppender;
import appenders.FileAppender;
import logger.Logger;
import loglevel.LogLevel;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            ConsoleAppender consoleAppender = new ConsoleAppender(LogLevel.DEBUG);
            FileAppender fileAppender = new FileAppender(LogLevel.INFO, System.getProperty("user.dir") + "/app.log");
            List<appenders.Appender> appenders = Arrays.asList(consoleAppender, fileAppender);
            Logger logger = new Logger(appenders);

            Runnable logTask = () -> {
                for (int i = 0; i < 2; i++) {
                    logger.info("Info message from " + Thread.currentThread().getName() + ": " + i);
                    logger.debug("Debug message from " + Thread.currentThread().getName() + ": " + i);
                    logger.error("Error message from " + Thread.currentThread().getName() + ": " + i);
                }
            };

            Thread t1 = new Thread(logTask, "Thread-1");
            Thread t2 = new Thread(logTask, "Thread-2");
            Thread t3 = new Thread(logTask, "Thread-3");

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();
            logger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
