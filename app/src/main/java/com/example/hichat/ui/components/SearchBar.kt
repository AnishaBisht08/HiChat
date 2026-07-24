package com.example.hichat.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hichat.ui.theme.PrimaryBlue
import com.example.hichat.ui.theme.TextPrimary
import com.example.hichat.ui.theme.TextSecondary

@Composable
fun SearchBar(
    search: String,
    onSearchChange: (String) -> Unit
) {

    OutlinedTextField(
        value = search,
        onValueChange = onSearchChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 20.dp),
        placeholder = {
            Text("Search users")
        },
        leadingIcon = {
            Icon(Icons.Default.Search, null)
        },
        shape = RoundedCornerShape(18.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF8FAFC),
            unfocusedContainerColor = Color(0xFFF8FAFC),

            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = TextSecondary,

            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,

            cursorColor = PrimaryBlue,
            focusedLabelColor = PrimaryBlue,
            unfocusedLabelColor = TextSecondary
        )
    )
}