package com.niyas.composeprofiler.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.niyas.composeprofiler.viewmodel.ProfileViewModel
import com.niyas.composeprofiler.R


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(profileId: String, onBackClick: () -> Unit, viewModel: ProfileViewModel) {

    val profile by viewModel.getProfileById(profileId).collectAsState(initial = null)
    val pagerState = rememberPagerState(initialPage = 0)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp)
            .background(Color(0xFFD6BD43)) // Mustard background color
    ) {
        // Determine the screen height and width
        val screenHeight = maxHeight

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            // Create references for layout elements
            val (backButton, pager, titleDescriptionColumn) = createRefs()

            // Back button with profile ID at the top left
            Row(
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
                    .clickable { onBackClick() }, // Handle back button click
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black // Change the icon color here
                )
                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
                profile?.idString?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
                    )
                }
            }

            // Image carousel with dots and pagination text inside
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(pager) {
                        top.linkTo(backButton.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                HorizontalPager(
                    state = pagerState,
                    count = 5, // Change this to the actual number of images you have
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    val imageResId = when (page) {
                        0 -> profile?.imageResId
                        1 -> R.drawable.actor_two
                        2 -> R.drawable.actor_three
                        3 -> R.drawable.actor_four
                        else -> R.drawable.actor_five
                    }

                    imageResId?.let { painterResource(id = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.3f) // Use a percentage of the screen height
                                .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners for images
                        )
                    }
                }

                // Dots indicator
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Align to the bottom center
                        .padding(bottom = 10.dp) // Adjust padding to place above pagination text
                ) {
                    repeat(5) { index -> // Change this to the actual number of images
                        val activeColor = Color.Gray // Active dot color (purple)
                        val inactiveColor = Color.White // Inactive dot color (gray)
                        val color = if (pagerState.currentPage == index) activeColor else inactiveColor
                        Box(
                            modifier = Modifier
                                .size(15.dp) // Size of the dots
                                .padding(4.dp)
                                .background(color, CircleShape)
                        )
                    }
                }

                // Pagination text "1/5"
                Text(
                    text = "${pagerState.currentPage + 1}/5", // Change this to the actual number of images
                    modifier = Modifier
                        .align(Alignment.BottomEnd) // Align to the bottom end
                        .padding(bottom = 10.dp, end = 20.dp) // Adjust padding
                        .background(Color(0x55000000), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    color = Color.White
                )
            }

            // Title and Description below the carousel
            Column(
                modifier = Modifier
                    .constrainAs(titleDescriptionColumn) {
                        top.linkTo(pager.bottom, margin = 8.dp) // Positioning below pager
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
                    .fillMaxSize() // Ensure full width for the title and description
                    .background(Color.White, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .padding(16.dp), // Inner padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                profile?.let {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                        textAlign = TextAlign.Start,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                profile?.let {
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
    }
}