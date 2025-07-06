package appenders;

import loggingmessage.LoggingMessage;
import loglevel.LogLevel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static logadapter.LogAdapter.format;

public class FileAppender implements Appender {

    BufferedWriter writer;

    private final LogLevel logLevel;
    public FileAppender(final LogLevel logLevel, final String logFile) throws IOException {
        this.logLevel = logLevel;
        writer = new BufferedWriter(new FileWriter(logFile, true));
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

        @Override
        public synchronized void write(final LoggingMessage message) throws IOException, InterruptedException {
            System.out.println("writing to file");
            writer.write(format(message) + "\n");
            writer.flush();
            System.out.println("written to file");
            Thread.sleep(1000);
        }

    public synchronized void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
