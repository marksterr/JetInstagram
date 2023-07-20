package com.vipulasri.jetinstagram.ui.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.data.ProfileRepository
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.User

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Profile(user: User) {
    val posts = ProfileRepository.getProfilePosts()

    Column {
        ProfileHeader(user)
        FollowMessageButtons()
        UserPostsGrid(posts)
    }
}

@Composable
fun ProfileHeader(user: User) {
    Row(
        modifier = Modifier.padding(16.dp), // Add padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display the user's image
        Image(
            painter = rememberImagePainter(data = user.image),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape) // Apply a circle crop
                .border(2.dp, MaterialTheme.colors.primary, CircleShape) // Apply border
        )

        Spacer(modifier = Modifier.width(16.dp)) // Add space between image and text

        Column {
            // Display the user's name and username
            Text(text = user.name, style = MaterialTheme.typography.h4)
            Text(text = "@${user.username}", style = MaterialTheme.typography.subtitle1)

            // Display the user's followers and following count
            Spacer(modifier = Modifier.height(8.dp)) // Add some space
            Text(
                text = "1,242 followers",
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray
            )
            Text(
                text = "398 following",
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FollowMessageButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp), // Padding for aesthetic spacing
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* TODO: handle follow action */ },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text("Follow")
        }

        Button(
            onClick = { /* TODO: handle message action */ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text("Message")
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun UserPostsGrid(posts: List<Post>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3), // Or however many columns you want
        contentPadding = PaddingValues(8.dp), // Increase padding
        content = {
            items(posts.size) { index ->
                val post = posts[index]
                Image(
                    painter = rememberImagePainter(data = post.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // Crop the image to fill the space
                    modifier = Modifier
                        .aspectRatio(1.33f) // Adjust to match the aspect ratio of your images
                        .fillMaxSize() // Fill the space
                        .padding(2.dp) // Add padding between images
                        .border(1.dp, MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp)) // Add a border
                )
            }
        }
    )
}