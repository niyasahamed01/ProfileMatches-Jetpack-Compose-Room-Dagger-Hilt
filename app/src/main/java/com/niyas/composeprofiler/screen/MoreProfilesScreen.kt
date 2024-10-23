package com.niyas.composeprofiler.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.niyas.composeprofiler.util.Profile
import com.niyas.composeprofiler.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun MoreProfilesScreen(viewModel: ProfileViewModel) {

    val profiles by viewModel.profiles.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00C853),
                        Color(0xFF69F0AE)
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(profiles.profiles) { profile ->
                ProfileCardList(profile, viewModel)
            }
        }

    }
}


@Composable
fun ProfileCardList(
    profile: Profile, viewModel: ProfileViewModel
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(profile.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillHeight
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconWithText(
                        icon = Icons.Default.CheckCircle,
                        text = "Verified",
                        color = Color(0xFFaebbeb)
                    )
                    IconWithText(
                        icon = Icons.Default.Star,
                        text = "Premium NRI",
                        color = Color(0xFFcfbbe4)
                    )
                }

                Text(
                    text = profile.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 3.dp)
                )

                Text(
                    text = profile.description,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 3.dp)
                )

                Divider(
                    color = Color(0xFFF8F8F8),
                    thickness = 3.dp,
                    modifier = Modifier.padding(vertical = 5.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.deleteProfile(profile)
                                Toast.makeText(
                                    context,
                                    "${profile.name} deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFb6b6b6)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Shortlist",
                            color = Color(0xFFb6b6b6),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "Like him?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    IconButton(onClick = {
                        coroutineScope.launch {
                            viewModel.deleteProfile(profile)
                            Toast.makeText(
                                context,
                                "${profile.name} deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Check),
                            contentDescription = null,
                            modifier = Modifier
                                .border(
                                    width = 1.dp, // Stroke width
                                    color = Color.Black, // Stroke color
                                    shape = RoundedCornerShape(8.dp) // Rounded corners for the stroke
                                )
                                .background(color = Color.Magenta, shape = RoundedCornerShape(5.dp))
                                .padding(5.dp)
                        )
                    }

                    IconButton(onClick = {
                        coroutineScope.launch {
                            viewModel.deleteProfile(profile)
                            Toast.makeText(
                                context,
                                "${profile.name} deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Clear),
                            contentDescription = null,
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFFF9800),
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IconWithText(icon: ImageVector, text: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = color
        )
    }
}


