package com.niyas.composeprofiler.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.niyas.composeprofiler.R
import com.niyas.composeprofiler.util.ProfileState
import com.niyas.composeprofiler.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileList(
    viewModel: ProfileViewModel,
    navController: NavController,
) {
    // Collect the profiles as state
    val profiles by viewModel.profiles.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Check if the list is empty
    if (profiles.profiles.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF33A4DC)) // Use the correct color format
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No data found",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.Black)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Recover the static profiles
                coroutineScope.launch {
                    viewModel.insertStaticProfiles()
                    Toast.makeText(context, "Data recovered", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Recover Data")
            }
        }
    } else {
        ProfileCardList(profiles, navController, coroutineScope, viewModel, context)
    }
}

@Composable
fun ProfileCardList(
    profiles: ProfileState,
    navController: NavController,
    coroutineScope: CoroutineScope,
    viewModel: ProfileViewModel,
    context: Context
) {

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between cards
        contentPadding = PaddingValues(16.dp) // Padding around the content
    ) {
        items(profiles.profiles) { profile ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .background(Color(0xFF33A4DC)) // Use the correct color format
                    .width(250.dp) // Use 90% of the screen width
                    .clickable {
                        // Navigate to the image screen when clicked
                        navController.navigate("image/${profile.id}") // Navigate with profile ID
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {

                // Display the image using the resource ID
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(profile.imageResId)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.place_holder),
                    error = painterResource(R.drawable.place_holder), // Error image
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(RectangleShape)
                        .background(Color.Blue) // Use full width of the card
                        .fillMaxWidth()
                        .height(200.dp) // Set the size (height and width) of the image
                )

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
                    )
                    // Display the profile's description with overflow handling
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Display the profile's name

                        Text(
                            text = profile.description,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                            overflow = TextOverflow.Ellipsis, // Use Ellipsis for clipping
                            maxLines = 2, // Limit to 2 lines
                            modifier = Modifier.align(Alignment.CenterStart) // Align to start
                        )
                    }

                    // Display Yes and No buttons for profile removal
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        Button(onClick = {
                            coroutineScope.launch {
                                viewModel.deleteProfile(profile)
                                Toast.makeText(
                                    context,
                                    "${profile.name} deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }) {
                            Text("No")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            coroutineScope.launch {
                                viewModel.deleteProfile(profile)
                                Toast.makeText(
                                    context,
                                    "${profile.name} deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }) {
                            Text("Yes")
                        }
                    }
                }
            }
        }
    }
}

