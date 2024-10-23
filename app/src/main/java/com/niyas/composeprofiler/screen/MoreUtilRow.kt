package com.niyas.composeprofiler.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MoreUtilRow(title: String, onMoreClick: () -> Unit) {

    Row(
        modifier = Modifier
            .clickable {
                onMoreClick()
            }
            .fillMaxWidth()
            .padding(top = 45.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically // Align items vertically to center

    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
        )
    }
}

