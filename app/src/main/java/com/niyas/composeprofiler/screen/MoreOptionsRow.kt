package com.niyas.composeprofiler.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MoreOptionsRow(text1: String, text2: String, onMoreClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 45.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Space between text columns and icon
    ) {
        // Column to contain two text elements
        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = text1,
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // More icon on the right
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Options",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onMoreClick()
                        },
                    tint = Color.White
                )
            }
            // Row to contain text2 and box with "5 new"
            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between text2 and the box
            ) {

                Text(
                    text = text2,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )

                // Box with rounded corners and stroke (border) around "5 new"
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp, // Stroke width
                            color = Color.Cyan, // Stroke color
                            shape = RoundedCornerShape(8.dp) // Rounded corners for the stroke
                        )
                        .background(
                            color = Color(0xFF33A4DC), // Background color inside the box
                            shape = RoundedCornerShape(8.dp) // Rounded corners for the background
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp) // Padding inside the box
                ) {
                    Text(
                        text = "5 new",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
            }
        }
    }
}