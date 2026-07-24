package com.example.hichat.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Time {

    fun formatTime(time: Long?): String{

        if (time == null) return ""

        val formatter = SimpleDateFormat(
            "hh:mm a",
            Locale.getDefault()
        )

        return formatter.format(Date(time))

    }
}