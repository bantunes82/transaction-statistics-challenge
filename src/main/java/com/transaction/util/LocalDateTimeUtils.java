package com.transaction.util;


import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public interface LocalDateTimeUtils {

    static LocalDateTime getLocalDateTimeSixtySecondInThePast(){
        return getLocalDateTimeNow().minusSeconds(60);
    }

    static LocalDateTime getLocalDateTimeNow(){
        LocalDateTime sixtySecondsBefore = LocalDateTime.now(UTC);
        String sixtySecondsBeforeIsoFormat = sixtySecondsBefore.format(ISO_DATE_TIME);
        return LocalDateTime.parse(sixtySecondsBeforeIsoFormat, ISO_DATE_TIME);
    }
}
