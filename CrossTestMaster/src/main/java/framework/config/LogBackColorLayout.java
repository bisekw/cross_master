package framework.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LogBackColorLayout extends LayoutBase<ILoggingEvent> {
    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder sbuf = new StringBuilder(128);
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimeStamp()), ZoneId.of("Europe/Warsaw"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
        if ((event.getLoggerName().contains("framework."))) {
            sbuf.append("\u001B[34m"); // Kolor niebieski
        }
        if (event.getFormattedMessage().contains("[SCENARIO CONTEXT]")) {
            sbuf.append("\u001B[32m"); // Kolor zielony
        }
        if (event.getFormattedMessage().contains("[SCENRAIO NAME]")) {
            sbuf.append("\u001B[38;5;208m"); // Przybliżony kolor pomarańczowy
        }
        sbuf.append(dateTime.format(formatter));
        sbuf.append(" ");
        sbuf.append(" ");
        sbuf.append(event.getLevel());
        sbuf.append(" [");
        sbuf.append(event.getThreadName());
        sbuf.append("] ");
        sbuf.append(event.getLoggerName());
        sbuf.append(" - ");
        sbuf.append(event.getFormattedMessage());
        sbuf.append("\u001B[0m"); // Reset kolorów
        sbuf.append(System.lineSeparator());

        return sbuf.toString();
    }
}
