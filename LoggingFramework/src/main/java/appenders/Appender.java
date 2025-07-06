package appenders;

import loggingmessage.LoggingMessage;
import loglevel.LogLevel;

import java.io.IOException;

public interface Appender {

    LogLevel getLogLevel();
    void write(LoggingMessage message) throws InterruptedException, IOException;
    void close() throws IOException;
}
