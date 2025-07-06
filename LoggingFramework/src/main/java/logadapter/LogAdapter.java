package logadapter;

import loggingmessage.LoggingMessage;

public class LogAdapter {

    public static String format(final LoggingMessage message) {
        return String.format("[%s] [%s] [%s]: %s",
                message.getTimestamp(),
                message.getLogLevel(),
                message.getRequestId(),
                message.getLogMessageContent());
    }

}
