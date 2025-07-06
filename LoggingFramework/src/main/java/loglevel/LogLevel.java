package loglevel;

public enum LogLevel {
    DEBUG, INFO, WARN, ERROR, FATAL;

    public boolean isAsSevere(final LogLevel other) {
        return this.ordinal() >= other.ordinal();
    }
}
