package com.example.hichat.utils

import com.example.hichat.R
object AvatarHelper {

    val avatars = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6
    )

    fun getAvatar(name: String): Int {
        return when (name) {
            "avatar1" -> avatars[0]
            "avatar2" -> avatars[1]
            "avatar3" -> avatars[2]
            "avatar4" -> avatars[3]
            "avatar5" -> avatars[4]
            "avatar6" -> avatars[5]
            else -> avatars[0]
        }
    }
}