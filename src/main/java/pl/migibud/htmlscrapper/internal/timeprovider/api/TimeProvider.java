package pl.migibud.htmlscrapper.internal.timeprovider.api;

import java.time.*;

public interface TimeProvider {

    Instant now();

    default ZonedDateTime zonedDateTimeNow() {
        return ZonedDateTime.ofInstant(now(), ZoneId.systemDefault());
    }

    default LocalDate dateNow() {
        return LocalDate.ofInstant(now(), ZoneId.systemDefault());
    }

    default LocalDateTime dateTimeNow() {
        return LocalDateTime.ofInstant(now(), ZoneId.systemDefault());
    }

}
