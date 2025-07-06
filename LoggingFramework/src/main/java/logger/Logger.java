package logger;

import appenders.Appender;
import loggingmessage.LoggingMessage;
import loglevel.LogLevel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Logger {

    private final List<Appender> appenders;
    private final ExecutorService executorService;

    public Logger(List<Appender> appenders) {
        this.appenders = appenders;
        executorService = Executors.newFixedThreadPool(appenders.size());
    }

    public void log(final LogLevel logLevel, final String logMessageContent) {
        try {
            final LoggingMessage message = new LoggingMessage(logLevel, logMessageContent, Thread.currentThread().getName());
            for (final Appender appender : appenders) {
                if (!appender.getLogLevel().isAsSevere(logLevel)) continue;
                appender.write(message);
//                executorService.submit(() -> {
//                    try {
//                        appender.write(message);
//                    } catch (Exception e) {
//                        e.printStackTrace(); // Log exception for debugging
//                    }
//                });
            }
        } catch (Exception ex) {

        }
    }

    public void info(String logMessageContent) {
        log(LogLevel.INFO, logMessageContent);
    }

    public void debug(String logMessageContent) {
        log(LogLevel.DEBUG, logMessageContent);
    }

    public void error(String logMessageContent) {
        log(LogLevel.ERROR, logMessageContent);
    }

    public void close() {
//         Shutdown executor and wait for tasks to finish
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        for(final Appender appender: appenders) {
            try {
                appender.close();
            } catch (Exception ignored){
            }
        }
    }
}
