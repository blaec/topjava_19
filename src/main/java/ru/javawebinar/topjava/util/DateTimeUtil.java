package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<? super T>> boolean isBetweenInclusive(T lt, T startDate, T endDate) {
        return lt.compareTo(startDate) >= 0 && lt.compareTo(endDate) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toLocalDate(String localDate, LocalDate def) {
        return localDate.isEmpty() ? def : LocalDate.parse(localDate);
    }

    public static LocalTime toLocalTime(String localTime, LocalTime def) {
        return localTime.isEmpty() ? def : LocalTime.parse(localTime);
    }
}

