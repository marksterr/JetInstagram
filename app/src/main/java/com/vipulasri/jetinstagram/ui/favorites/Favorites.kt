package com.vipulasri.jetinstagram.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.data.FavoritesRepository
import com.vipulasri.jetinstagram.model.FavoriteUser

@Composable
fun Favorites() {
    val favoriteUsers = FavoritesRepository.getFavoriteUsers()

    Column {
        Text(
            text = "Favorites",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        SearchBar()
        FavoriteUsersList(favoriteUsers)
    }
}

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Search") },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun FavoriteUsersList(favoriteUsers: List<FavoriteUser>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteUsers) { user ->
            FavoriteUserRow(user)
        }
    }
}

@Composable
fun FavoriteUserRow(user: FavoriteUser) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = user.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = user.username, style = MaterialTheme.typography.subtitle1)
            Text(text = user.name, style = MaterialTheme.typography.subtitle1, color = Color.Gray)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { FavoritesRepository.removeFavoriteUser(user.id) }) {
            Text("Remove")
        }
    }
}