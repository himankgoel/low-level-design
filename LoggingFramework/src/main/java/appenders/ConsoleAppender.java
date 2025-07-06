package appenders;

import loggingmessage.LoggingMessage;
import loglevel.LogLevel;

import static logadapter.LogAdapter.format;

public class ConsoleAppender implements Appender {

    private final LogLevel logLevel;
    public ConsoleAppender(final LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public synchronized void write(final LoggingMessage logMessage) {
        System.out.println(format(logMessage));
    }

    @Override
    public void close() {
        // No op.
    }
}
