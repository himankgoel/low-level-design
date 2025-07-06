package loggingmessage;

import loglevel.LogLevel;
import lombok.Getter;

@Getter
public class LoggingMessage {

    private final LogLevel logLevel;
    private final long timestamp;
    private final String logMessageContent;
    private final String requestId;

    public LoggingMessage(LogLevel logLevel, String logMessageContent, String requestId) {
        this.logLevel = logLevel;
        this.logMessageContent = logMessageContent;
        this.requestId = requestId;
        this.timestamp = System.currentTimeMillis();
    }
}
