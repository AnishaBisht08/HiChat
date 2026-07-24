package com.example.hichat.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hichat.data.model.Message
import com.example.hichat.ui.theme.ChatBubbleBlue
import com.example.hichat.ui.theme.ChatBubbleGray
import com.example.hichat.utils.Time

@Composable
fun MessageBubble(
    message: Message,
    isMe: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp),
        horizontalArrangement = if (isMe) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {

        Surface(
            modifier = Modifier.widthIn(max = 200.dp),
            color = if (isMe) ChatBubbleBlue else ChatBubbleGray,
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isMe) 18.dp else 6.dp,
                bottomEnd = if (isMe) 6.dp else 18.dp
            ),
            shadowElevation = 1.dp
        ) {

            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                )
            ) {

                Text(
                    text = message.text,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = Time.formatTime(message.timestamp),
                        fontSize = 11.sp,
                        color = Color.White
                    )

                    if (isMe) {

                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            imageVector = Icons.Default.DoneAll,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.75f),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }

}