package com.elion.assistant.data.local.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter fun fromLocalDate(value: String?): LocalDate? =
        value?.let { LocalDate.parse(it, dateFormatter) }

    @TypeConverter fun toLocalDate(date: LocalDate?): String? =
        date?.format(dateFormatter)

    @TypeConverter fun fromLocalTime(value: String?): LocalTime? =
        value?.let { LocalTime.parse(it, timeFormatter) }

    @TypeConverter fun toLocalTime(time: LocalTime?): String? =
        time?.format(timeFormatter)

    @TypeConverter fun fromLocalDateTime(value: String?): LocalDateTime? =
        value?.let { LocalDateTime.parse(it, dateTimeFormatter) }

    @TypeConverter fun toLocalDateTime(dt: LocalDateTime?): String? =
        dt?.format(dateTimeFormatter)
}
