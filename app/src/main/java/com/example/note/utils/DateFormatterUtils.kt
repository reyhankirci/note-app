package com.example.note.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatterUtils {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun convertDateByDateTimeFormatter(localDate: LocalDate, dateTimeFormatter: DateTimeFormatter): String {
            return localDate.format(dateTimeFormatter).toString()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun convertDateToLocalDate(date: Date): LocalDate {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }
}